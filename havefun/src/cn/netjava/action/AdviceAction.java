package cn.netjava.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.netjava.dao.DBESQL;

public class AdviceAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String theme=request.getParameter("theme");
		String comment=request.getParameter("comment");
		System.out.println(name+email+theme+comment);
		String sql="insert into advice(name,email,theme,comment)values(?,?,?,?)";
		Map<String,String> map=new LinkedHashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		map.put("theme", theme);
		map.put("comment", comment);
		DBESQL esql=new DBESQL();
		int i=esql.executeSql(map,sql);
		String jsonstring = "{\"code\":0}";
		if(i!=0){
			jsonstring="{\"code\":1}";
			System.out.println("用户建议插入成功");
		}
		System.out.println(jsonstring);
		return jsonstring;
		
	}

}
