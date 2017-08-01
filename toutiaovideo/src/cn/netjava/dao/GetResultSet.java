package cn.netjava.dao;
import java.sql.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GetResultSet {
	private static  String username = "root";
	private static String password = "kevin_zxd";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	public GetResultSet() throws ClassNotFoundException {

	}

	public GetResultSet(String username, String password) {
		// super();
		GetResultSet.username = username;
		GetResultSet.password = password;
		System.out.println(username + password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		GetResultSet.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		GetResultSet.password = password;
	}

	public static ResultSet getResultSet(String table) throws InstantiationException, Exception, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/toutiao_video?useUnicode=true&characterEncoding=utf8";
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("驱动加载成功");
		conn = DriverManager.getConnection(url, username, password);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from "+table);
		return rs;

	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String resultSetToJson(ResultSet rs,String[] column) throws SQLException, JSONException {
		// json数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(column[i], value); // 中文转到html无法解析
			}
			array.add(jsonObj);
		}
		return array.toString();
	}

}