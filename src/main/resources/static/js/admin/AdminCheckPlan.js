var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    getPlan(id);
});
//获取
function getPlan(user_id){
    var plan_table=$("#plan_table")[0];
    plan_table.innerHTML="<tr style=\"border: none;background-color: grey\">" +
        "<th style=\"width: 200px\">计划名称</th>" +
        "<th style=\"width: 200px\">实践课程/代码</th>" +
        "<th style=\"width: 100px\">学分</th>" +
        "<th style=\"width: 200px\">实训时间</th>" +
        "<th style=\"width: 200px\">学校</th>" +
        "<th style=\"width: 200px\">负责公司</th>" +
        "<th style=\"width: 100px\">操作</th> </tr>";
    $.ajax({
        url: "/plan/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({

        }),
        success:function(res){
            if(res.type === "fail"){
                alert("获取计划失败");
                return;
            }
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                plan_table.innerHTML+="<tr style='border: none'><td>"+data.plan_name+"</td><td>"+data.course_name+"</td><td>"+data.course_score+
                    "</td><td>"+renderTime(data.start_time)+"--"+renderTime(data.end_time)+"</td><td>"+data.sch_name+"</td><td>"+data.com_name+"</td><td><a href='AdminCheckPlanInfo?plan_id="+data.plan_id+"' style='color: #b52e31'>查看</a></td></tr>";
            }
        }
    });
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
