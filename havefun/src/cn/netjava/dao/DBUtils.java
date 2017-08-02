package cn.netjava.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.kevin.entity.ReData;
import cn.kevin.entity.User;

public class DBUtils {
	static String sql = null;
	static DBHelper db1 = null;
	static ResultSet ret = null;
 public static ReData passmodify(String email1,String passwd1, String conta1){
	 ReData re=new ReData("email1", "passwd1", "conta1");
	 String email = null;
		String passwd = null;
		String conta = null;
		boolean flag = false;
		sql = "select *from users where email=?";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			PreparedStatement preStmt1 = db1.pst;
			preStmt1.setString(1, email1);
			ret = preStmt1.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				email = ret.getString("email");
				passwd = ret.getString("company");
				conta = ret.getString("contact");
				re=new ReData(email, passwd, conta);
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		
	 return re;
 }
	public static boolean insertinfo(String table, String col, String value) {
		String sql1 = "select * from " + table + " where " + col + " = ?";
		sql = "insert into " + table + "(" + col + ")" + " values(?)";
		db1 = new DBHelper(sql);
		DBHelper db2 = new DBHelper(sql1);
		boolean flag = false;
		try {
			
			PreparedStatement preStmt1 = db2.pst;
			preStmt1.setString(1, value);
			ResultSet flag1 = preStmt1.executeQuery();
			if (!flag1.next()) {
				PreparedStatement preStmt = db1.pst;
				preStmt.setString(1, value);
				flag = !preStmt.execute();
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		db1.close();
		return flag;

	}

	public static boolean regis(User user) {
		String email = user.getEmail();
		String passwd = user.getPassword();
		String conta = user.getContract();
		String qq = user.getQq();
		String tele = user.getTele();
		boolean flag = false;
		sql = "insert into users(email,password,contact,company,tele,qq) values(?,?,?,?,?,?)";
		db1 = new DBHelper(sql);
		try {
			PreparedStatement preStmt = db1.pst;
			preStmt.setString(1, user.getEmail());
			preStmt.setString(2, user.getPassword());
			preStmt.setString(3, user.getContract());
			preStmt.setString(4, user.getCompany());
			preStmt.setString(5, user.getTele());
			preStmt.setString(6, user.getQq());

			flag = !preStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		db1.close();
		return flag;
	}

	public static boolean querybyinfo(String col, String info) {
		String email1 = null;
		boolean flag = false;
		sql = "select *from users where " + col + "=?";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			PreparedStatement preStmt = db1.pst;
			preStmt.setString(1, info);
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				email1 = ret.getString(1);
				if (info.equals(info)) {
					flag = true;
				}
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean query(String email1, String passwd1, String conta1) {
		String email = null;
		String passwd = null;
		String conta = null;
		boolean flag = false;

		sql = "select *from users";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				email = ret.getString(1);
				passwd = ret.getString(2);
				conta = ret.getString(3);
				if (email.equals(email1) && passwd.equals(passwd1) && conta.equals(conta1)) {
					flag = true;
				}
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static ReData querybyemail(String email1, String passwd1, String conta1) {
		String email = null;
		String passwd = null;
		String conta = null;
		boolean flag = false;
		ReData re = new ReData("email", "password", "contact");
		sql = "select *from users";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			while (ret.next()) {
				email = ret.getString(1);
				passwd = ret.getString(2);
				conta = ret.getString(3);
				if (email.equals(email1)) {
					//flag = true;
					System.out.println("matching..........");
					re = new ReData(email, passwd, conta);
				}
			} // 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return re;
	}
	

	public static void main(String[] args) {
		//boolean flag = insertinfo("emailinfo", "email", "106789203@qq.com");
		 int flag=updatepasswd("1067892503@qq.com","1111");
		//ReData re=passmodify("1067892503@qq.com","wer","东");
		System.out.println(flag);
		// User user=new User("12", "123", "123", "123", "123", "123");
		// boolean reg=regis(user);
		// System.out.println(reg);
	}
	public static int updatepasswd(String email, String newpasswd) {
		// TODO Auto-generated method stub
		int flag=0;
		sql = "update users set password=? where email=?";// SQL语句
		db1 = new DBHelper(sql);// 创建DBHelper对象
		try {
			PreparedStatement preStmt = db1.pst;
			preStmt.setString(1, newpasswd);
		    preStmt.setString(2, email);
		    flag=preStmt.executeUpdate();
		   

			db1.close();
			// 显示数据
			// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}