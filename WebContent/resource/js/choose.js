$(document).ready(function() {
	var exam = "";
	$('input[type=radio]').click(function(){
		var tr = $(this).parent().siblings()[2];//id
		exam = tr.innerHTML;
	});
	
	$('.btn-warning').click(function(){
		if(exam == "")
			alert("请选择试卷");
		else{
			$.ajax({
				url:"/japanese-OES/teacher/choose?id="+exam,
				success:function(){
					alert("设置成功");
				}
			});
		}
	});
	
	//请求试题的xml文件 并将试题信息展现到页面
	$('.btn-info').click(function(){
		var s = $(this).parents('tr').children('td');
		var path = "/japanese-OES/exam/" + s[0].innerText + ".xml";
		$.ajax({
			url:path,
			dataType:'xml',
			success:function(data){
				$('#details').find('tbody').empty();
				$(data).find("generals").children().each(function(index,ele){
					var e = $(ele);
					var name = e.find('description')[0].innerHTML;
					var time = e.find('name')[0].innerHTML;
					time = time.substring(0,time.indexOf('.'));
					$('#details').find('tbody').append("<tr><td>"+name+"</td>" +
							"<td>general</td><td>"+time+"</td></tr>");
					$('#details').attr('style','display');
				});
				$(data).find("imgs").children().each(function(index,ele){
					var e = $(ele);
					var name = e.find('description')[0].innerHTML;
					var time = e.find('name')[0].innerHTML;
					time = time.substring(0,time.indexOf('.'));
					$('#details').find('tbody').append("<tr><td>"+name+"</td>" +
							"<td>img</td><td>"+time+"</td></tr>");
					$('#details').attr('style','display');
				});
			}
		
		});
	});
});