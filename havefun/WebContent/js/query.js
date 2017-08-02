$(document).ready(function() {
		$("#validate").click(function() {
			redirect();
		});

	});
	function redirect() {
		var column;
		var theme;
		var tab=$("#table").val();
		if(tab=="users"){
			column='{ ,email,password,contact,company,tele,qq';
			//alert(column);
		}
		else if(tab=="theme"){
			column='{ ,id,theme';
		}
		else if(tab=="emailinfo"){
			column='{ ,email';
		}
		else if(tab=="advice"){
			column='{ ,id,email,name,theme,comment';
		}
		$.ajax({
			url:"process",
			type:"post",
			data:{
				username : $("#username").val(),
				password : $("#password").val(),
				table:$("#table").val(),
				column:column,
			},
			dataType:"json",
			success:function(data){
				//alert(data);
				//data = '{tablename:" ",rows:' + data + '}';
				document.getElementById("content").style.display ="block";
				$('#test').DataGridClear();
				$('#test').DataGridSetItemClass("tr1", "tr2", "trhover");
				$('#test').DataGrid("100%", 200, data);
			},
			error:function(){
				alert("数据查询失败！");
			}
		});
	}