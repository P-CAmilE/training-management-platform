var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	getTeam(id);

	$("#search").submit(function(){
		$.ajax({
			url: "/team/find",
			type: "POST",
			dataType : "json",
			contentType: 'application/json;charset=UTF-8',
			data :  JSON.stringify({
				"search_name":$("#search_name").val()
			}),
			success:function(res){
				if(res.type=="fail"){
					alert("获取团队失败");
					return;
				}
				//动态生成团队表
				var team_table=$("#team_table")[0];
				team_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>团队名</th><th style='width: 100px'>计划名</th><th style='width: 100px'>项目名</th><th style='width: 100px'>学校</th><th style='width: 100px'>负责公司</th><th style='width: 200px'>操作</th><th style='width: 300px'>评分</th></tr>";
				for(var i=0;i<res.data.length;i++){
					var data = res.data[i];
					team_table.innerHTML+=" <tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.team_name+"</label></td><td><label>"+data.plan_name+"</label></td><td><label>"+data.pro_name+"</label></td><td><label>"+data.sch_name+"</label></td><td><label>"+data.com_name+"</label></td><td><a href='TeacherCheckTeam?team_id="+data.team_id+"'>查看</a></td><td><input id='score"+data.team_id+"' type='number' max='100' min='0' style='width: 80px;margin-left: 20px'><button class='studentinput bu' style='width: 80px;text-align: center;padding-left: 10px;margin-left: 50px' value="+data.team_id+">提交</button></td></tr>";
				}
			}
		});
	});
});	
//获取团队
function getTeam(user_id){
	$.ajax({
        url: "/team/findByTeacher",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"tea_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取团队失败");
				return;
			}
			//动态生成团队表
			var team_table=$("#team_table")[0];
			team_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>团队名</th><th style='width: 100px'>计划名</th><th style='width: 100px'>项目名</th><th style='width: 100px'>学校</th><th style='width: 100px'>负责公司</th><th style='width: 200px'>操作</th><th style='width: 300px'>评分</th></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				team_table.innerHTML+=" <tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.team_name+"</label></td><td><label>"+data.plan_name+"</label></td><td><label>"+data.pro_name+"</label></td><td><label>"+data.sch_name+"</label></td><td><label>"+data.com_name+"</label></td><td><a href='TeacherCheckTeam?team_id="+data.team_id+"'>查看</a></td><td><input id='score"+data.team_id+"' type='number' max='100' min='0' style='width: 80px;margin-left: 20px'><button class='studentinput bu' style='width: 80px;text-align: center;padding-left: 10px;margin-left: 50px' value="+data.team_id+">提交</button></td></tr>";
			}
        }
    });
	
	$("#team_table").on('click',".bu",function(){
	
	$.ajax({
        url: "/team/scoreForTeam",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"team_id":$(this).val(),
			"team_score":$("#score"+$(this).val()).val(),
			"tea_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("提交失败");
			}
			else{
				alert("提交成功");
			}
        }
    });
});
}