$(function(){
	//获取url参数
	(function ($) {
		$.getUrlParam = function (name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null) return unescape(r[2]); return null;
			}
	})(jQuery);

	
	var account ="123";// $.cookie('com_account');
	var com_name="asd";//$.cookie('com_name');
	
	var team_id = $.getUrlParam('team_id');

	
	
	//var team_id = getQueryString('team_id');//从url中获取参数
	
	$.ajax({
			url : "team/find",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"team_id": team_id
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"||res.data.length==0){
					alert("获取队伍消息失败");
					return;
				}
				//初始化页面
				$("#team_name").val(res.data[0].team_name);
				$("#tea_description").val(res.data[0].tea_description);
				
			}
		});
		
		$("#name")[0].innerHTML=com_name+"&nbsp;&nbsp;&nbsp;"
		

        alert(str);
		
});



