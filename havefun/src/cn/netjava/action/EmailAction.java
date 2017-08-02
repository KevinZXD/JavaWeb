package cn.netjava.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.netjava.dao.DBUtils;

public class EmailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		 String email = request.getParameter("email");
		 String info=null;
		 DBUtils ut = new DBUtils();
		 boolean flag=ut.insertinfo("emailinfo", "email", email);
		 if (flag==true)
		 {
		 info="<script>alert('Your email is received!');window.location.href =\"index.html\";</script>"; 
		}else{
			info="<script>alert('Something is wrong or your email has received!');window.location.href =\"index.html\"</script>";
		}
		 System.out.println(info);
	
		 return info;
	}

}
