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
				$("#git_acc").val(res.data[0].github_account);
				$("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
				//alert(res.data[0].com_name);
				//alert(res.type);
			}
		});
		
	//上传图片
	$("#upimg").change(function(){
		var formdata=new FormData();
		formdata.append("file",$("#upimg")[0].files[0]);
		formdata.append("owner_id",id);
		formdata.append("type","image");
		formdata.append("storage_name","com_image.jpg");
			
		$.ajax({
			type: "post",
			url: "resource/upload",
			contentType: false,
			processData: false,
			async : false,
			data: formdata,
			dataType: 'json',
			success: function (res) {
				//alert(res.type);
				com_img.attr("src", "resource/download?type=image&filename=com_image.jpg&owner_id="+id+"&"+'?'+Math.random());
			}
		});	
	});
	
	$("#stu_info").submit(function(){
		$.ajax({
		url : "student/update",
		type : "Post",
		contentType: 'application/json;charset=UTF-8',
		data : JSON.stringify({
			"new_stu_name": $("#name").val(),
		    "new_github_account":$("#git_acc").val(),
			"new_password":$("#password1").val(),
			"stu_id":id
		}),
		dataType : "json",
		async : false,
		success : function(res) {
			if(res.type=="fail"){
				alert("保存失败");
			}
			else{
				alert("保存成功");
			}
		}
		});
	});
	
});