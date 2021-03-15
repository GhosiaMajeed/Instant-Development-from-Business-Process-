/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFrontEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import static projectFrontEnd.dashboard.PackageName;
import static projectFrontEnd.dashboard.selectedFile;

/**
 *
 * @author Bilal
 */
public class WebservicesCreation {
    public void createWebServices() throws Exception{
    //Model
		
		try {
		
		File xml = dashboard.selectedFile;
		FileReader fr = new FileReader(xml);
		BufferedReader br = new BufferedReader(fr);
	
		String[] words=null;
		String s;
		String tbname="";
		String cName="";
		String cType="";
		File modelfile;
		FileWriter fw;
		PrintWriter pw;
		while((s=br.readLine())!=null) {
			words = s.split(" ");
			for(String word : words) {
				if(s.toLowerCase().contains("dbtable") && (word.contains("Name=")) && (s.contains("Type=")) ) {
					tbname="";
					for(int i=6;i<word.length()-1;i++) {
						tbname = tbname+word.charAt(i);
					}
					File dir  = new File(PackageName+"\\"+tbname+"\\model");
					if(!dir.exists()) {
						dir.mkdirs();
					}
					modelfile = new File(PackageName+"\\"+tbname+"\\model\\"+tbname+"Model.java");
					fw = new FileWriter(modelfile.getAbsoluteFile(), true);
					pw = new PrintWriter(fw);
					pw.println("import javax.persistence.*;");
					pw.println("\n\n");
					pw.println("@Entity");
					pw.println("@Table(name = \"TBL"+tbname.toUpperCase()+"\")");
					pw.println("\n\n");
					pw.println("public class "+tbname.toUpperCase()+" {");
					pw.println("\n");
					pw.println("@Id");
					pw.println("@GeneratedValue(strategy = GenerationType.IDENTITY)");
					
					pw.close();
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Name="))) {
					cName="";
					for(int i=6;i<word.length()-1;i++) {
						cName= cName + word.charAt(i);
						
					}
					System.out.println(cName);
				}
				if(s.toLowerCase().contains("dbcolumn") && (word.startsWith("Type="))) {
					modelfile = new File(PackageName+"\\"+tbname+"\\model\\"+tbname+"Model.java");
					fw = new FileWriter(modelfile.getAbsoluteFile(), true);
					pw = new PrintWriter(fw);
					cType="";
					for(int i=6;i<word.length()-1;i++) {
						cType= cType + word.charAt(i);
						
					}
					if(cName.equalsIgnoreCase("ID")) {
						
						pw.println("private long "+tbname.toUpperCase()+"_ID;");
						pw.println("");
						pw.println("public void set"+tbname.toUpperCase()+"_ID(long "+tbname+"_ID) {");
						pw.println(tbname.toUpperCase()+"_ID = "+tbname+"_ID;");
						pw.println("}");
						pw.println("");
						pw.println("public long get"+tbname.toUpperCase()+"_ID() {");
						pw.println("return "+tbname.toUpperCase()+"_ID;");
						pw.println("}");
						pw.println("");
					}
					else {
						if(cType.equalsIgnoreCase("int")) {
							pw.println("@Column(name = \""+cName.toUpperCase()+"\"_");
							pw.println("private long "+cName.toUpperCase()+";");
							pw.println("");
							pw.println("public long get"+cName.toUpperCase()+"(){");
							pw.println("return "+cName.toUpperCase()+";");
							pw.println("}");
							pw.println("");
							pw.println("public void set"+cName.toUpperCase()+"(long "+cName+") {");
							pw.println(cName.toUpperCase()+" = "+cName+";");
							pw.println("}");
							pw.println("");
							System.out.println("aho");
						}
						else {
							pw.println("@Column(name = \""+cName.toUpperCase()+"\")");
							pw.println("private String "+cName.toUpperCase()+";");
							pw.println("");
							pw.println("public String get"+cName.toUpperCase()+"(){");
							pw.println("return "+cName.toUpperCase()+";");
							pw.println("}");
							pw.println("");
							pw.println("public void set"+cName.toUpperCase()+"(String "+cName+") {");
							pw.println(cName.toUpperCase()+" = "+cName+";");
							pw.println("}");
							pw.println("");
							System.out.println("aho");
						}
					}
					pw.close();
				}	
			}
		}
		br.close();
			
		}catch(Exception e) {
			System.out.println("problem");
		}
		
		//Repository 
		
		try {
			File xml = selectedFile;
			FileReader fr = new FileReader(xml);
			BufferedReader br = new BufferedReader(fr);
			String[] words=null;
			String s;
			String tbname="";
			String cName="";
			String cType="";
			File repfile;
			FileWriter fw;
			PrintWriter pw;
			while((s=br.readLine())!=null) {
				words = s.split(" ");
				for(String word : words) {
					if(s.toLowerCase().contains("dbtable") && (word.contains("Name=")) && (s.contains("Type=")) ) {
						tbname="";
						for(int i=6;i<word.length()-1;i++) {
							tbname = tbname+word.charAt(i);
						}
						File dir  = new File(PackageName+"\\"+tbname+"\\repository");
						if(!dir.exists()) {
							dir.mkdirs();
						}
						repfile = new File(PackageName+"\\"+tbname+"\\repository\\"+tbname+"Repository.java");
						fw = new FileWriter(repfile.getAbsoluteFile(), true);
						pw = new PrintWriter(fw);
						pw.println("import java.util.List;");
						pw.println();
						pw.println("import org.springframework.data.jpa.repository.Jparepository;");
						pw.println("import org.springframework.data.jpa.repository.Query;");
						pw.println();
						pw.println("import "+tbname+".model."+tbname+";");
						pw.println("public interface "+tbname+"Repository extends JpaRepository<"+tbname.toUpperCase()+", Long>{");
						pw.println("");
						pw.println("@Query(value=\"select * from TBL"+tbname.toUpperCase()+" where ISACTIVE= \'Y\'\", nativeQuery=true");
						pw.println("List<"+tbname.toUpperCase()+"> findActive();");
						pw.println("}");
						pw.close();
					}
				}
			}
		
		}catch(Exception e) {
			System.out.println("Na g");
		}
                dashboard.t2.start();
}
}
