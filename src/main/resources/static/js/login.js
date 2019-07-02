var host="127.0.0.1:8080"


$("#company_login").click(function(){
    alert($("#recipient-name2").val());
    var username = $("#recipient-name2").val();
    var password = $("#password4").val();
    $.ajax({
        url: "/login",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "username":username,
            "password": password,
            "usertype": 1
        }),
        success:function(data){
            if(data.logstate == "1"){ window.location.href="CompIndex";}
            alert(data.msg);
        },
        error:function () {
            alert("ajax error");
        }
    });

})


$("#student_login").click(function(){
    alert($("#recipient-name").val());
    var username = $("#recipient-name").val();
    var password = $("#password").val();

    $.ajax({
        url: "/login",
        type: "POST",
        // dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "username": username,
            "password": password,
            "usertype": 0
        }),
        success:function(data){
            if(data.logstate == "1"){ window.location.href="StudentIndex";}
            alert(data.msg);
        },
        error: function () {
            alert("ajax error");
        }
    });

})
$("#register").click(function(){
    alert($("#recipient-name3").val());
    var username = $("#recipient-name3").val();
    var password = $("#password1").val();
    var useremail = $("#recipient-email").val();

    $.ajax({
        url: "/register",
        type: "POST",

        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "username":username,
            "password": password,
            "usertype": 0,
            "useremail": useremail

        }),
        success:function(data){
            if(data.getParameter("logstate") == "1"){ window.location.href="CompIndex";}
            else if(data.logstate == "0"){ window.location.href="StudentIndex";}
            alert(data.msg);
        },
        error:function () {
            alert("ajax error");
        }
    });

})