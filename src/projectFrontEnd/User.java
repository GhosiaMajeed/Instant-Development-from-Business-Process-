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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Bilal
 */
public class User {
    public void Signup(){
        try{
        File reg = new File("reg.txt");
        if(!reg.exists())
            reg.createNewFile();
        FileWriter fw = new FileWriter(reg.getAbsoluteFile(), true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(SignUp.txtEmail.getText()+","+SignUp.txtUsername.getText()+","+SignUp.txtPassword.getText());
        pw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
       public static String email="";
       public static String uname="";
       public static String pword="";
       public static String logt = "false";
    public void Signin(){
        try{
                logt="false";
                File reg = new File("reg.txt");
                if(!reg.exists())
                    reg.createNewFile();
                FileReader fr = new FileReader(reg);
                BufferedReader br = new BufferedReader(fr);
                String words[];
                String s;
                while((s=br.readLine())!=null){
                    words = s.split(",");
                    email = words[0];
                    uname = words[1];
                    pword = words[2];
                    if(SignIn.txtUsername.getText().equals(uname) && SignIn.txtPassword.getText().equals(pword)){
                        logt = "true";
                        break;
                    }
                }
            }catch(Exception e){
                System.out.println("No file");
        }
            
        
        
    }
    public void ChangePassword(String username, String password){
        File f = new File("reg.txt");
        File temp = new File("temp.txt");
        try{
        if(!f.exists()){
            f.createNewFile();
        }
        if(!temp.exists()){
            temp.createNewFile();
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String words[];
        FileWriter fw = new FileWriter(temp.getAbsoluteFile(), true);
        PrintWriter pw = new PrintWriter(fw);
        while((line = br.readLine())!=null){
            words = line.split(",");
            if(words[1].equals(username)){
                pw.println(words[0]+","+words[1]+","+password);
            }
            else{
                pw.println(line);
            }
        }
        pw.close();
        br.close();
        f.delete();
        temp.renameTo(f);
        }catch(Exception e){
            
        }
    }
    public boolean resetPassword(String email){
        File f = new File("reg.txt");
        Boolean sit = false;
        File temp = new File("temp.txt");
        try{
        if(!f.exists()){
            f.createNewFile();
        }
        if(!temp.exists()){
            temp.createNewFile();
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String words[];
        FileWriter fw = new FileWriter(temp.getAbsoluteFile(), true);
        PrintWriter pw = new PrintWriter(fw);
        while((line = br.readLine())!=null){
            words = line.split(",");
            if(words[0].equals(email)){
                String password = JOptionPane.showInputDialog("Enter new Password");
                pw.println(words[0]+","+words[1]+","+password);
                sit = true;
            }
            else{
                pw.println(line);
            }
        }
        pw.close();
        br.close();
        f.delete();
        temp.renameTo(f);
        }catch(Exception e){
            
        }
        return sit;
    }
}
