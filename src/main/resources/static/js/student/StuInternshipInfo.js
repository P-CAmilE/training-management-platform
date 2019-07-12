var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "project/findProjectDetail",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"pro_id":getUrlParam("pro_id")
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取项目信息失败");
					return;
				}
				//初始化页面
				var data=res.data[0];
				$("#plan_name").append(data.plan_name);
				$("#plan_name").append(data.plan_name);
				$("#project_name").append(data.pro_name);
				$("#time").append(renderTime(data.start_time)+"--"+renderTime(data.end_time));
				$("#pro_judge").append(data.pro_judge);
				$("#sch_name").append(data.sch_name);
				$("#context").append(data.pro_description);
				for(var i=0;i<res.teacher.length;i++){
					$("#teacher").append(res.teacher[i].tea_name+" ");
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

function renderTime(date) {
	var temp = new Date(date);
	var t = temp.getFullYear()+"."+(temp.getMonth()+1)+"."+temp.getDate();
	return t;
}
