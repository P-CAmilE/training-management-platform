var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id+"&"+'?'+Math.random());
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	$.ajax({
			url : "teacher/find",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"tea_id":id
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取个人消息失败");
					return;
				}
				//初始化页面
				$("#name").val(res.data[0].tea_name);
				$("#acc").val(res.data[0].account);
				$("#unit").val(res.data[0].tea_unit);
			}
		});
		
	//上传图片
	$("#upimg").change(function(){
		var formdata=new FormData();
		formdata.append("file",$("#upimg")[0].files[0]);
		formdata.append("owner_id",id);
		formdata.append("type","image");
		formdata.append("storage_name","logo.jpg");
			
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
				$("#img").attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id+"&"+'?'+Math.random());
			}
		});	
	});
	
	$("#teacher_info").submit(function(){
		$.ajax({
		url : "teacher/update",
		type : "Post",
		contentType: 'application/json;charset=UTF-8',
		data : JSON.stringify({
			"new_tea_name": $("#name").val(),
			"new_tea_unit": $("#unit").val(),
			"new_password":$("#password1").val(),
			"tea_id":id
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