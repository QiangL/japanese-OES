

$(function(){
	$("#signupForm").submit(function(e)
  {
    	var isValid=true;
		  var ifCookie = false;
      var thisVal1 = null;
      var thisVal2 = null;

      thisVal1=$("#userName").val();
      if(thisVal1.length<4 || thisVal1.length>25)
      {
        checkFalse("#userName");
        isValid=false;
      } 
      else
      {
        checkTrue("#userName");
        isValid=true;
      }

      thisVal2=$("#password").val();
      if(thisVal2.length<6 || thisVal2.length>25)
      {
        checkFalse("#password");
        isValid=false;
      } 
      else
      {
        checkTrue("#password");
        isValid=true;
      }

      $("#signupForm .validate").each(function()
      {
        var attr=$(this).attr('valid');
        if(!(attr=="true" || attr=="false"))
        {
        	checkFalse("#"+$(this).attr("id"));
          isValid=false;
        }
        if(!checkIsValid("#"+$(this).attr("id")))
        {
          checkAlert("#"+$(this).attr("id"));
          isValid=false;
        }
      })

  		if($("#rememeberMe").attr("checked") == "checked")
  		{
  			var userName = document.getElementById('userName');
  			var passWord = document.getElementById('password');
  			setCookie('password',passWord.value,14);
  			setCookie('username',userName.value,30);
  		}
      if(!isValid)
		  {
        e.preventDefault();
      }
  });
  $("#userName").keyup(function () 
  {
      var thisVal = null;
      thisVal=$(this).val();
      if(thisVal.length < 6 || thisVal.length > 25)
      {
        checkFalse("#userName");
      } 
	    else
      {
        checkTrue("#userName");
      }
  });
  $("#userName").change(function () 
  {
      var thisVal = null;
      thisVal=$(this).val();
      if(thisVal.length < 6 || thisVal.length > 25)
      {
        checkFalse("#userName");
      } 
      else
      {
        checkTrue("#userName");
      }
  });
  $("#password").keyup(function () 
  {
      var thisVal = null;
      thisVal = $(this).val();
      if (thisVal.length < 6 || thisVal.length > 25) 
      {
        checkFalse("#password");
      } 
      else 
      {
        checkTrue("#password");
      }
  });
  $("#password").change(function () 
  {
      var thisVal = null;
      thisVal = $(this).val();
      if (thisVal.length < 6 || thisVal.length > 25) 
      {
        checkFalse("#password");
      } 
      else 
      {
        checkTrue("#password");
      }
  });
})

