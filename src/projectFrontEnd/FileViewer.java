/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFrontEnd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bilal
 */
public class FileViewer {
    public void ShowData(File selectedFile){
        String str ;
             byte bt[];   
            try {
                bt = Files.readAllBytes(selectedFile.toPath());
                str=new String(bt,"UTF-8");
               dashboard.jTextArea1.setText(str);
            } catch (IOException ex) {
                Logger.getLogger(dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
