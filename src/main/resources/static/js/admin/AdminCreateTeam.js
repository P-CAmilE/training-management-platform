var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

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

            var plan_id=$("#plan_id")[0];
            for(var i=0;i<res.data.length;i++){
                var data = res.data[i];
                plan_id.innerHTML+="<option value ='"+ data.plan_id+"'>"+data.plan_name+"</option>";
            }
        }
    });
    if($("#plan_id").val() != null) {
        $.ajax({
            type: "post",
            url: "team/findPlanProject",
            contentType: 'application/json;charset=UTF-8',
            async: false,
            data: JSON.stringify({
                "plan_id": $("#plan_id").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);

                var pro_id = $("#pro_id")[0];
                for (var i = 0; i < res.data.length; i++) {
                    var data = res.data[i];
                    pro_id.innerHTML += "<option value ='" + data.pro_id + "'>" + data.pro_name + "</option>";
                }
            }
        });
    }

    $("#plan_id").change(function () {
        var pro_id=$("#pro_id")[0];
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
                "plan_id": $("#plan_id").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                studentrnames = res.data;
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    student_table.innerHTML+="<tr style=\"border: none\" background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+">" +
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

    $("#create_team_info").submit(function() {

        $.ajax({
            url: "team/insert",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "team_name": $("#team_name").val(),
                "plan_id": $("#plan_id").val(),
                "pro_id": $("#pro_id").val(),
                "stu_ids": vals,
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type === "fail") {
                    alert("添加团队失败");
                } else {
                    alert("添加团队成功");
                    window.location.href = "AdminCheckTeam.html";
                }
            }
        });
    });
});


