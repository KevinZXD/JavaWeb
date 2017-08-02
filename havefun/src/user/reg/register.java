package user.reg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.kevin.entity.User;
import cn.netjava.dao.DBUtils;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String contact=request.getParameter("contact");
		String company=request.getParameter("company");
		String tel=request.getParameter("tel");
		String qq=request.getParameter("QQ");
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("contact"));
		System.out.println(request.getParameter("company"));
		System.out.println(request.getParameter("tel"));
		System.out.println(request.getParameter("QQ"));
		PrintWriter write=response.getWriter();
		DBUtils ut=new DBUtils();
		User user=new User(email, password, contact, tel, qq, company);
		boolean flag=ut.regis(user);
		
	
		
		//System.out.println(code.code);
	; 
		doGet(request, response);
	}

}
