var id =$.cookie('acc_id');
var acc =$.cookie('account');
var user_name=$.cookie('username');

$(function(){
	getLog(getUrlParam("stu_id"));
	$("#username")[0].innerHTML=acc+"&nbsp;&nbsp;&nbsp;";
	
$("#log_table").on('click',".read",function(){
	$.ajax({
        url: "/log/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"log_id":$(this).val(),
        }),
        success:function(res){
			if(res.type=="fail"){
				return;
			}
			$("#log_context").val(res.data[0].log_context);
			$("#title").val(res.data[0].log_title);
        }
    });
	
});
	

});

//获取学生日志
function getLog(user_id){
	$.ajax({
        url: "/log/find",
        type: "POST",
        dataType : "json",
        contentType: 'application/json;charset=UTF-8',
        data :  JSON.stringify({
			"user_id":user_id
        }),
        success:function(res){
			if(res.type=="fail"){
				alert("获取历史日志失败");
				return;
			}
			//动态生成日志表
			var log_table=$("#log_table")[0];
			log_table.innerHTML="<tr id='log_table' style='border: none;background-color: grey'><td>标题</td><td>最新提交时间</td><td>操作</td></tr>";
			for(var i=0;i<res.data.length;i++){
				var data=res.data[i];
				log_table.innerHTML+="<tr style='border: none'><td>"+data.log_title+"</td><td>"+renderTime(data.log_date)+"</td><td><button type='button' class='read' value='"+data.log_id+"' style='border-style: none;background: none;color: #b52e31' data-toggle='modal' aria-pressed='false' data-target='#exampleModal2'>查看</button></td></tr>";
			}
			
        }
    });
}

function renderTime(date) {
  var dateee = new Date(date).toJSON();
  return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
}

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  // 匹配目标参数
    if (r != null) return unescape(r[2]); return null; // 返回参数值
}