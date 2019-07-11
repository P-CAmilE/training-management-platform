var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

    $.ajax({
        type: "post",
        url: "student/findSchool",
        contentType: false,
        processData: false,
        async : false,
        data: false,
        dataType: 'json',
        success: function (res) {
            //alert(res.type);
            var sch_id=$("#sch_id")[0];
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                sch_id.innerHTML+="<option value ='"+ data.sch_id+"'>"+data.sch_name+"</option>";
            }
        }
    });
    $("#create_student_info").submit(function() {
        $.ajax({
            url: "student/insert",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "stu_name": $("#stu_name").val(),
                "account": $("#account").val(),
                "password": $("#password").val(),
                "sch_id": $("#sch_id").val(),
                "user_type": "student"
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type == "fail") {
                    alert("添加学生失败");
                } else {
                    alert("添加学生成功");
                    window.location.href = "AdminCheckStudent.html";
                }
            }
        });
    });
});



