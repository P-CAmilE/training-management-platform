var id=$.cookie('acc_id');
var acc =$.cookie('account');
//var user_name="???";//$.cookie('username');
$(function(){
	getLog(id);
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
$("#bu1").click(function(){
	$.ajax({
        url: "/log/insert",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"user_id":id,
			"log_title":$("#log_title").val(),
			"log_context":$("#log_context").val(),
			"log_date":getNowFormatDate()
			
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("发送日志失败");
			}
			else{
				alert("发送日志成功");
				$("#log_title").val("");
				$("#log_context").val("");
				getLog(id);
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
			"user_id":user_id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取历史日志失败");
				return;
			}
			//动态生成日志表
			var log_table=$("#log_table")[0];
			log_table.innerHTML="<tr style='border: none;background-color: grey'><td>标题</td><td>最新提交时间</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				log_table.innerHTML+="<tr><td>"+res.data[i].log_title+"</td><td>"+renderTime(res.data[i].log_date)+"</td><td><a href='StuModifyLog.html' style='color: #b52e31'>修改</a></td></tr>";
			}
			
        }
    });
}

function renderTime(date) {
  var dateee = new Date(date).toJSON();
  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
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
