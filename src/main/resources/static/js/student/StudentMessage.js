var id=$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');
var ws = new WebSocket("ws://localhost:8080/websocket/"+id);

$(function(){
	
	getMessage(id);
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

//查看消息内容
$("#msg_table").on('click',".read",function(){
	
	$.ajax({
        url: "/message/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"msg_id":$(this).val()
        }),
        success:function(res){
			if(res.type=="fail"){
				return;
			}
			var msg_context=$("#msg_context");
			msg_context.val(res.data[0].msg_context);
        }
    });
	$.ajax({
		url: "/message/update",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"msg_id":$(this).val(),
			"new_is_read":1
        }),
        success:function(res){
			if(res.type=="fail"){
				return;
			}
			getMessage(id);
        }
    });
});

	
	ws.onmessage = function(evt) {
		getMessage(id);
	};
	
	ws.onerror = function(evt) {
		alert("连接失败");
	};
});
function getMessage(id){
	$.ajax({
        url: "/message/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"to_id":id,
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取历史消息失败");
				return;
			}
			var msg_table=$("#msg_table")[0];
			msg_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><td>发送者</td><td>消息标题</td><td>发送时间</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var c="";
				if(res.data[i].is_read==0)
					c="新！"
				msg_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+res.data[i].tea_name+"</td><td>"
				+c+res.data[i].msg_title+"</td><td>"+renderTime(res.data[i].msg_date)
				+"</td><td><button type='button' class='read' value='"+res.data[i].msg_id+"' style='border-style: none;background: none;color: #b52e31' data-toggle='modal' aria-pressed='false' data-target='#exampleModal'>查看</button></td></tr>";
			}
        }
    });
}

function renderTime(date) {
  var dateee = new Date(date).toJSON();
  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
}