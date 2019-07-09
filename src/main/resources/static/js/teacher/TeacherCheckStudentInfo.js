var id =22;//$.cookie('acc_id');
var acc ="bb";//$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "student/findByTeacher",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"stu_id":getUrlParam("stu_id")
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取学生信息失败");
					return;
				}
				var data = res.data[0];
				//初始化页面
				$("#stu_name").append(data.stu_name);
$("#stu_name").append(data.stu_name);
				$("#acc").append(data.account);
				$("#sch_name").append(data.sch_name);
				$("#sch_name").append(data.sch_name);
				$("#score").append(data.stu_score);
				$("#pro_name").append(data.pro_name);
				$("#plan_name").append(data.plan_name);
				$("#team_name").append(data.team_name);
				for(var i=0;i<res.teacher.length;i++){
					$("#tea_list").append(res.teacher[i].tea_name+" ");
				}
			}
		});
		
		var ws = new WebSocket("ws://localhost:8080/websocket/"+id);
	ws.onmessage = function(evt) {
		alert(evt.data);
    };

	//发送消息
	$("#send").submit(function(){
		 var json = [];
		 var row = {
			 from_id: id,
			 to_id: getUrlParam("stu_id"),
			 msg_title: $("#title").val(),
			 msg_context: $("#msg").val(),
			 msg_date:getNowFormatDate()
		}
		json.push(row);
		var jsonStr = JSON.stringify(json[0]);
		ws.send(jsonStr); 
	});	
		
});

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

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}