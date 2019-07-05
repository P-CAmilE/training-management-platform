var host="http://localhost:8080"

$("#company_login").click(function(){
    alert($("#recipient-name2").val());
    var username = $("#recipient-name2").val();
    var password = $("#password4").val();
    $.ajax({
        url: host+"/login",
        type:"post",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "username":username,
            "password": password,
            "usertype": 1
        }),
        success:function(data){
            alert(data.stuAccount);
        }
    });
    alert("bb");
})

$("#student_login").click(function(){
    alert($("#recipient-name").val());
    var username = $("#recipient-name").val();
    var password = $("#password").val();
    $.ajax({
        url: host+"/login",
        type:"post",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
            "username":username,
            "password": password,
            "usertype": 0
        }),
        success:function(data){
            alert(data.stuAccount);
        }
    });
    alert("bb");
})

$("#register").click(function(){
    alert($("#recipient-name3").val());
    var username = $("#recipient-name3").val();
    var password = $("#password1").val();
    var useremail = $("#recipient-email").val();

    $.ajax({
        url: host+"/register",
        type:"Post",
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
        }
    });
    alert("bb");
})