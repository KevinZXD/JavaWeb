package cn.netjava.action;   
  
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;

import cn.kevin.entity.ReData;
import cn.kevin.entity.User;
import cn.netjava.dao.DBUtils;
   
  
public class LoginAction implements Action {   
    public String execute(HttpServletRequest request,   
            HttpServletResponse response) {   
    	boolean login=false;
    	String url=null;
    	String loginurl="index.html";
    	String jsonstring=null;
        // 得到用户名和密码   
        String email = request.getParameter("email");   
        String passwd = request.getParameter("password"); 
        String contact = request.getParameter("contact"); 
        DBUtils ut=new DBUtils();
        System.out.println(email);
        System.out.println(passwd);
        System.out.println(contact);
        boolean e=false;
        boolean p=false;
        boolean c=false;
        ReData re=ut.querybyemail(email, passwd, contact);
       if(re.getEmail().equals(email)){
       	 e=true;
       }
        if(re.getPasswd().equals(passwd)){
       	 p=true;
       }
        if(re.getContact().equals(contact)){
       	 c=true;
       }
        login=ut.query(email, passwd,contact);
        if (login==true) {  
        	System.out.println("登陆成功");
        	jsonstring="{\"url\":\"index.html\",\"email\":1,\"passwd\":1,\"contact\":1}";
            System.out.println("index.html");
            return jsonstring;   
        } else {   
            //request.setAttribute("email", email); 
            //request.setAttribute("info", "登陆失败"); 
        	if(e==false){
        		System.out.println("email is no registed!");
        		jsonstring="{\"url\":\"register.html\",\"email\":0,\"passwd\":0,\"contact\":0}";
        	}else if(p==false){
        		jsonstring="{\"url\":\"login.html\",\"email\":1,\"passwd\":0,\"contact\":0}";
        	}else if(c==false){
        		jsonstring="{\"url\":\"login.html\",\"email\":1,\"passwd\":1,\"contact\":0}";
        	}
             
        } 
        System.out.println(jsonstring);
        return jsonstring; 
    }   
  
}  