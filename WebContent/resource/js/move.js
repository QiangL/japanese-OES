function checkTrue(id) 
{
    $(id).attr("valid", "true");
    $(id).parent().find(".true").fadeIn(100);
    $(id).parent().find(".false").fadeOut(100);
}
function checkAlert(id) 
{
    var t = $(id).parent().find(".false");
    t.animate({ "margin-right": 5 }, 100).animate({ "margin-right": 0 }, 100);
}
function checkFalse(id)
{
    $(id).attr("valid", "false");
    $(id).parent().find(".false").fadeIn(100);
    $(id).parent().find(".true").fadeOut(100);
}
function checkIsValid(id) 
{
    if ($(id).attr("valid") == "true")
    {
        return true;
    } 
    else 
    {
        return false;
    }
}