var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $("#cancelModify")[0].href= "AdminCheckTeamInfo?team_id=" + getUrlParam("team_id");

    $.ajax({
        type: "post",
        url: "team/findPlanProject",
        contentType: 'application/json;charset=UTF-8',
        async : false,
        data: JSON.stringify({

        }),
        dataType: 'json',
        success: function (res) {
            //alert(res.type);

            var plan_id=$("#new_plan_id")[0];
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                plan_id.innerHTML+="<option value ='"+ data.plan_id+"'>"+data.plan_name+"</option>";
            }
        }
    });
    if($("#new_plan_id").val() != null) {
        $.ajax({
            type: "post",
            url: "team/findPlanProject",
            contentType: 'application/json;charset=UTF-8',
            async: false,
            data: JSON.stringify({
                "plan_id": $("#new_plan_id").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);

                var pro_id = $("#new_pro_id")[0];
                for (var i = 0; i < res.data.length; i++) {
                    var data = res.data[i];
                    pro_id.innerHTML += "<option value ='" + data.pro_id + "'>" + data.pro_name + "</option>";
                }
            }
        });
    }

    $("#new_plan_id").change(function () {
        var pro_id=$("#new_pro_id")[0];
        pro_id.innerHTML = null;
        $.ajax({
            type: "post",
            url: "team/findPlanProject",
            contentType: 'application/json;charset=UTF-8',
            async : false,
            data: JSON.stringify({
                "plan_id": $(this).children("option:selected").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    pro_id.innerHTML+="<option value ='"+ data.pro_id+"'>"+data.pro_name+"</option>";
                }
            }
        });
    });

    var vals= [];
    var studentrnames =[];
    $("#selectStudent").click(function(){
        var student_table=$("#student_table")[0];
        student_table.innerHTML = "<tr style=\"border: none;background-color: grey\">" +
            "                                                        <th style=\"width: 100px\">选择</th>" +
            "                                                        <th style=\"width: 100px\">学生名</th>" +
            "                                                        <th style=\"width: 100px\">学校</th>" +
            "                                                    </tr>";
        $.ajax({
            type: "post",
            url: "team/findStudent",
            contentType: 'application/json;charset=UTF-8',
            async : false,
            data: JSON.stringify({
                "plan_id": $("#new_plan_id").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                studentrnames = res.data;
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    student_table.innerHTML+="<tr style=\"border: none\">" +
                        "                                                        <td><input name=\"teacher\" type=\"checkbox\" value=\""+ data.stu_id+ "\" /></td>" +
                        "                                                        <td><label>"+ data.stu_name +"</label></td>" +
                        "                                                        <td><label>"+ data.sch_name +"</label></td>" +
                        "                                                    </tr>";
                }
            }
        });
    });

    $("#choose").click(function(){

        var i = vals.length;
        var students = "已选择：";
        vals.splice(0,i);
        $("input:checkbox:checked").each(function (index, item) {
            vals.push($(this).val());
            for(var i = 0;i < studentrnames.length;i++) {
                if (studentrnames[i].stu_id == $(this).val()) {
                    students = students + "   " + studentrnames[i].stu_name;
                }
            }
        });
        $("#selectedStudent").val(students);
    });

    $("#modify_team_info").submit(function() {

        $.ajax({
            url: "team/update",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "team_id": getUrlParam("team_id"),
                "new_team_name": $("#new_team_name").val(),
                "new_plan_id": $("#new_plan_id").val(),
                "new_pro_id": $("#new_pro_id").val(),
                "stu_id": vals,
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type === "fail") {
                    alert("修改团队失败");
                } else {
                    alert("修改团队成功");
                    window.location.href = "AdminCheckTeamInfo?team_id=" + getUrlParam("team_id");
                }
            }
        });
    });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}




