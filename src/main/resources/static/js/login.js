var host="127.0.0.1:8080"


$("#company_login").click(function(){
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
        success:function(data){
            alert(data.msg);
            if(data.type == "success"){window.location.href="CompHomepage";}

        },
        error:function () {
            alert("ajax error")
        }
    });

})

$("#student_login").click(function(){
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
        success:function(data){
            alert(data.msg);
            if(data.type == "success"){window.location.href="stuHomepage";}

        },
        error: function () {
            alert("ajax error");

        }
    });

})
$("#register").click(function(){
    var username = $("#recipient-name3").val();
    var password = $("#password1").val();
    var useremail = $("#recipient-email").val();
    var confirmpassword = $("#password2").val();
    if(password == confirmpassword){
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
    }else{
        alert("两次输入密码不同，请重新输入");
    }

})