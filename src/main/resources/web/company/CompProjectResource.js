$(function(){
	var account ="123";// $.cookie('com_account');
	var com_id ="1";
	$.ajax({
			url : "company/find",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"com_account": account
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
			}
		});
	
	$("#template_file").submit(function(){
		var formdata=new FormData();
		formdata.append("file",$("#file")[0].files[0]);
		formdata.append("account",account);
		
		$.ajax({
			type: "post",
			url: "file/upload",
			contentType: false,
			processData: false,
			async : false,
			data: formdata,
			dataType: 'json',
			success: function (res) {

			}
		});
	});
	
	$("#document_file").submit(function(){
		var formdata=new FormData();
		formdata.append("file",$("#file1")[0].files[0]);
		formdata.append("account",account);
		//formdata.append("storage_name","");
		
		$.ajax({
			type: "post",
			url: "file/upload",
			contentType: false,
			processData: false,
			async : false,
			data: formdata,
			dataType: 'json',
			success: function (res) {
				//alert(res.type);
			}
		});	
		
	});
	
});