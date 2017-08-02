package cn.netjava.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.netjava.dao.DBESQL;

public class ThemeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String content=request.getParameter("comment");
		String sql="insert into theme(theme)values(?)";
		Map<String,String> map=new LinkedHashMap<String, String>();
		map.put("themen", content);
		DBESQL esql=new DBESQL();
		int i=esql.executeSql(map,sql);
		String jsonstring = "{\"code\":0}";
		//String code="0";
		if(i!=0){
			jsonstring="{\"code\":1}";
			System.out.println("插入成功");
			//code="1";
		}
		System.out.println(jsonstring);
		return jsonstring;
	}

}
