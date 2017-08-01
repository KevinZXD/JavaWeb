package cn.netjava.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.kevin.entity.ReData;
import cn.kevin.entity.User;
import net.sf.json.JSONException;

public class DBUtils {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
	
	public static ResultSet querybyindex(String pageIndex,String table) {
		sql = "select * from "+table+" limit "+pageIndex+",6";//
		db1 = new DBHelper(sql);// 
		try {
			PreparedStatement preStmt = db1.pst;
			ret = db1.pst.executeQuery();//
			//ret.close();
			//db1.close();// 鍏抽棴杩炴帴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	

	public static void main(String[] args) throws ClassNotFoundException, JSONException, SQLException {
		
		String index="0";
		ret=DBUtils.querybyindex(index,"budejie");
		String[] column=new String[]{" ","id","video_id","title","video_url","image_url","ti","public_date"};
	int len=ret.getMetaData().getColumnCount();
	System.out.println(len);
	if(len!=0){
		String overcome=GetResultSet.resultSetToJson(ret, column);
		System.out.println(overcome);
	}else{
		System.out.println("越界");
	}

		
		
	}

}