$(document).ready(function() {
	$('.changeContentOn .change').click(function() {
		var role = $(this).attr("id");
		var index = $(this).attr("index");
		if(role == "t"){
			var id = $('#t_teacherId_'+index).text();
			$('#teacherId').val(id);
		}
		
		else{
			var id = $('#t_userId_' + index).text();
			$('#userId').val(id);
		}
		$('.changeContentOff').slideDown();
	});

	//点击删除事件 
	$('.changeContentOn .delete').click(function() {
		var index = $(this).attr("index");
		var id;
		var confirmed = confirm("Y/N");
		var role = $(this).attr("id");
		if (confirmed == true) {
			var url;
			if(role == "s"){
				url = "/japanese-OES/admin/delete/student";
				id  = $('#t_userId_' + index).text();
			}
			else{
				url = "/japanese-OES/admin/delete/teacher";
				id = $('#t_teacherId_' + index).text();
			}
			$.get(url, {
				"id" : id
			}, function() {
				$('.changeContentOn[id=tr_' + index + ']').remove();
				alert("删除成功");
			});
		}
	});
	//确认修改学生信息响应事件
	$('#changeConfirm').click(function() {
		var id = $('#changeForm input[id=userId]').val();
		var name = $('#changeForm input[id=userName]').val();
		var classNumber = $('#changeForm input[id=classNumber]').val();
		$.ajax({
			type : "get",
			url : "/japanese-OES/admin/update/student",
			data : {
				"userId" : id,
				"userName" : name,
				"classNumber" : classNumber
			},
			async : true,// 同步
			success : function(data) {
				alert("修改成功");
			},
		});
		// alert(id + name + classNumber);
	});
	
	$('#changeConfirmTeacher').click(function(){
		var id = $('#changeForm input[id=teacherId]').val();
		var name = $('#changeForm input[id=teacherName]').val();
		$.ajax({
			type : "get",
			url : "/japanese-OES/admin/update/teacher",
			data : {
				"teacherId" : id,
				"teacherName" : name,
			},
			async : true,// 同步
			success : function(data) {
				alert("修改成功");
			},
		});
	});
	
});

//获取教师信息 动态展示到页面上
//function getTeacherData(page){
//	var json;
//	$.ajax({
//		url:"/japanese-OES/admin/teachers",
//		data:{"page":page},
//		async:false,
//		success:function(data){
//		json = eval('('+data+')');
//	}
//	});
//	var totalPage = json.totalPageNumber;
//	var currentPage = json.currentPage;
//	var teachers = json.teachers;
//	
//	$.each(teachers,function(index,object){
//		//alert(index +"   " + object.teacherId);
//		$('#t_teacher').empty();//
//		$('#t_teacher').append("<tr class='changeContentOn' id='tr_" + index +"'>"
//				+"<td id='t_teacherId_"+index+"'>" + object.teacherId + "</td>"
//				+"<td id='t_teacherName_"+index+"'>" + object.teacherName + "</td>"
//				+"<"
//				+"<td><input type='button' id='teacher' index='"+index+"'" +
//						" value='修改'class='btn change btn-primary btn-medium'> " +
//						"</td><td>" +
//						"<input type='button' id='teacher' index='"+index+"'" +
//						"value='删除'class='btn delete btn-primary btn-medium'></td>" 
//						);
//	});
//}