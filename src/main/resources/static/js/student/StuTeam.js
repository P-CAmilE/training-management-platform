var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	getTeam(id);
});	
//获取团队
function getTeam(user_id){
	$.ajax({
        url: "/team/findByStudent",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"stu_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取团队失败");
				return;
			}
			//动态生成团队表
			var team_table=$("#team_table")[0];
			team_table.innerHTML="<tr style='border: none;background-color: grey'><td>团队名</td><td>计划名</td><td>项目名</td><td>学校</td><td>负责公司</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				team_table.innerHTML+="<tr style='border: none'><td>"+data.team_name+"</td><td>"+data.plan_name+"</td><td>"+data.plan_name+"</td><td>"+data.sch_name+"</td><td>"+data.com_name+"</td><td><a href='StuTeamInfo?team_id="+data.team_id+"' style='color: #b52e31'>查看</a> </td></tr>";
			}
			
        }
    });
}
