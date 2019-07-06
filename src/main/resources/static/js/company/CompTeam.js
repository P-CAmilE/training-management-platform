$(function(){
	var account ="123";// $.cookie('com_account');
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
				$("#name")[0].innerHTML=res.data[0].com_name+"&nbsp;&nbsp;&nbsp;"
			}
		});
		
		var str = getQueryString("team_id");
        alert(str);
		
});



//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}

