var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
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
				alert("获取项目失败");
				return;
			}
			//动态生成项目表
			var project_table=$("#project_table")[0];
			project_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><td>项目名</td><td>计划名</td><td>实训时间</td><td>学校</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				project_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.pro_name+"</td><td>"+data.plan_name+"</td><td>"+renderTime(data.start_time)+"--"+renderTime(data.end_time)+"</td><td>"+data.sch_name+"</td><td><a href='StuInternshipInfo.html?pro_id="+data.pro_id+"' style='color: #b52e31'>查看</a> </td></tr>";
			}
			
        }
    });
}

function renderTime(date) {
	var temp = new Date(date);
	var t = temp.getFullYear()+"."+(temp.getMonth()+1)+"."+temp.getDate();
	return t;
}