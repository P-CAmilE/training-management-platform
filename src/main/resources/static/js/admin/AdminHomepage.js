var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $.ajax({
        url : "admin/find",
        type : "Post",
        contentType: 'application/json;charset=UTF-8',
        data : JSON.stringify({
            "admin_id":id
        }),
        dataType : "json",
        async : false,
        success : function(res) {
            if(res.type=="fail"){
                alert("获取管理员消息失败");
                return;
            }
            //初始化页面
            $("#acc").append(res.data[0].account);
            $("#email").append(res.data[0].admin_email);
            $("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
        }
    });

});