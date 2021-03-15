/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFrontEnd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bilal
 */
public class DBConnection {
    public static Connection getConnection(String nam) throws Exception{
	try {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/"+nam;
		String username = "root";
		String password = "KalsoomMajeed786";
		Class.forName(driver);	
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connected");
		return conn;
	}catch(Exception e) {
		System.out.println(e);
	}
	
	return null;
}

}
