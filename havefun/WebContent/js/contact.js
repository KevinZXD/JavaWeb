
function regist(validate){	
	//校验Email, password，校验如果失败的话不提交
	if(validate.form()){
			$.ajax({
				url: "themeAction.do",
				type: "post",
				data: {
					comment: $("#content").val(),
				},
				dataType: "json",
				beforeSend: function(){
					$('.load').show();
				},
				success: function(data){
					//这里需要对后台传过来的数据进行解析，注册是否顺利
					$('.load').hide();
					//alert(data);
					if(data.code==0){
						alert("提交未成功，请重新提交");	
					}
					else{
						alert("您关注的内容已经提交成功！");		
					}
					//window.location.href ="contact.html";
					
				},
				error:function(data){
					//alert(data.code);
					alert("提交失败！请重新刷新提交！");
					//window.location.href ="contact.html";
				}
					

			});
		}
}



function advice(validate){	
	
	if(validate.form()){
			$.ajax({
				url: "adviceAction.do",
				type: "post",
				
				data: {
					name: $("#name").val(),
					email: $("#email").val(),
					theme: $("#reason").val(),
					comment: $("#message").val(),
				},
				dataType: "json",
				beforeSend: function(){
					$('.load').show();
				},
				success: function(data){
					//这里需要对后台传过来的数据进行解析，注册是否顺利
					$('.load').hide();
					//alert(data);
					if(data.code==0){
						alert("提交未成功，请重新提交");	
					}
					else{
						alert("您的问题或者建议我们已经成功收到，我们会尽快回复您帮您解决问题！");		
					}
					//window.location.href ="contact.html";
					
				},
				error:function(data){
					//alert(data.code);
					alert("提交失败！请重新输入！");
					//window.location.href ="contact.html";
				}
					

			});
		}
}
var validate=$("#search-form").validate({});
$("#submit").bind("click", function(){
	regist(validate);
});
var comment=$("#contact-form").validate({});
$("#commit").bind("click", function(){
	advice(comment);
});



