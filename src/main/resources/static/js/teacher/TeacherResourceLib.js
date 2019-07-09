var id=22;//$.cookie('acc_id');
var acc ="bb";//$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	
	getResource(id);
	
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";

$("#bu1").click(function(){
		var formdata=new FormData();
		formdata.append("file",$("#file")[0].files[0]);
		formdata.append("owner_id",id);
		formdata.append("type","doc");
		
		$.ajax({
			type: "post",
			url: "resource/upload",
			contentType: false,
			processData: false,
			async : false,
			data: formdata,
			dataType: 'json',
			success: function (res) {
				if(res.type=="fail"){
					alert("上传失败");
				}
				else{
					alert("上传成功");
					getResource(id);
				}
			}
		});	

	});
	
$("#file_list").on('click',".form-control1",function(){
	location.href = "/resource/download?type=doc&owner_id="+id+"&filename="+$(this)[0].innerHTML;
});
});



function getResource(id){
	$.ajax({
        url: "/resource/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"owner_id":id,
			"type":"doc"
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取文件失败");
				return;
			}
			var file_list=$("#file_list")[0];
			file_list.innerHTML="";
			for(var i=0;i<res.data.length;i++){
				file_list.innerHTML+="<label type='text' class='form-control1' style='border:0px;background:rgba(0, 0, 0, 0); margin-left: 100px'>"+res.data[i].resource_name+"</label>";
			}
        }
    });
}