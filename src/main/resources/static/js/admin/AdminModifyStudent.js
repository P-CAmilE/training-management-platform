var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

var account=getUrlParam("account");
var stu_id=getUrlParam("stu_id");
$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $("#cancelModify")[0].href= "AdminCheckStudentInfo?stu_id=" + getUrlParam("stu_id");
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

            $("#account").val(account);
            var sch_id=$("#new_sch_id")[0];
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                sch_id.innerHTML+="<option value ='"+ data.sch_id+"'>"+data.sch_name+"</option>";
            }
        }
    });
    $("#modify_student_info").submit(function (){
        if ($("#new_password1").val() !== $("#new_password").val()) {
            alert("请保证两次输入的密码相同");
            return;
        } else {
            $.ajax({
                url: "student/update",
                type: "Post",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                    "stu_id":stu_id,
                    "new_stu_name": $("#new_stu_name").val(),
                    "new_password": $("#new_password").val(),
                    "new_sch_id": $("#new_sch_id").val(),
                    "user_type": "student"
                }),
                dataType: "json",
                async: false,
                success: function (res) {
                    if (res.type === "fail") {
                        alert("修改学生失败");
                    } else {
                        alert("修改学生成功");
                        window.location.href = "AdminCheckStudentInfo?stu_id=" + stu_id;
                    }
                }
            });
        }
    });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}