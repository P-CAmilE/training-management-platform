var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    getTeacher(id);

    $("#search").submit(function(){
        $.ajax({
            url: "/student/find",
            type: "POST",
            dataType : "json",
            contentType: 'application/json;charset=UTF-8',
            data :  JSON.stringify({
                "search_name":$("#search_name").val()
            }),
            success:function(res){
                if(res.type=="fail"){
                    alert("获取学生失败");
                    return;
                }

                var student_table=$("#student_table")[0];
                student_table.innerHTML="<tr style=\"border: none;background-color: rgb(238, 217, 215)\">" +
                    "                                    <th style=\"width: 100px\">学生名</th>" +
                    "                                    <th style=\"width: 200px\">账号</th>" +
                    "                                    <th style=\"width: 200px\">学校</th>" +
                    "                                    <th style=\"width: 150px\">操作</th>" +
                    "                                </tr>";
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    student_table.innerHTML+="<tr style='border: none ;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.stu_name+"</td><td>"+data.account+"</td><td>"+data.sch_name+"</td><td><a href='AdminCheckstudentInfo?stu_id="+data.stu_id+"' style='color: #b52e31'>查看</a></td></tr>";
                }
            }
        });
    });
});
//获取教师
function getTeacher(user_id){

    var student_table=$("#student_table")[0];
    student_table.innerHTML="<tr style=\"border: none;background-color: rgb(238, 217, 215)\">" +
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
                student_table.innerHTML+="<tr style='border: none ;background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.stu_name+"</td><td>"+data.account+"</td><td>"+data.sch_name+"</td><td><a href='AdminCheckstudentInfo?stu_id="+data.stu_id+"' style='color: #b52e31'>查看</a></td></tr>";
            }
        }
    });
}
