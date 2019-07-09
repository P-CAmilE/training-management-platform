var id =22;//$.cookie('acc_id');
var acc ="bb";//$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "teacher/find",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"stu_id":id
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取个人消息失败");
					return;
				}
				//初始化页面
				$("#name").append(res.data[0].tea_name);
				$("#acc").append(res.data[0].account);
				$("#unit").append(res.data[0].tea_unit);
				$("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
			}
		});
		
});