package com.slk.util;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
private static Connection connection = null;
	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
            	/*String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/abc_bank";
                String user = "root";
                String password = "1234";*/
            	Properties prop = new Properties();
                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
                prop.load(inputStream);
				
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
				
				e.printStackTrace();
			}
            
            
            return connection;
        }
	}
}
