$(function(){
	$("#pro_info").submit(function(){
		$.ajax({
		url : "project/insert",
		type : "Post",
		contentType: 'application/json;charset=UTF-8',
		data : JSON.stringify({
			"pro_name":$("#pro_name").val(),
			"pro_description":$("#pro_description").innerHTML,
			"pro_process":$("#pro_process").innerHTML,
			"pro_judge":$("#pro_judge").innerHTML,
			"pro_start":$("#pro_start").val(),
			"pro_end":$("#pro_end").val()
		}),
		dataType : "json",
		async : false,
		success : function(res) {
			if(res.type=="fail"){
				alert("创建失败");
			}
			else{
				alert("创建成功");
			}
		}
	});
	});
	
	
});