$(function(){
    var account = "hlf";//$.cookie('stu_account');
    $.ajax({
        url: "/student/findProjecct",
        type: "POST",
        contentType: 'application/json;charset=UTF-8',
        data : JSON.stringify({
            "stu_account": account
        }),
        dataType: "json",
        //async: false,
        success : function(data) {
            if (data.type == "fail") {
                alert("获取实训消息失败")
            }
            $("#pro_name").innerHTML = data.pro_name;
            $("#pro_course").innerHTML = data.pro_course;
            $("#pro_time").innerHTML = data.pro_time;
            $("#pro_date").innerHTML = data.pro_date;
        }
    });
    // $("#upimg").change(function(){
    //     var formdata=new FormData();
    //     formdata.append("file",$("#upimg")[0].files[0]);
    //     formdata.append("account",account);
    //     formdata.append("storage_name","com_image.jpg");
    //     $.ajax({
    //         type: "post",
    //         url: "file/upload",
    //         contentType: false,
    //         processData: false,
    //         async : false,
    //         data: formdata,
    //         dataType: 'json',
    //         success: function (res) {
    //             //alert(res.type);
    //             $("#com_img").attr("src", "file/download?filename=com_image.jpg&account="+account);
    //         }
    //     });
    // });
});