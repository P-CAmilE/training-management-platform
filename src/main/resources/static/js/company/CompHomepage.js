$(function(){
	var id =1;// $.cookie('com_id');
	var account ="123";// $.cookie('com_account');
	var com_img=$("#com_img");
	var com_name=$("#com_name");
	var com_description=$("#com_description");
	$.ajax({
			url : "company/find",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				//"com_account": account
				"com_id":id
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取企业消息失败");
					return;
				}
				//初始化页面
				$("#name")[0].innerHTML=res.data[0].com_name+"&nbsp;&nbsp;&nbsp;";
				com_name.val(res.data[0].com_name);
				com_description.val(res.data[0].com_description);
				com_img.attr("src", "resource/download?type=image&filename=com_image.jpg&owner_id="+id);
				//alert(res.data[0].com_name);
				//alert(res.type);
			}
		});
		
	$("#upimg").change(function(){
		var formdata=new FormData();
		formdata.append("file",$("#upimg")[0].files[0]);
		formdata.append("owner_id",id);
		formdata.append("type","image");
		formdata.append("storage_name","com_image.jpg");
			
		//等待图片加载完成自动关闭流 然后页面卡死了...
		//while(!com_img[0].complete){}
			
		//如果加载完成可以换头像...
		if(com_img[0].complete)
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
		else{
			//...
		}
	});
		
	$("#com_info").submit(function(){
		$.ajax({
		url : "company/update",
		type : "Post",
		contentType: 'application/json;charset=UTF-8',
		data : JSON.stringify({
			"new_name": com_name.val(),
			"new_description":com_description.val(),
			"com_account":account
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

	














