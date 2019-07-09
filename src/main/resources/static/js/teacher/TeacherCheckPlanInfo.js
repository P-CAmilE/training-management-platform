var id =22;//$.cookie('acc_id');
var acc ="bb";//$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "plan/findPlanDetail",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"plan_id":getUrlParam("plan_id")
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取计划信息失败");
					return;
				}
				var data = res.data[0];
				//初始化页面
				$("#plan_name").append(data.plan_name);
				$("#course").append(data.course_name);
				$("#course_score").append(data.course_score);
				$("#time").append(data.start_time+"--"+data.end_time);
				$("#com_name").append(data.com_name);
				$("#sch_name").append(data.sch_name);
				$("#joined_class").val(data.joined_class);
				$("#plan_description").val(data.plan_description);
				
				for(var i=0;i<res.teacher.length;i++){
					$("#tea_list").append(res.teacher[i].tea_name+" ");
				}
			}
		});
		
});

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}