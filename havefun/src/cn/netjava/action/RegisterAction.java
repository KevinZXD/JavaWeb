package cn.netjava.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kevin.entity.User;
import cn.netjava.dao.DBUtils;

public class RegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		boolean register = false;
		// 得到用户名和密码
		String jsonstring = "{\"url\":\"login.html\",\"email\":0,\"passwd\":0,\"contact\":0}";
		String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		String contact = request.getParameter("contact");
		String tele = request.getParameter("tel");
		String qq = request.getParameter("QQ");
		System.out.println(qq);
		String company = request.getParameter("company");
		DBUtils ut = new DBUtils();
		User user = new User(email, passwd, contact, tele, qq, company);
		boolean e = ut.querybyinfo("email", email);
		if (e == false) {
			register = ut.regis(user);
			if (register == true) {
				// request.setAttribute("userName", email);
				// request.setAttribute("info", "注册成功");
				jsonstring = "{\"url\":\"login.html\",\"email\":1,\"passwd\":1,\"contact\":1}";
			} 
			else{
				jsonstring = "{\"url\":\"register.html\",\"email\":0,\"passwd\":0,\"contact\":0}";
			}
		}else{
			System.out.println("email 已存在");
			
		}
		return jsonstring;
	}

}
