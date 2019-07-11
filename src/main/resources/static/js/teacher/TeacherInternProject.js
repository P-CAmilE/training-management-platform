var id =$.cookie('acc_id');
var acc=$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	getProject(id);

	$("#search").submit(function(){
		$.ajax({
			url: "/project/find",
			type: "POST",
			dataType : "json",
			contentType: 'application/json;charset=UTF-8',
			data :  JSON.stringify({
				"search_name":$("#search_name").val()
			}),
			success:function(res){
				if(res.type=="fail"){
					alert("获取项目失败");
					return;
				}

				//动态生成项目表
				var project_table=$("#project_table")[0];
				project_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>项目名</th><th style='width: 200px'>计划名</th><th style='width: 200px'>项目时间</th><th style='width: 100px'>学校</th><th style='width: 200px'>负责公司</th><th style='width: 200px'>操作</th></tr>";
				for(var i=0;i<res.data.length;i++){
					var data = res.data[i];
					project_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.pro_name+"</label></td><td><label>"+data.plan_name+"</label></td><td><label>"+renderTime(data.start_time)+"--"+renderTime(data.end_time)+"</label></td><td><label>"+data.sch_name+"</label></td><td><label>"+data.com_name+"</label></td><td><a href='TeacherCheckProjectInfo?pro_id="+data.pro_id+"'>查看</a></td></tr>";
				}
			}
		});
	});
});	
//获取项目
function getProject(user_id){
	$.ajax({
        url: "/project/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取团队失败");
				return;
			}
			//动态生成项目表
			var project_table=$("#project_table")[0];
			project_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>项目名</th><th style='width: 200px'>计划名</th><th style='width: 200px'>项目时间</th><th style='width: 100px'>学校</th><th style='width: 200px'>负责公司</th><th style='width: 200px'>操作</th></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				project_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.pro_name+"</label></td><td><label>"+data.plan_name+"</label></td><td><label>"+renderTime(data.start_time)+"--"+renderTime(data.end_time)+"</label></td><td><label>"+data.sch_name+"</label></td><td><label>"+data.com_name+"</label></td><td><a href='TeacherCheckProjectInfo?pro_id="+data.pro_id+"'>查看</a></td></tr>";
			}
			
        }
    });
}

function renderTime(date) {
    var temp = new Date(date);
    var t = temp.getFullYear()+"."+(temp.getMonth()+1)+"."+temp.getDate();
    return t;
}