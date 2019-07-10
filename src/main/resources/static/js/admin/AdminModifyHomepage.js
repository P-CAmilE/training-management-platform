var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id+"&"+'?'+Math.random());
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
            if(res.type === "fail"){
                alert("获取管理员消息失败");
                return;
            }
            //初始化页面
            $("#acc").val(res.data[0].account);
            $("#email").val(res.data[0].admin_email);
            $("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
            //alert(res.data[0].com_name);
            //alert(res.type);
        }
    });

    //上传图片
    $("#upimg").change(function(){
        var formdata=new FormData();
        formdata.append("file",$("#upimg")[0].files[0]);
        formdata.append("owner_id",id);
        formdata.append("type","image");
        formdata.append("storage_name","logo.jpg");

        $.ajax({
            type: "post",
            url: "resource/upload",
            contentType: false,
            processData: false,
            async : false,
            data: formdata,
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                $("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id+"&"+'?'+Math.random());
            }
        });
    });

    $("#admin_info").submit(function(){
        var answer = confirm("确定修改？");
        if (answer) {
            if ($("#password").val() === $("#password1").val()) {
                $.ajax({
                    url: "admin/update",
                    type: "Post",
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify({
                        "new_admin_email": $("#email").val(),
                        "new_password": $("#password1").val(),
                        "admin_id": id
                    }),
                    dataType: "json",
                    async: false,
                    success: function (res) {
                        if (res.type === "fail") {
                            alert("保存失败");
                        } else {
                            alert("保存成功");
                            window.location.href = "AdminHomepage.html";
                        }
                    }
                });
            } else {
                alert("请确保两次输入的密码相同");
            }
        }
        else
            return false;

    });

});