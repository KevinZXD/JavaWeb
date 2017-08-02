package com.json;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.netjava.dao.GetResultSet;
import net.sf.json.JSONException;

/**
 * Servlet implementation class Json2Jsp
 */
@WebServlet("/Json2Jsp")
public class Json2Jsp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Json2Jsp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String[] column12 = {" ", "email", "password", "contact", "company","tele","qq" };
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String table=request.getParameter("table");
		String column1=request.getParameter("column");
		System.out.println(username+password+table+column1);
		GetResultSet a = new GetResultSet(username, password);
		ResultSet set = null;
		try {
			set = GetResultSet.getResultSet(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] column=column1.split(",");
		System.out.println(column[1]);
		String jsonsql = null;
		try {
			jsonsql = GetResultSet.resultSetToJson(set,column);
		} catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JSONArray arry=JSONArray.fromObject(list);
		// System.out.println(arry.toString());
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		// System.out.println(overtime);
		// System.out.println(number);
		System.out.println(jsonsql);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonsql);
		response.getWriter().flush();
		response.getWriter().close();
		GetResultSet.close();
	}
}
