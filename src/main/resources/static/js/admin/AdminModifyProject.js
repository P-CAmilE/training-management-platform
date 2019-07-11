var id =$.cookie('acc_id');
var acc =$.cookie('account');
// var user_name=$.cookie('username');

$(function(){
    $("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
    $("#cancelModify")[0].href= "AdminCheckProjectInfo?pro_id=" + getUrlParam("pro_id");

    $.ajax({
        type: "post",
        url: "project/findPlanName",
        contentType: false,
        processData: false,
        async : false,
        data: false,
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
            url: "project/findTeacherByPlan",
            contentType: 'application/json;charset=UTF-8',
            async : false,
            data: JSON.stringify({
                "plan_id": $("#new_plan_id").val()
            }),
            dataType: 'json',
            success: function (res) {
                //alert(res.type);
                // var i = teachernames.length;
                // teachernames.splice(0,i);
                teachernames = res.data;
                for(var i=0;i<res.data.length;i++){
                    var data = res.data[i];
                    teacher_table.innerHTML+="<tr style=\"border: none\" background-color: rgb"+(i%2==0?"(248, 255, 247)":"(238, 217, 215)")+">" +
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

    $("#modify_project_info").submit(function() {


        $.ajax({
            url: "project/update",
            type: "Post",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "pro_id": getUrlParam("pro_id"),
                "new_pro_name": $("#new_pro_name").val(),
                "new_plan_id": $("#new_plan_id").val(),
                "new_pro_description": $("#new_pro_description").val(),
                "new_pro_judge": $("#new_pro_judge").val(),
                "tea_id": vals,
            }),
            dataType: "json",
            async: false,
            success: function (res) {
                if (res.type === "fail") {
                    alert("修改项目失败");
                } else {
                    alert("修改项目成功");
                    window.location.href = "AdminCheckProjectInfo?pro_id=" + getUrlParam("pro_id");
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




