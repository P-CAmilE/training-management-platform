var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $.ajax({
        url : "team/findTeamDetail",
        type : "Post",
        contentType: 'application/json;charset=UTF-8',
        data : JSON.stringify({
            "team_id":getUrlParam("team_id")
        }),
        dataType : "json",
        async : false,
        success : function(res) {
            if(res.type=="fail"){
                alert("获取团队消息失败");
                return;
            }
            //初始化页面

            $("#modify")[0].href= "AdminModifyTeam?team_id=" + getUrlParam("team_id");
            var data=res.data[0];
            var teachers = "";
            var students = "";
            for(var i = 0;i < res.teacher.length;i ++){
                teachers += res.teacher[i].tea_name + "       ";
            }
            for(var i = 0;i < res.student.length;i ++){
                students += res.student[i].stu_name + "       ";
            }
            $("#team_name").val(data.team_name);
            $("#pro_name").val(data.pro_name);
            $("#plan_name").val(data.plan_name);
            $("#pro_time").val(renderTime(data.start_time) + "--" + renderTime(data.end_time));
            $("#sch_name").val(data.sch_name);
            $("#com_name").val(data.com_name);
            $("#stu_name").append(students);
            $("#tea_name").val(teachers);
        }
    });

    $("#delete").click(function(){
        var answer = confirm("确认要删除吗");
        if (answer)
        {
            $.ajax({
                url : "team/delete",
                type : "Post",
                contentType: 'application/json;charset=UTF-8',
                data : JSON.stringify({
                    team_id:getUrlParam("team_id")
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
                    window.location.href="AdminCheckTeam.html";
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

function renderTime(date) {
    var temp = new Date(date);
    var t = temp.getFullYear()+"."+(temp.getMonth()+1)+"."+temp.getDate();
    return t;
}

function getNowFormatDate() {//获取当前时间
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1<10? "0"+(date.getMonth() + 1):date.getMonth() + 1;
    var strDate = date.getDate()<10? "0" + date.getDate():date.getDate();
    var currentdate = date.getFullYear() + seperator1  + month  + seperator1  + strDate
        + " "  + date.getHours()  + seperator2  + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
