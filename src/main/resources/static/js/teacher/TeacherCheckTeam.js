var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	var members_id=new Array();
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	//获取队伍信息
	$.ajax({
			url : "team/findByTeacher",
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			data : JSON.stringify({
				"team_id":getUrlParam("team_id")
			}),
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取队伍消息失败");
					return;
				}
				//初始化页面
				var data=res.data[0];
				$("#team_name").append(data.team_name);
				$("#plan_name").append(data.plan_name);
				$("#pro_name").append(data.pro_name);
				$("#time").append(renderTime(data.start_time)+"--"+renderTime(data.end_time));
				$("#sch_name").append(data.sch_name);
				$("#com_name").append(data.com_name);
				for(var i=0;i<res.student.length;i++){
					members_id[i]=res.student[i].stu_id;
					$("#stu_list").append("<a href='TeacherCheckStudentInfo.html?stu_id"+members_id[i]+"' class='form-control1' style='height: 35px;width:250px;border: none' readonly>"+res.student[i].stu_name+"</a>");
				}
				$("#github")[0].innerHTML=data.team_github;
				$("#github").attr("href",data.team_github);
				$("#team_score").append(data.team_score);
				//$("#stu_score").append(data.stu_score);
			}
		});
		
	var ws = new WebSocket("ws://localhost:8080/websocket/"+id);
	ws.onmessage = function(evt) {
		
    };

	//发送消息
	$("#send").click(function(){
		 var json = [];
		 var row = {
			 from_id: id,
			 to_id: 0,
			 msg_title: $("#title").val(),
			 msg_context: $("#msg").val(),
			 msg_date:getNowFormatDate()
		}
		for(var i=0;i<members_id.length;i++){
			row.to_id=members_id[i];
			json.push(row);
			var jsonStr = JSON.stringify(json[0]);
			ws.send(jsonStr);
		}	 
		alert("发送成功");	 
	});	
	
	
	draw(-1);
	
	$("#stats").change(function(){
		draw($(this).val());	
	});
});
function renderTime(date) {
	var temp = new Date(date);
	var t = temp.getFullYear()+"."+(temp.getMonth()+1)+"."+temp.getDate();
	return t;
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

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}

//绘制条形图
function draw(week){
	$.ajax({
			url : "git/team_adc?team_id="+getUrlParam("team_id")+"&week="+week,
			type : "Post",
			contentType: 'application/json;charset=UTF-8',
			dataType : "json",
			async : false,
			success : function(res) {
				if(res.type=="fail"){
					alert("获取队伍消息失败");
					return;
				}
				var a = new Array();
				var d = new Array();
				var c = new Array();
				var member = new Array();
				for(var i=0;i<res.data.length;i++){
					data=res.data[i];
					a[i]=data.a;
					d[i]=data.d;
					c[i]=data.c;
					member[i]=data.username;
				}
					drawDiagram('canvas1',a,member);
					drawDiagram('canvas2',d,member);
					drawDiagram('canvas3',c,member);
			}
		});
		

}

function drawDiagram(canvas,data,member) {

          var xinforma = member;

          // 获取上下文
          var a_canvas = document.getElementById(canvas);
		  a_canvas.height=a_canvas.height;
          var context = a_canvas.getContext("2d");

          // 绘制背景
          var gradient = context.createLinearGradient(0,0,0,300);


         // gradient.addColorStop(0,"#e0e0e0");
          //gradient.addColorStop(1,"#ffffff");


          context.fillStyle = gradient;

          context.fillRect(0,0,a_canvas.width,a_canvas.height);

          var realheight = a_canvas.height-15;
          var realwidth = a_canvas.width-40;
          // 描绘边框
          var grid_cols = data.length + 1;
          var grid_rows = 4;
          var cell_height = realheight / grid_rows;
          var cell_width = realwidth / grid_cols;
          context.lineWidth = 1;
          context.strokeStyle = "#a0a0a0";

          // 结束边框描绘
          context.beginPath();
          // 准备画横线
          /*for(var row = 1; row <= grid_rows; row++){
            var y = row * cell_height;
            context.moveTo(0,y);
            context.lineTo(a_canvas.width, y);
          }*/

            //划横线
            context.moveTo(0,realheight);
            context.lineTo(realwidth,realheight);


            //画竖线
          context.moveTo(0,20);
          context.lineTo(0,realheight);
          context.lineWidth = 1;
          context.strokeStyle = "black";
          context.stroke();


          var max_v =0;

          for(var i = 0; i<data.length; i++){
            if (data[i] > max_v) { max_v =data[i]};
          }
          max_v = max_v * 1.1;
          // 将数据换算为坐标
          var points = [];
          for( var i=0; i < data.length; i++){
            var v= data[i];
            var px = cell_width * (i +1);
            var py = realheight - realheight*(v / max_v);
            //alert(py);
            points.push({"x":px,"y":py});
          }

          //绘制坐标图形
          for(var i in points){
            var p = points[i];
            context.beginPath();
            context.fillStyle="brown";
            context.fillRect(p.x,p.y,15,realheight-p.y);

            context.fill();
          }
          //添加文字
          for(var i in points)
          {  var p = points[i];
            context.beginPath();
            context.fillStyle="black";
            context.fillText(data[i], p.x + 1, p.y -10);
             context.fillText(xinforma[i],p.x + 1,realheight+12);
             context.fillText('小组组员',realwidth,realheight+12);
             context.fillText('数量',0,10);
              }
        }
