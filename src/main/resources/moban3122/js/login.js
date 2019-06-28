var host="http://localhost:8080"

$("#student_login").click(function(){
	alert($("#student_username").val());
	
	$.ajax({
		url: host+"/Afind?aname=aa",
		type:"Get",
		dataType : "json",
		contentType: 'application/json;charset=UTF-8',
		//data :  JSON.stringify({
		//	"aname":"aa"
		//}),
		success:function(rs){
			alert(rs.data[1].aid);
		}
	});
	alert("bb");
})
