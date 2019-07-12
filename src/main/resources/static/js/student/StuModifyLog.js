var id=$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');
$(function(){
	getLog(id);
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

$("#send").click(function(){
	$.ajax({
        url: "/log/update",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"log_id":getUrlParam("log_id"),
			"new_log_title":$("#title").val(),
			"new_log_context":$("#context").val(),
			"new_log_date":getNowFormatDate()
			
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("修改日志失败");
			}
			else{
				alert("修改日志成功");
				window.location.href="StuLog";
			}
        }
    });	
});

});

//获取历史日志
function getLog(user_id){
	$.ajax({
        url: "/log/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"log_id":getUrlParam("log_id")
			
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取历史日志失败");
				return;
			}
			$("#title").val(res.data[0].log_title);
			$("#context").val(res.data[0].log_context);
			
        }
    });
}

function renderTime(date) {
  var dateee = new Date(date).toJSON();
  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
}


//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}

function getNowFormatDate() {//获取当前时间
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1<10? "0"+(date.getMonth() + 1):date.getMonth() + 1;
	var strDate = date.getDate()<10? "0" + date.getDate():date.getDate();
	var currentdate = date.getFullYear() + seperator1  + month  + seperator1  + strDate
			+ " "  + date.getHours()  + seperator2  + date.getMinutes()
			+ seperator2 + date.getSeconds();
	return currentdate;
}
