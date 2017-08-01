package cn.netjava.action;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.netjava.dao.DBUtils;
import cn.netjava.dao.GetResultSet;
import net.sf.json.JSONException;

public class GetVideoAction implements Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String jsonstring = null;
		String[] column = null;
		DBUtils db = new DBUtils();
		String index = request.getParameter("index");
		
		int max=Integer.valueOf(index);
		 index=String.valueOf(((int) (Math.random()*max)));
		String table = request.getParameter("table");
		//response.setCharacterEncoding("utf-8");
		System.out.println("起始页为："+index);
		
		String[] column2=new String[]{" ","id","video_id","title","video_url","image_url","ti","public_date"};
		String[] column1 = new String[] { " ", "id", "play_count", "ti", "image_url", "video_url", "title" };
		if(table.equals("1")){
			column=column1;//头条表
			table="video";
		}else{
			column=column2;//budejiebiao
			table="budejie";
		}
		ResultSet ret = DBUtils.querybyindex(index,table);
		try {
			int len=ret.getMetaData().getColumnCount();
			if(len!=0){
				jsonstring = GetResultSet.resultSetToJson(ret, column);
			}else{
				System.out.println("越界！！！");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(jsonstring);
		return jsonstring;
	}

}