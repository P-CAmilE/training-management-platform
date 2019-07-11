var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

var account=getUrlParam("account");
var tea_id=getUrlParam("tea_id");
$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $("#cancelModify")[0].href= "AdminCheckTeacherInfo?tea_id=" + getUrlParam("tea_id");

    $.ajax({
        type: "post",
        url: "teacher/findUnit",
        contentType: false,
        processData: false,
        async: false,
        data: false,
        dataType: 'json',
        success: function (res) {
            //alert(res.type)
            $("#account").val(account);
            var tea_unit = $("#new_tea_unit")[0];
            for (var i = 0; i < res.company.length; i++) {
                var data = res.company[i];
                tea_unit.innerHTML += "<option value ='" + data.com_name + "'>" + data.com_name + "</option>";
            }
            for (var i = 0; i < res.school.length; i++) {
                var data = res.school[i];
                tea_unit.innerHTML += "<option value ='" + data.sch_name + "'>" + data.sch_name + "</option>";
            }
        }
    });
    $("#modify_teacher_info").submit(function (){
        if ($("#new_password1").val() !== $("#new_password").val()) {
            alert("请保证两次输入的密码相同");
            return;
        } else {
            $.ajax({
                url: "teacher/update",
                type: "Post",
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify({
                "tea_id":tea_id,
                "new_tea_name": $("#new_tea_name").val(),
                "new_password": $("#new_password").val(),
                "new_tea_unit": $("#tea_unit").val(),
                "user_type": "teacher"
                }),
                dataType: "json",
                async: false,
                success: function (res) {
                    if (res.type === "fail") {
                        alert("修改教师失败");
                    } else {
                        alert("修改教师成功");
                        window.location.href = "AdminCheckTeacherInfo?tea_id=" + tea_id;
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