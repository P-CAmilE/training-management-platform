var host="127.0.0.1:8080"


$("#company_login").click(function(){
    alert($("#recipient-name2").val());
    var username = $("#recipient-name2").val();
    var password = $("#password4").val();
    $.ajax({
        url: "/login",
        type: "POST",
        // dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "username":username,
            "password": password,
            "usertype": 1
        }),
        success:function(data){
            alert("company log in");
            window.location.href="CompIndex"
        },
        error:function () {
            alert("error")
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
            alert("student log in");
            window.location.href="StudentIndex"
        },
        error: function () {
            alert("error");

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
            alert(data);
            window.location.href="StudentIndex"
        },
        error:function () {
            alert("error");

        }
    });

})