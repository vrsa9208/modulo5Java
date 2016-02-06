/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.IOException;
import mx.com.pixup.portal.dao.ArtistaParserDaoJdbc;
import org.jdom2.JDOMException;

/**
 *
 * @author JAVA-08
 */
public class DemoArtistaParserXML {
    public static void main(String[] args) throws IOException, JDOMException {
        ArtistaParserDaoJdbc losArtistas = new ArtistaParserDaoJdbc();
        
        losArtistas.parserXML();
    }
}
