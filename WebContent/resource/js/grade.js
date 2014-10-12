$(document).ready(function(){
	$('.clazzNumber').click(function(){
		var clazz = $(this).text();
		$.ajax({
			url:"/japanese-OES/teacher/students",
			data:{classNumber:clazz},
			dataType:"json",
			success:function(data){
				var studentList = $('#studentList');
				studentList.empty();
				for(var i = 0; i < data.length; i++){
					studentList.append("<li class='studentId' sid='"+data[i].userId+"'>"+data[i].userName+"</li>");
				}
				//更新列表后绑定监听事件
				$('.studentId').click(function(){
					var object = $(this);
					var id = object.attr('sid');
					var name = object.text();
					$('#sName').text(name);
					$('#sId').text(id);
					//向后台请求录音数据
					$.ajax({
						url:"/japanese-OES/teacher/getRecords",
						dataType:'json',
						data:{userId:id},
						success:function(data){
							var recordsTable = $('#records');
							recordsTable.empty();
							for(var i = 0; i < data.length; i++){
								var appendStr = "";
								appendStr = "<tr><td>"+i+"</td>"
										+"<td>"+data[i].questionName+"</td>"
										+"<td><button class='btn' id='"+data[i].id+"' onclick='getRecord(id)'>试听</button></td>"
										
								var status = data[i].status;
								if(status == 0)//未评分
									appendStr += "<td><input type='text' placeholder='打分' class='doscore' /></td>"
										+"<td><button class='btn btn-warning' id='"+data[i].id+"'>确认</button></td></tr>";
								else{//评分过
									appendStr += "<td><input type='text' placeholder='打分' class='doscore' value='"+data[i].score+"' disabled='true'/></td>"
										+"<td><button class='btn btn-warning' id='"+data[i].id+"' disabled='true'>已评分</button></td></tr>";
								}
								recordsTable.append(appendStr);
							}
							//为打分按钮绑定事件
							$('.btn-warning').click(function(){
								var rid = $(this).attr('id');//录音文件id
								var score = $(this).parent().prev().find('input')[0].value;
								
								$.get("/japanese-OES/teacher/score",
										{recordId:rid,score:score},
									function(data){
									if(data == "success")
										alert("评分成功");
									else
										alert("评分出错");
								});
							});
							
						}
					});
				});
			}
		});
	});
});

//获取录音文件的url 并更新播放器状态
function getRecord(rid){
	var url = "/japanese-OES/";
	var player = document.getElementById("player");
	$.ajax({
		url:url+"teacher/getRecord",
		data:{rid:rid},
		success:function(data){
			alert(data);
			player.src=data.toString();
		},
	});
}



