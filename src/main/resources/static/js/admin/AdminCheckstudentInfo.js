var id =21;//$.cookie('acc_id');
var acc ="user";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $.ajax({
        url : "student/find",
        type : "Post",
        contentType: 'application/json;charset=UTF-8',
        data : JSON.stringify({
            "stu_id":getUrlParam("stu_id")
        }),
        dataType : "json",
        async : false,
        success : function(res) {
            if(res.type=="fail"){
                alert("获取学生消息失败");
                return;
            }
            //初始化页面

            $("#modify")[0].href= "AdminModifyStudent?stu_id=" + getUrlParam("stu_id")+"&account="+ res.data[0].account;
            var data=res.data[0];
            $("#stu_name").append(data.stu_name);
            $("#account").append(data.account);
            $("#sch_name").append(data.sch_name);
        }
    });

    $("#delete").click(function(){
        var answer = confirm("确认要删除吗");
        if (answer)
        {
            $.ajax({
                url : "student/delete",
                type : "Post",
                contentType: 'application/json;charset=UTF-8',
                data : JSON.stringify({
                    "stu_id":getUrlParam("stu_id")
                }),
                dataType : "json",
                async : false,
                success : function(res) {
                    if(res.type === "fail"){
                        alert("删除失败");
                        return;
                    }
                    //初始化页面
                    alert("删除成功");
                    window.location.href="AdminCheckStudent.html";
                }
            });
        }
        else
            return false;
    });
});


//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}
