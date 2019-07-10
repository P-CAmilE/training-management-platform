var id =21;//$.cookie('acc_id');
var acc ="admin";//$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $("#cancelModify")[0].href= "AdminCheckPlanInfo?plan_id=" + getUrlParam("plan_id");
    var schools ;
    var companys;
    $.ajax({
        type: "post",
        url: "plan/findUnit",
        contentType: false,
        processData: false,
        async : false,
        data: false,
        dataType: 'json',
        success: function (res) {
            //alert(res.type);
            companys = res.company;
            schools = res.school;
            var com_name=$("#new_com_name")[0];
            for(var i=0;i<res.company.length;i++){
                var data = res.company[i];
                com_name.innerHTML+="<option value ='"+ data.com_name+"'>"+data.com_name+"</option>";
            }
            var sch_name=$("#new_sch_name")[0];
            for(var i=0;i<res.school.length;i++){
                var data = res.school[i];
                sch_name.innerHTML+="<option value ='"+ data.sch_name+"'>"+data.sch_name+"</option>";
            }
        }
    });

    var vals= [];
    var teachernames =[];
    $("#selectTeacher").click(function(){
        var teacher_table=$("#teacher_table")[0];
        teacher_table.innerHTML = "<tr style=\"border: none;background-color: grey\">" +
            "                                                        <th style=\"width: 100px\">选择</th>" +
            "                                                        <th style=\"width: 100px\">教师名</th>" +
            "                                                        <th style=\"width: 100px\">单位</th>" +
            "                                                    </tr>";
        $.ajax({
            type: "post",
            url: "plan/findTeacherByUnit",
            contentType: 'application/json;charset=UTF-8',
            async : false,
            data: JSON.stringify({
                "com_name": $("#new_com_name").val(),
                "sch_name": $("#new_sch_name").val(),
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                // var i = teachernames.length;
                // teachernames.splice(0,i);
                teachernames = res.data;
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    teacher_table.innerHTML+="<tr style=\"border: none\">" +
                        "                                                        <td><input name=\"teacher\" type=\"checkbox\" value=\""+ data.tea_id+ "\" /></td>" +
                        "                                                        <td><label>"+ data.tea_name +"</label></td>" +
                        "                                                        <td><label>"+ data.tea_unit +"</label></td>" +
                        "                                                    </tr>";
                }
            }
        });
    });

    $("#choose").click(function(){

        var i = vals.length;
        var teachers = "已选择：";
        vals.splice(0,i);
        $("input:checkbox:checked").each(function (index, item) {
            vals.push($(this).val());
            for(var i = 0;i < teachernames.length;i++) {
                if (teachernames[i].tea_id == $(this).val()) {
                    teachers = teachers + "    " + teachernames[i].tea_name;
                }
            }
        });
        $("#selectedTeacher").val(teachers);
    });

    $("#modify_plan_info").submit(function() {

        var schoolID, companyID;
        for(var i = 0;i < schools.length;i ++){
            if(schools[i].sch_name == $("#new_sch_name").val()) {
                schoolID = schools[i].sch_id;
                break;
            }
        }
        for(var i = 0;i < companys.length;i ++){
            if(companys[i].com_name == $("#new_com_name").val()) {
                companyID = companys[i].com_id;
                break;
            }
        }
        $.ajax({
            url: "plan/update",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "plan_id": getUrlParam("plan_id"),
                "new_plan_name": $("#new_plan_name").val(),
                "new_course_name": $("#new_course_name").val(),
                "new_course_score": $("#new_course_score").val(),
                "new_start_time": $("#new_start_time").val(),
                "new_end_time": $("#new_end_time").val(),
                "new_sch_id": schoolID,
                "new_com_id": companyID,
                "new_joined_class": $("#new_joined_class").val(),
                "new_plan_description": $("#new_plan_description").val(),
                "tea_id": vals,
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type === "fail") {
                    alert("修改计划失败");
                } else {
                    alert("修改计划成功");
                    window.location.href = "AdminCheckPlanInfo?plan_id=" + getUrlParam("plan_id");
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




