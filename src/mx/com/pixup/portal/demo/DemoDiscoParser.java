/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import mx.com.pixup.portal.dao.DiscoPaisGenerateDaoJdbc;
import mx.com.pixup.portal.dao.DiscoParserDaoJdbc;
import org.jdom2.JDOMException;

/**
 *
 * @author vrsa9208
 */
public class DemoDiscoParser {

    public static void main(String[] args) throws IOException, JDOMException {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el archivo XML a importar)");
        FileFilter filter = new FileNameExtensionFilter("Archivo XML", "xml");
        chooser.addChoosableFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            String direccion = chooser.getSelectedFile().getAbsolutePath();

            if (!direccion.substring(direccion.length() - 4, direccion.length()).equals(".xml")) {
                JOptionPane.showMessageDialog(null, "El archivo leido debe ser XML");
            } else {
                DiscoParserDaoJdbc parser = new DiscoParserDaoJdbc(direccion);
                parser.parserXML();
                JOptionPane.showMessageDialog(null, "Importación completa!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al importar.");
        }
        System.exit(0);
    }
}
