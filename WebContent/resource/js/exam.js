var examContent = null;

$(document).ready(function(){
	var name = document.getElementById("name").innerHTML;
	
	$.ajax({
		url:"/japanese-OES/exam/"+name+".xml",
    dataType: 'xml',
    async: false,
		success:function(data){
			examContent = data;
			display(examContent,"general");
    }
  });
  //showGeneralQuestion();
  $("#general_question").click(showGeneralQuestion);
  $("#img_question").click(showImgQuestion);
	
});

function showImgQuestion() {
  $("#general_question").removeClass("active");
  $("#img_question").addClass("active");
  display(examContent, "img");
  $("#img_src").css("display", "block");
}
function showGeneralQuestion() {
  $("#general_question").addClass("active");
  $("#img_question").removeClass("active");
  $("#img_src").css("display", "none");
  display(examContent, "general");
}

function display(content,type){
	var questions = $('#questions');
	questions.empty();
  if (type == 'general') {
    $(content).find('generals').children().each(function(index,element){

			var name = $(element).find('description')[0].innerHTML;
			var id = $(element).attr('id');
			questions.append("<li><a class='question_href' href='#' qid='"+id+"'>"+name+"</a></li>");
		});
  } else if (type == "img") {
    $(content).find('imgs').children().each(function(index,element){
			
			var name = $(element).find('description')[0].innerHTML;
			var id = $(element).attr('id');
			questions.append("<li><a class='question_href' href='#' qid='"+id+"'>"+name+"</a></li>");
		});
  }
  $(".question_href").live("click", function () {
    var type = $("#general_question").hasClass("active") ? "general" : "img";
    var basePath = type == "general" ? "/japanese-OES/question/generalQuestion/" : "/japanese-OES/question/imgQuestion/";
    var qid = $(this).attr("qid");
    $("#qid_input").attr("value", qid);
    if (type === "general") {
      $("#" + type + "_src").attr("src", basePath + $(examContent).find("#" + qid).find("name").text());
    } else {
      $("#" + type + "_src").attr("src", basePath + $(examContent).find("#" + qid).find("name").text());
      var audioPath = $(examContent).find("#" + qid).find("resource").text();
      $("#general_src").attr("src",basePath+audioPath);
    }
    
  })
		
}

//录音控制

function timecode(ms) {
    var hms = {
      h: Math.floor(ms/(60*60*1000)),
      m: Math.floor((ms/60000) % 60),
      s: Math.floor((ms/1000) % 60)
    };
    var tc = []; // Timecode array to be joined with '.'

    if (hms.h > 0) {
      tc.push(hms.h);
    }

    tc.push((hms.m < 10 && hms.h > 0 ? "0" + hms.m : hms.m));
    tc.push((hms.s < 10  ? "0" + hms.s : hms.s));

    return tc.join(':');
  }


  Recorder.initialize({
    swfSrc: "/japanese-OES/flash/recorder.swf",
    flashContainer:document.getElementById("container")
  });

  function record(){
    Recorder.record({
      start: function(){
        alert("recording starts now. press stop when youre done. and then play or upload if you want.");
      },
      progress: function(milliseconds){
        document.getElementById("time").innerHTML = timecode(milliseconds);
      }
    });
  }
  
  function play(){
    Recorder.stop();
    Recorder.play({
      progress: function(milliseconds){
        document.getElementById("time").innerHTML = timecode(milliseconds);
      }
    });
  }
  
  function stop(){
    Recorder.stop();
  }
  
  
  var userId = document.getElementById("userId");
  function upload() {
    var qid = $("#qid_input").attr("value");
    if (qid == undefined) {
      alert("请先选择题目");
      return;
    }
    Recorder.upload({
      url:        "/japanese-OES/user/record",
      audioParam: "file",
      params:{"userId":userId.innerHTML,"qId":qid},
      success: function(){
        alert("录音上传成功");
      }
    });
  }