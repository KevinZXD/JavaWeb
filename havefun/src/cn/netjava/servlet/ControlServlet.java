package cn.netjava.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.netjava.action.Action;   
import cn.netjava.action.ActionFactory; 
/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        //得到当前Servlet的请求路径   
        String pathName =request.getServletPath();   
        // request.getContextPath();得到项目名字   
        System.out.println("pathName:"+pathName);   
        //得到请求的Action名字   
        int index = pathName.indexOf(".");   
        String ActionName = pathName.substring(1, index);   
        System.out.println(ActionName);   
        //获取运行时参数   
        String ActionClassName = this.getInitParameter(ActionName);   
        //得到Action对象   
        Action action = ActionFactory.getActionFactory().getAction(ActionClassName);   
        //执行Action的execute得到要返回的URL路径   
        String url = action.execute(request, response);   
        if(url==null){   
            request.getRequestDispatcher("error.jsp").forward(request, response);      
        }else{  
        	System.out.println("开始重定向"+url);
        	PrintWriter out = response.getWriter();
        	 //String jsonstring="{\"url\":\"index.html\",\"email\":\"zxdcf\",\"passwd\":1,\"contact\":0}";
        	out.println(url);
            //request.getRequestDispatcher(url).forward(request, response);   
        	//response.sendRedirect(url);
        }   
    }   

}
