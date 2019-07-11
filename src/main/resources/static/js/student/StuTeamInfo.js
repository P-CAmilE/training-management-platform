var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

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
					alert("获取个人消息失败");
					return;
				}
				//初始化页面
				var data=res.data[0];
				$("#team_name").append(data.team_name);
				$("#plan_name").append(data.plan_name);
				$("#project_name").append(data.pro_name);
				$("#time").append(data.start_time+"--"+data.end_time);
				$("#sch_name").append(data.sch_name);
				$("#com_name").append(data.com_name);
				for(var i=0;i<res.student.length;i++){
					$("#members").append(res.student[i].stu_name+" ");
				}
				$("#github").val(innerHTML=data.team_github);
				//$("#github").attr("href",data.team_github);
				//$("#team_score").append(data.team_score);
				//$("#stu_score").append(data.stu_score);
			}
		});
$("#send").click(function(){
	$.ajax({
			url : "team/update",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"new_team_github":$("#github").val(),
				"team_id":getUrlParam("team_id")
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("修改失败");
					return;
				}
			}
	});	
	
});	
	

});

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}
