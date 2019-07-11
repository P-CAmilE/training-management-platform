var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

    $.ajax({
        type: "post",
        url: "teacher/findUnit",
        contentType: false,
        processData: false,
        async : false,
        data: false,
        dataType: 'json',
        success: function (res) {
            //alert(res.type);
            var tea_unit=$("#tea_unit")[0];
            for(var i=0;i<res.company.length;i++){
                var data = res.company[i];
                tea_unit.innerHTML+="<option value ='"+ data.com_name+"'>"+data.com_name+"</option>";
            }
            for(var i=0;i<res.school.length;i++){
                var data = res.school[i];
                tea_unit.innerHTML+="<option value ='"+ data.sch_name+"'>"+data.sch_name+"</option>";
            }
        }
    });
    $("#create_teacher_info").submit(function() {
        $.ajax({
            url: "teacher/insert",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "tea_name": $("#tea_name").val(),
                "account": $("#account").val(),
                "password": $("#password").val(),
                "tea_unit": $("#tea_unit").val(),
                "user_type": "teacher"
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type == "fail") {
                    alert("添加教师失败");
                } else {
                    alert("添加教师成功");
                    window.location.href = "AdminCheckTeacher.html";
                }
            }
        });
    });
});



