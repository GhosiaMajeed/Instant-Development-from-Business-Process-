/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFrontEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static projectFrontEnd.dashboard.PackageName;


/**
 *
 * @author Bilal
 */
public class FileHandler {
   public void ExtractionAndConversion() throws Exception{
        
		// TODO Auto-generated method stub
		
		
		
		File f1 = dashboard.selectedFile;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		String[] words=null;
		String s;
		String tbname="";
		String cName="";
		String cType="";
		boolean cIsNull=false;
		String cLength="";
		boolean cIsPrimaryKey = false;
		String query = "CREATE TABLE IF NOT EXISTS";
		System.out.println(query);
		while((s=br.readLine())!=null) {
			
			//System.out.println(s);
			words = s.split(" ");
		//	System.out.println(words[0]+"DD");
			for(String word : words) {
				
				if(s.toLowerCase().contains("dbtable") && (word.contains("Name=")) && (s.contains("Type=")) ) {
					tbname="";
					for(int i=6;i<word.length()-1;i++) {
						tbname = tbname+word.charAt(i);
					}
					query = query+" "+tbname+"(";
					System.out.println(query);
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Length="))) {
					cLength="";
					for(int i=8;i<word.length()-1;i++) {
						cLength= cLength + word.charAt(i);
						
					}
					System.out.println(cLength);
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Name="))) {
					cName="";
					for(int i=6;i<word.length()-1;i++) {
						cName= cName + word.charAt(i);
						
					}
					System.out.println(cName);
				}
				
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Nullable="))) {
					if(word.contains("false")) {
						cIsNull = false;
					
					}
					else {
						cIsNull=true;
					}
					System.out.println(cIsNull);
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("PrimaryKey="))) {
					if(word.contains("false")) {
						cIsPrimaryKey = false;
					}
					else {
						cIsPrimaryKey=true;
					}
					System.out.println(cIsPrimaryKey);
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Type="))) {
					cType="";
					for(int i=6;i<word.length()-1;i++) {
						cType= cType + word.charAt(i);
						
					}
					System.out.println(cType);
					if(cType.equals("integer")) {
					query = query+cName+" int";
					}
					else {
						query = query+cName+" "+cType+"("+"255"+")";
					}
					if(cIsNull==false) {
						query = query+" NOT NULL, ";
					}
					else {
						query = query+", ";
					}
				}
				
				if(word.equals("")) {
					
				}
			}
			if(s.contains("</DBTable>")) {
				System.out.println("table done");
				String sqlQuery ="";
				for(int i=0;i<query.length()-2;i++) {
					sqlQuery = sqlQuery+query.charAt(i);
				}
				sqlQuery = sqlQuery+ ")";
				query = "CREATE TABLE IF NOT EXISTS";
				System.out.println(sqlQuery);
                                DBCreation dc = new DBCreation();
				dc.createTables(PackageName,sqlQuery);
			}
		}
		fr.close();
		dashboard.t1.start();
	//   createTables(nam);
    }

  
}
