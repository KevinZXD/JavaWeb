package cn.netjava.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import cn.kevin.entity.ReData;
public class DBESQL {
public static int executeSql(Map<String,String> map,String sql){
	int flag=0;
	int i=1;

DBHelper db1 = new DBHelper(sql);// 创建DBHelper对象
try {
	PreparedStatement preStmt1 = db1.pst;

for(String value:map.values()){
	preStmt1.setString(i, value);
	i++;
}
flag = preStmt1.executeUpdate();// 执行语句，得到结果集
	
	db1.close();// 关闭连接
} catch (SQLException e) {
	e.printStackTrace();
	
}
	return flag;
}
public static void main(String[] args) {
	String sql="insert into advice(email,name,theme,comment) values(?,?,?,?)";
	String sql1="insert into theme(theme)values(?)";
	Map<String,String> map=new LinkedHashMap<String, String>();
	map.put("email", "1067@qq.com");
	int i=executeSql(map,sql1);
	System.out.println(i);
}

}