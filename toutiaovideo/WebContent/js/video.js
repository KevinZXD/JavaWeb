$(document).ready(function () {  
	var index=0;
	var index1=2000;
	refrash();
	function refrash() {  
		$("#content").empty();
	  $.ajax({  
	         type: "POST",  
	         url: "getVideoAction.do",
	         data: {
					index:index1,	
					table:"2"
				         },
	         dataType: "JSON",  
	         success: function(data)
			 {  
	            var video=eval(data);
		        for(var video_url in video )
				{
				var title=video[video_url].title;
					//alert(title);
					var image_url=video[video_url].image_url;
					var video_url=video[video_url].video_url;
					
					//var public_date=video[video_url]['public_date'];
					//var ti=video[video_url]['ti'];
					var str="<li>"+title+"</li><video width='600px' height='300px' src='"+video_url+"' poster='"+image_url+"' controls='controls' preload='none'></video><hr/>";
					$("#content").append(str);				
		        }
		        index=index+6;
			 }
		})
		     }
        $("#refresh1").click(function(){
        	refrash();
        });
        $("#refresh2").click(function(){
        	refrash();
        });
			});