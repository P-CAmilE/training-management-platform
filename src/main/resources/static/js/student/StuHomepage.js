var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "student/find",
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
				$("#name").val(res.data[0].stu_name);
				$("#acc").val(res.data[0].account);
				$("#git_acc")[0].innerHTML=res.data[0].github_account;
				$("#git_acc").attr("href",res.data[0].github_account);
				$("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
				//alert(res.data[0].com_name);
				//alert(res.type);
			}
		});
		
});

	












