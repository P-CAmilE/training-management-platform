var host="127.0.0.1:8080"


$("#company_login").submit(function(){
    var username = $("#recipient-name2").val();
    var password = $("#password4").val();
    $.ajax({
        url: "/login",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "account":username,
            "password": password,
            "user_type": "company"
        }),
        success:function(res){
            alert(res.msg);
            if(res.type == "success"){window.location.href="CompHomepage";}
        },
        error:function () {
            alert("ajax error")
        }
    });

});

$("#student_login").submit(function(){
    var username = $("#recipient-name").val();
    var password = $("#password").val();
    $.ajax({
        url: "/login",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "account": username,
            "password": password,
            "user_type": "student"
        }),
        success:function(res){
            alert(res.msg);
            if(res.type == "success"){window.location.href="StuHomepage";}

        },
        error: function () {
            alert("ajax error");
        }
    });
});

$("#teacher_login").submit(function(){
    var username = $("#recipient-name1").val();
    var password = $("#password3").val();
    $.ajax({
        url: "/login",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "account":username,
            "password": password,
            "user_type": "teacher"
        }),
        success:function(res){
            alert(res.msg);
            if(res.type == "success"){window.location.href="TeacherHomepage";}

        },
        error:function () {
            alert("ajax error")
        }
    });

});

$("#register").submit(function(){
    var username = $("#recipient-name3").val();
    var password = $("#password1").val();
    var useremail = $("#recipient-email").val();
    var confirmpassword = $("#password2").val();
        $.ajax({
            url: "/register",
            type: "POST",
            dataType : "json",
            contentType: 'application/json;charset=UTF-8',
            data :  JSON.stringify({
                "account":username,
                "password": password,
                "user_type": "student",
                "user_email": useremail
            }),
            success:function(data){
                alert(data.msg);
                if(data.type == "success") {
                    if (data.usertype == "student") {
                        window.location.href = "stuHomepage";
                    } else if (data.usertype == "company") {
                        window.location.href = "CompHomepage";
                    }
                }
            },
            error: function (){
                alert("ajax error");
            }
        });
});