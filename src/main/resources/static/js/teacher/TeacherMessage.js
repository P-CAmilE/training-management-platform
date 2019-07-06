$(function(){
	var account ="123";// $.cookie('tea_account');
	
	var tea_name="<script>alert('a')</script>";//$.cookie('tea_name');//教师用户名
	$("#name")[0].innerHTML=tea_name+"&nbsp;&nbsp;&nbsp;";

	var team_members=['a','s','d','f'];//团队成员应通过服务器返回

	
    var ws = new WebSocket("ws://localhost:8080/websocket/"+account);
	
	ws.onerror = function(){
        alert(连接失败);
    };
	
	$("#bu1").click(function(){
		var json = [];
        var row = {
			from: account,
			to: null,
			msg: $("#msg").val()
			};
		for(var i=0;i<team_members.length;i++){
			row.to=team_members[i];
			json.push(row);
			var jsonStr = JSON.stringify(json[0]);
			ws.send(jsonStr);
		}
        alert("发送成功");
		$("#msg").val("");
 
	});
	
	var ws2 = new WebSocket("ws://localhost:8080/broadcast/"+account);
	
	ws2.onerror = function(){
        alert(连接失败);
    };
	$("#bu2").click(function(){
		var json = [];
        var row = {
			from: account,
			msg: $("#broadcast_msg").val()
			};
		json.push(row);
		var jsonStr = JSON.stringify(json[0]);
		ws2.send(jsonStr);
        alert("发送成功");
		$("#broadcast_msg").val("");
 
	});
	
	
});