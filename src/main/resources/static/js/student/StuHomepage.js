var id =23;//$.cookie('acc_id');
var acc ="321";//$.cookie('account');
var user_name="xxx";//$.cookie('username');

$(function(){
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
				$("#username")[0].innerHTML=res.data[0].stu_name+"&nbsp;&nbsp;&nbsp;";
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

	












