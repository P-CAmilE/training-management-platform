var id =23;//$.cookie('acc_id');
var acc ="321";//$.cookie('account');
var user_name="xxx";//$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=user_name+"&nbsp;&nbsp;&nbsp;";
	getProject(id);
});	
//获取团队
function getProject(user_id){
	$.ajax({
        url: "/project/findByStudent",
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
			var project_team=$("#project_team")[0];
			project_team.innerHTML="<tr style='border: none;background-color: grey'><td>项目名</td><td>计划名</td><td>实训时间</td><td>学校</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				team_table.innerHTML+="<tr style='border: none'><td>"+data.pro_name+"</td><td>"+data.plan_name+"</td><td>"+data.start_time+"--"+data.end_time+"</td><td>"+data.sch_name+"</td><td><a href='"+"StuInternshipInfo.html"+"' style='color: #b52e31'>查看</a> </td></tr>";
			}
			
        }
    });
}
