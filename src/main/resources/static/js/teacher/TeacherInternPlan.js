var id =22;//$.cookie('acc_id');
var acc ="bb"//$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	getTeam(id);
});	
//获取计划
function getTeam(user_id){
	$.ajax({
        url: "/plan/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取计划失败");
				return;
			}
			//动态生成计划表
			var plan_table=$("#plan_table")[0];
			plan_table.innerHTML="<tr style='border: none;background-color: grey'><th style='width: 100px'>计划名称</th><th style='width: 200px'>实践课程/代码</th><th style='width: 100px'>学分</th><th style='width: 200px'>实训时间</th><th style='width: 100px'>学校</th><th style='width: 100px'>负责公司</th><th style='width: 200px'>操作</th></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				plan_table.innerHTML+="<tr style='border: none'><td><label>"+data.plan_name+"</label></td><td><label>"+data.course_name+"</label></td><td><label>"+data.course_score+"</label></td><td><label>"+data.start_time+"--"+data.end_time+"</label></td><td><label>"+data.sch_name+"</label></td><td><label>"+data.com_name+"</label></td><td><a href='TeacherCheckPlanInfo?plan_id="+data.plan_id+"'>查看</a></td></tr>";
			}
			
        }
    });
}