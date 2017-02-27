$(document).ready(function () {
	generate(1);
	eventBind();
});

//构造普通试题表的内容
function generate(pageNo) {
	var url ="/japanese-OES/teacher/choosePaper";
	if (pageNo != undefined)
		url += "?pageNo=" + pageNo;
	$.ajax({
		url: url,
		type: "POST",
		async: false,
		success: function (data) {
			var json = eval('(' + data + ')');
			var currentPage = json.currentPageNo;
			var totalPageCount = json.totalPageCount;
			var questions = json.papers;
		
			var quesTable = getQuestion(questions);
			//生成table标签
			
			var tableTag = "<table class='table'> " +
				"<thead>" +
				"<tr>" +
				"<th>试卷名</th>" +
				"<th>组卷时间</th>" +
				"<th>详情</th>" +
				"<th>选择</th>" +
				"</tr>" +
				quesTable +
				"</thead>" +
				"</table>" +
				generatePageBtn(currentPage, totalPageCount, "/japanese-OES/teacher/choosePaper");
			//刷新页面
			$("#papers").empty().append(tableTag);
			/*
			if (type == "general") {
				$('#questions_gen').empty();
				$('#questions_gen').append(tableTag);
			}
			if (type == "img") {
				$('#questions_img').empty();
				$('#questions_img').append(tableTag);
			}
			*/
			eventBind();//绑定事件
		}
	});
}
//构造翻页button
function generatePageBtn(currentPage,totalPageCount,url){
	var result="";
	result += " <div class='pagination'><ul>";
	for(var i = 1; i <= eval(totalPageCount); i++){
		if(i == eval(currentPage))
			result += "<li class='acactive-link'>";
		else
			result += "<li>";
		
		result += "<a class='pageButton' id='"+i+"' href='#'>" + i +"</a></li>";
		
	}
	result += "</ul></div>";
	return result;
}


//绑定事件
function eventBind() {
	//普通题点击翻页
	$('.pageButton').unbind().click(function () {
		var pageNo = $(this).attr('id');
		//展示试题
		generate(pageNo);
	});
}
function getQuestion(data){
var result = "";
for(var i = 0; i < data.length ; i++){
	var paper = data[i];
	result += "<tr><td>" + paper.name + "</td><td>" + paper.time + "</td><td style='display:none' class='examId'>" +
		paper.id + "</td><td><input type='radio' name='select'></td>" +
		"<td><input class='btn btn-info' type='button' value='试题内容' flag='false'></td></tr>";
	/*
	result += "<td><input class='btn btn-info' category='"+type+"' id='" +i+ "' type='button' " +
			" value ='选择'></td>"+
		"<td id='id' style='display:none'>" + question.id + "</td>";
	*/
	}
	return result;
}