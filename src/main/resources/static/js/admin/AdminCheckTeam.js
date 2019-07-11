var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    getTeam(id);
});
//获取
function getTeam(user_id){
    var team_table=$("#team_table")[0];
    team_table.innerHTML="<tr style=\"border: none;background-color: rgb(238, 217, 215)\">" +
        "<th style=\"width: 200px\">团队名</th>" +
        "<th style=\"width: 200px\">计划名</th>" +
        "<th style=\"width: 200px\">项目名</th>" +
        "<th style=\"width: 200px\">学校</th>" +
        "<th style=\"width: 200px\">负责公司</th>" +
        "<th style=\"width: 100px\">操作</th> </tr>";
    $.ajax({
        url: "/team/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({

        }),
        success:function(res){
            if(res.type === "fail"){
                alert("获取团队失败");
                return;
            }
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                team_table.innerHTML+="<tr style='border: none; background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.team_name+"</td><td>"+data.plan_name+"</td><td>"+data.pro_name+
                    "</td><td>"+data.sch_name+"</td><td>"+data.com_name+"</td><td><a href='AdminCheckTeamInfo?team_id="+data.team_id+"' style='color: #b52e31'>查看</a></td></tr>";
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
