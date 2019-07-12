var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	getStudent(id);
	
$("#search").submit(function(){
		$.ajax({
        url: "/student/findByTeacher",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"search_name":$("#search_name").val(),
			"tea_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取学生失败");
				return;
			}
			
			//动态生成项目表
			var stu_table=$("#stu_table")[0];
			stu_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>学生名</th><th style='width: 200px'>账号</th><th style='width: 100px'>学校</th><th style='width: 300px'>操作</th><th style='width: 300px'>评分</th></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				stu_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.stu_name+"</label></td><td><label>"+data.account+"</label></td><td><label>"+data.sch_name+"</label></td><td><a href='TeacherCheckStudentInfo?stu_id="+data.stu_id+"'>查看</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='TeacherCheckLog?stu_id="+data.stu_id+"'>日志</a></td><td><input id='score"+data.stu_id+"' value='"+data.stu_score+"'type='number' max='100' min='0' style='width: 80px;margin-left: 20px'><button class='studentinput bu' style='width: 80px;text-align: center;padding-left: 10px;margin-left: 50px' value="+data.stu_id+">提交</button></td></tr>";
			}
			}
    });
});
});	
//获取学生
function getStudent(user_id){
	$.ajax({
        url: "/student/findByTeacher",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"tea_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取学生失败");
				return;
			}
			//动态生成项目表
			var stu_table=$("#stu_table")[0];
			stu_table.innerHTML="<tr style='border: none;background-color: rgb(238, 217, 215)'><th style='width: 100px'>学生名</th><th style='width: 200px'>账号</th><th style='width: 100px'>学校</th><th style='width: 300px'>操作</th><th style='width: 300px'>评分</th></tr>";
			for(var i=0;i<res.data.length;i++){
				var data = res.data[i];
				stu_table.innerHTML+="<tr style='border: none;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td><label>"+data.stu_name+"</label></td><td><label>"+data.account+"</label></td><td><label>"+data.sch_name+"</label></td><td><a href='TeacherCheckStudentInfo?stu_id="+data.stu_id+"'>查看</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href='TeacherCheckLog?stu_id="+data.stu_id+"'>日志</a></td><td><input id='score"+data.stu_id+"' value='"+data.stu_score+"'type='number' max='100' min='0' style='width: 80px;margin-left: 20px'><button class='studentinput bu' style='width: 80px;text-align: center;padding-left: 10px;margin-left: 50px' value="+data.stu_id+">提交</button></td></tr>";
			}
			
        }
    });

	$("#stu_table").on('click',".bu",function(){
	$.ajax({
        url: "/student/scoreForStudent",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"stu_id":$(this).val(),
			"stu_score":$("#score"+$(this).val()).val(),
			"tea_id":id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("评分失败");
			}
			else{
				alert("评分成功");
			}
        }
    });
});
}