$(document).ready(function(){
	var URL = "/japanese-OES/teacher/questions";
	generate("general",1);//展示普通试题
	generate("img",1);
	imgHide();
	
	eventBind();
	
	//隐藏general table
	function genHide(){
		$('#questions_gen').hide();
		$('#questions_img').show();
	}
	
	//隐藏img table
	function imgHide(){
		$('#questions_gen').show();
		$('#questions_img').hide();
	}
	
	//点击导航栏图片题
	$('#navi_img').click(function(){
		genHide();
		$(this).attr({"class":"active"});
		$('#navi_gen').attr({"class":""});
	});
	
	//点击导航栏普通题
	$('#navi_gen').click(function(){
		imgHide();
		$(this).attr({"class":"active"});
		$('#navi_img').attr({"class":""});
	});
	
	$('.btn-large').click(function(){
		var examName = $('#examName').val();
		var data = new Array();
		var count = 0;
		$('#t_selected').find('tr').each(function(index,element){
				if(index != 0){
					data[count++] = {id:$(this).find('td#t_id').text(),
									type:$(this).find('td#t_type').text()};
				}
				
		});
		if(examName == undefined || examName == "" || examName == null)
		{
			alert("请输入试卷名");
			return ;
		}
		if(data.length == 0){
			alert("请选择试题");
			return ;
		}
			
		var json = "questions=" + JSON.stringify(data) + "&examName=" + examName;
		console.dir(json);
		$.ajax({
			url:"/japanese-OES/teacher/generatePaper",
			data:json,
			success:function(data){
				alert(data);
			},
		});
	});
	
	//构造普通试题表的内容 
	function generate(type,pageNo){
		var	url = URL + "?type="+type;
		if(pageNo != undefined)
			url += "&page=" + pageNo;
		$.ajax({
			url:url,
			async:false,
			success:function(data){
				var json = eval('('+data+')');
				var currentPage = json.currentPage;
				var totalPageCount = json.totalPageCount;
				var questions = json.questions;
				
				var quesTable = getQuestion(questions,type);
				//生成table标签
				var tableTag = "<table class='table'> " +
				"<thead>" +
					"<tr>" +
						"<td>试题名</td>" +
						"<td>类型</td>"+
						"<td>主题</td>" +
						"<td>上传时间</td>" +
						"<td>资源</td>" +
						"<td></td>" +//对应btn
						"<td></td>"+ //对应题目的数据库id 
					"</tr>" +
						quesTable +
				"</thead>" +
		"</table>" + 
		generatePageBtn(currentPage,totalPageCount,"/japanese-OES/teacher/questions",type);
				
				//刷新页面
				
				if(type == "general"){
					$('#questions_gen').empty();
					$('#questions_gen').append(tableTag);
				}
				if(type == "img"){
					$('#questions_img').empty();
					$('#questions_img').append(tableTag);
				}
				eventBind();//绑定事件
			}
		});
		
		function getQuestion(data,type){
		var result = "";
		for(var i = 0; i < data.length ; i++){
			var question = data[i];
			result += "<tr><td id='name'>" + question.description + "</td>"
					+"<td id='type' >" + type +"</td>"
					+ "<td id='title'>" + question.title + "</td>"
			+ "<td id='date'>" + question.upDate + "</td>";
			if(type == "general")
				result += "<td>无</td>";
			else if(type == "img")
				result += "<td src='"+question.resource+"'>预览</td>";
			result += "<td><input class='btn btn-info' category='"+type+"' id='" +i+ "' type='button' " +
					" value ='选择'></td>"+
					"<td id='id' style='display:none'>"+question.id+"</td>";
			}
			return result;
		}
	}
	//构造翻页button
	function generatePageBtn(currentPage,totalPageCount,url,type){
		var result="";
		result += " <div class='pagination'><ul>";
		for(var i = 1; i <= eval(totalPageCount); i++){
			if(i == eval(currentPage))
				result += "<li class='acactive-link'>";
			else
				result += "<li>";
			
			result += "<a class='"+type+"' id='"+i+"' href='#'>" + i +"</a></li>";
			
		}
		result += "</ul></div>";
		return result;
	}
	
	
	//绑定事件
	function eventBind(){
		//普通题点击翻页
		$('.general').unbind().click(function(){
			var pageNo = $(this).attr('id');
			//展示试题
			generate("general",pageNo);
		});
		
		$('.img').unbind().click(function(){
			var pageNo = $(this).attr('id');
			//展示试题
			generate("img",pageNo);
		});
		
		//向选中的题目中添加该题信息
		$('input.btn-info').unbind().click(function(){
			var id = $(this).attr('id');
			var tr = $(this).parent().parent();
			var name = tr.children('td#name').text();
			var type = tr.children('td#type').text();
			var idDB = tr.children('td#id').text();
			$('#t_selected').append(
					"<tr><td>" + name +"</td>"+
					"<td id='t_type'>" + type + "</td>" + 
					"<td><input class='btn btn-warning' id='delete"+
					"' type='button' index='"+id+"' category='"+type+"' value = '删除'/></td>" +
					"<td id='t_id' style='display:none'>"+idDB+"</td></tr>"
					);
			
			var selectBtn = $(this);
			selectBtn.attr({"disabled":"disabled"});
			//给添加的btn绑定事件 点击之后删除该行  恢复选择表中btn可点击
			$('.btn-warning').unbind().click(function(){
				var btn = $(this);
				var type = btn.attr("category");
				var index = btn.attr("index");
				$(this).parent().parent().remove();
				$(".btn-info[category='"+type+"'][id='"+index+"']").removeAttr("disabled");
			});
		});
		
	}
	
	
});
