

$(document).ready(function(){
	var name = document.getElementById("name").innerHTML;
	var examContent = null;
	
	
	$.ajax({
		url:"/japanese-OES/exam/"+name+".xml",
    dataType: 'xml',
    content:"application/xml; charset=UTF-8",
		success:function(data){
			examContent = data;
			display(examContent,"general");
		}
	});
	
});

function display(content,type){
	var questions = $('#questions');
	questions.empty();
	if(type == 'general')
		$(content).find('generals').children().each(function(index,element){
			
			var name = $(element).find('description')[0].innerHTML;
			var id = $(element).attr('id');
			questions.append("<li><a href='#' qid='"+id+"'>"+name+"</a></li>");
		});
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
  function upload(){
    Recorder.upload({
      url:        "/japanese-OES/user/record",
      audioParam: "file",
      params:{"userId":userId.innerHTML,"qId":""},
      success: function(){
        alert("录音上传成功");
      }
    });
  }