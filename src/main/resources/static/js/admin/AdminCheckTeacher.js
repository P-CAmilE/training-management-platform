var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    getStudent(id);

    $("#search").submit(function(){
        $.ajax({
            url: "/teacher/find",
            type: "POST",
            dataType : "json",
            contentType: 'application/json;charset=UTF-8',
            data :  JSON.stringify({
                "search_name":$("#search_name").val()
            }),
            success:function(res){
                if(res.type=="fail"){
                    alert("获取老师失败");
                    return;
                }

                var teacher_table=$("#teacher_table")[0];
                teacher_table.innerHTML="<thead><tr style='border: none;background-color: rgb(238, 217, 215)'><th style=\"width: 100px\">教师名</th>" +
                    "                                    <th style=\"width: 200px\">账号</th>" +
                    "                                    <th style=\"width: 200px\">单位</th>" +
                    "                                    <th style=\"width: 150px\">操作</th>"+"</tr></thead>";
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    teacher_table.innerHTML+="<tr style='border: none; background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.tea_name+"</td><td>"+data.account+"</td><td>"+data.tea_unit+"</td><td><a href='AdminCheckTeacherInfo?tea_id="+data.tea_id+"' style='color: #b52e31'>查看</a></td></tr>";
                }


            }
        });
    });
});
//获取

function getStudent(user_id){

    var teacher_table=$("#teacher_table")[0];
    teacher_table.innerHTML="<thead><tr style='border: none;background-color: rgb(238, 217, 215)'><th style=\"width: 100px\">教师名</th>" +
        "                                    <th style=\"width: 200px\">账号</th>" +
        "                                    <th style=\"width: 200px\">单位</th>" +
        "                                    <th style=\"width: 150px\">操作</th>"+"</tr></thead>";

    $.ajax({
        url: "/teacher/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({

        }),
        success:function(res){
            if(res.type=="fail"){
                alert("获取教师失败");
                return;
            }

            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                teacher_table.innerHTML+="<tr style='border: none; background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+"'><td>"+data.tea_name+"</td><td>"+data.account+"</td><td>"+data.tea_unit+"</td><td><a href='AdminCheckTeacherInfo?tea_id="+data.tea_id+"' style='color: #b52e31'>查看</a></td></tr>";
            }
        }
    });
}
