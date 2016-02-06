package mx.com.pixup.portal.demo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;

/**
 *
 * @author eljugo
 */
public class ValidateXSDXMLCancion {
    public static void main(String[] args) throws  IOException, JDOMException  {
        
      try {
          //Create the XMLReaderJDOMFacotory directly using the schema file instead of 'Schema'
        String schemaFile = "";
        String file = "";
        
        XMLReaderJDOMFactory factory2 = new XMLReaderXSDFactory(schemaFile);
        SAXBuilder sb2 = new SAXBuilder(factory2);
        Document doc2 = sb2.build(new File(file));
        System.out.println("ok");
         System.out.println(doc2.getRootElement().getName());
      } catch (org.jdom2.input.JDOMParseException e) {
          e.getLineNumber();
          System.out.println("error");
      }
    }
}
