package com.xiaba2.core;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;


public class Test {
//	public static void main(String[] args) throws NamingException, NoSuchAlgorithmException {
//
//		if(true)
//		{
//
//			//LDAPAuthenticate.createInstance().add("goddie2", "123456");
//			LDAPAuthenticate.createInstance().update("goddie", "goddie");
//		}else
//		{
//			LDAPAuthenticate.createInstance().checkUser("admin", "123456");
//		}
//
//	}
	
	
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;  
  
    public static void main(String[] args) {  
       
    }
    
    
    public static String print()
    {
    	StringBuilder sb =new StringBuilder();
    	 sql = "select * from db_task_user where username like 'CRMT_%'";//SQL语句  
         db1 = new DBHelper(sql);//创建DBHelper对象  
   
         try {  
             ret = db1.pst.executeQuery();//执行语句，得到结果集  
             while (ret.next()) {
            	 sb.append("delete from db_task_user where nickname='");
            	 sb.append(ret.getString("nickname"));
            	 sb.append("' and introduce='");
            	 sb.append(ret.getString("introduce"));
            	 sb.append("';");
            	 sb.append("\n");
             }//显示数据  
             ret.close();  
             db1.close();//关闭连接  
         } catch (SQLException e) {  
             e.printStackTrace();  
         }  
         
         return sb.toString();
    }
	
	
}


class DBHelper {  
    public static final String url = "jdbc:mysql://127.0.0.1/cr2";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "goddie";  
  
    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public DBHelper(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  

