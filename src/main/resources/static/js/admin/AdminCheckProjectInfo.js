var id =21;//$.cookie('acc_id');
var acc ="user";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $.ajax({
        url : "project/findProjectDetail",
        type : "Post",
        contentType: 'application/json;charset=UTF-8',
        data : JSON.stringify({
            "pro_id":getUrlParam("pro_id")
        }),
        dataType : "json",
        async : false,
        success : function(res) {
            if(res.type=="fail"){
                alert("获取计划消息失败");
                return;
            }
            //初始化页面

            $("#modify")[0].href= "AdminModifyProject?pro_id=" + getUrlParam("pro_id");
            var data=res.data[0];
            var teachers = "";
            for(var i = 0;i < res.teacher.length;i ++){
                teachers += res.teacher[i].tea_name + "       ";
            }
            $("#pro_name").val(data.pro_name);
            $("#plan_name").val(data.plan_name);
            $("#pro_time").val(renderTime(data.start_time) + "--" + renderTime(data.end_time));
            $("#sch_name").val(data.sch_name);
            $("#com_name").val(data.com_name);
            $("#pro_description").append(data.pro_description);
            $("#tea_name").val(teachers);
            $("#pro_judge").val(data.pro_judge);
        }
    });

    $("#delete").click(function(){
        var answer = confirm("确认要删除吗");
        if (answer)
        {
            $.ajax({
                url : "project/delete",
                type : "Post",
                contentType: 'application/json;charset=UTF-8',
                data : JSON.stringify({
                    pro_id:getUrlParam("pro_id")
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
                    window.location.href="AdminCheckProject.html";
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
