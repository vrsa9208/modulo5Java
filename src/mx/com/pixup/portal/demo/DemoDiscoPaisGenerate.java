/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mx.com.pixup.portal.dao.DiscoPaisGenerateDaoJdbc;

/**
 *
 * @author vrsa9208
 */
public class DemoDiscoPaisGenerate {
    
    public static void main(String[] args) throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el directorio y nombre del archivo (agregar extensión)");
        
        
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            
            String direccion = chooser.getSelectedFile().getAbsolutePath();
            
            if(!direccion.substring(direccion.length() - 4, direccion.length()).equals(".xml")){
                JOptionPane.showMessageDialog(null, "La extensión del archivo generado debe ser .xml");
            }
            else{
                DiscoPaisGenerateDaoJdbc discoPais = new DiscoPaisGenerateDaoJdbc(direccion);
                discoPais.generateXML();
                JOptionPane.showMessageDialog(null, direccion + " generado!");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "NO se genero el archivo XML.");
        }
        System.exit(0);
    }
}
