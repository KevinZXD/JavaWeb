package cn.netjava.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.kevin.entity.ReData;
import cn.kevin.entity.User;
import cn.netjava.dao.DBUtils;
public class PassmodifyAction implements Action  {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
    	
    	String url=null;
    	String jsonstring=null;
        // 得到用户名和密码   
        String email = request.getParameter("email");   
        String passwd = request.getParameter("password"); 
        String contact = request.getParameter("contact");
        String  newpasswd= request.getParameter("newpasswd");
        System.out.println(email+passwd+contact+newpasswd);
        
        DBUtils ut=new DBUtils();
        boolean e=false;
        boolean p=false;
        boolean c=false;
        ReData passmodify = ut.passmodify(email,passwd,contact);
        System.out.println(passmodify);
        if(passmodify.getEmail().equals(email)){
          	  e = true;
          }
           if(passmodify.getPasswd().equals(passwd)){
          	 p=true;
          }
           if(passmodify.getContact().equals(contact)){
          	 c=true;
          }
           if (e==true&&p==true&&c==true) {  
        	   int update=ut.updatepasswd(email, newpasswd);
        	   if (update==1){
        			System.out.println("修改成功");
                   	jsonstring="{\"url\":\"login.html\",\"email\":1,\"passwd\":1,\"contact\":1}";
                       System.out.println("login.html");
                       return jsonstring;     
        	   }else{
        		   jsonstring="{\"url\":\"passwd.html\",\"email\":1,\"passwd\":1,\"contact\":1}";
        	   }
           
           } else {   
               //request.setAttribute("email", email); 
               //request.setAttribute("info", "登陆失败"); 
           	if(e==false){
           		System.out.println("email is no registed!");
           		jsonstring="{\"url\":\"register.html\",\"email\":0,\"passwd\":0,\"contact\":0}";
           	}else if(p==false){
           		jsonstring="{\"url\":\"passwd.html\",\"email\":1,\"passwd\":0,\"contact\":0}";
           	}else if(c==false){
           		jsonstring="{\"url\":\"passwd.html\",\"email\":1,\"passwd\":1,\"contact\":0}";
           	}
                
           } 
		return jsonstring;
	}

}
