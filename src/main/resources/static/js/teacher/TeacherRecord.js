var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	getLog(id);
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	
$("#log_table").on('click',".read",function(){
	$.ajax({
        url: "/log/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"log_id":$(this).val(),
        }),
        success:function(res){
			if(res.type=="fail"){
				return;
			}
			$("#context").val(res.data[0].log_context);
			$("#title").val(res.data[0].log_title);
        }
    });
	
});

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
//获取记录
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
			log_table.innerHTML="<tr id='log_table' style='border: none;background-color: rgb(238, 217, 215)'><td>标题</td><td>最新提交时间</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var data=res.data[i];
				log_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.log_title+"</td><td>"+renderTime(data.log_date)+"</td><td><button type='button' class='read' value='"+data.log_id+"' style='border-style: none;background: none;color: #b52e31' data-toggle='modal' aria-pressed='false' data-target='#exampleModal2'>查看</button></td></tr>";
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