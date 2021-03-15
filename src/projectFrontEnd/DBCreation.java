/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFrontEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Bilal
 */
public class DBCreation {
    public static void createTables(String nam,String query) throws Exception{
	String dbname = nam;
	String Query=query;
	try {
        DBConnection dn = new DBConnection();
	Connection con = dn.getConnection(dbname);
	PreparedStatement create = con.prepareStatement(Query);
	create.executeUpdate();
	}catch(Exception e) {
		System.out.println(e);
	}
	finally {
		System.out.println("Table has been created");
	}
	
} 
}
