var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    getTeacher(id);
});
//获取教师
function getTeacher(user_id){

    var student_table=$("#student_table")[0];
    student_table.innerHTML="<tr style=\"border: none;background-color: grey\">" +
        "                                    <th style=\"width: 100px\">学生名</th>" +
        "                                    <th style=\"width: 200px\">账号</th>" +
        "                                    <th style=\"width: 200px\">学校</th>" +
        "                                    <th style=\"width: 150px\">操作</th>" +
        "                                </tr>";

    $.ajax({
        url: "/student/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({

        }),
        success:function(res){
            if(res.type=="fail"){
                alert("获取学生失败");
                return;
            }

            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                student_table.innerHTML+="<tr style='border: none'><td>"+data.stu_name+"</td><td>"+data.account+"</td><td>"+data.sch_name+"</td><td><a href='AdminCheckstudentInfo?stu_id="+data.stu_id+"' style='color: #b52e31'>查看</a></td></tr>";
            }
        }
    });
}
