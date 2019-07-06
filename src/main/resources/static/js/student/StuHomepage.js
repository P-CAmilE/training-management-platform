$(function(){
	var id =$.cookie('acc_id');
	var acc =$.cookie('account');
	var user_name=$.cookie('username');
	
	//控件
	var img=$("#img");
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
					alert("获取企业消息失败");
					return;
				}
				//初始化页面
				$("#username")[0].innerHTML=res.data[0].stu_name+"&nbsp;&nbsp;&nbsp;";
				name.val(res.data[0].stu_name);
				img.attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id);
				//alert(res.data[0].com_name);
				//alert(res.type);
			}
		});
		
	$("#upimg").change(function(){
		var formdata=new FormData();
		formdata.append("file",$("#upimg")[0].files[0]);
		formdata.append("owner_id",id);
		formdata.append("type","image");
		formdata.append("storage_name","logo.jpg");
			
		//等待图片加载完成自动关闭流 
		//while(!com_img[0].complete){}
			
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
				img.attr("src", "resource/download?type=image&filename=logo.jpg&owner_id="+id+"&"+'?'+Math.random());
			}
		});	

	});
		
});

	












