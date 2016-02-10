/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//clases para XML
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.JDOMParseException;
/**
 *
 * @author eljugo
 */
public class DiscoParserDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private SAXBuilder xmlParser;
    private Document xmlDocument;
    
    public DiscoParserDaoJdbc(String archivoFisico) throws IOException, JDOMException {
        BasicDataSource dataSource = new BasicDataSource();

        //seccion de la DB
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        //dataSource.setPassword("mysqlroot");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");
        this.dataSource = dataSource;

        //seccion generate XML
        this.xmlParser = new SAXBuilder();
        this.xmlDocument = this.xmlParser.build(archivoFisico);
    }

    //*** se utilizó void
    public void parserXML() {

        try {

            //variables BD
            String sql = "INSERT INTO disco VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement;
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            //se obtiene elemento raiz
            Element discos = this.xmlDocument.getRootElement();
            //elementos 2do nivel
            List<Element> listaDiscos = discos.getChildren();
            Iterator<Element> i = listaDiscos.iterator();
            
            while(i.hasNext()){
                Element disco = i.next();
                
                Attribute id = disco.getAttribute("id");
                Attribute idioma = disco.getAttribute("idioma");
                Attribute pais = disco.getAttribute("pais");
                Attribute disquera = disco.getAttribute("disquera");
                Attribute genero = disco.getAttribute("genero");
                
                //Elementos de 3er nivel
                Element titulo = disco.getChild("titulo");
                Element fechalanzamiento = disco.getChild("fechalanzamiento");
                Element precio = disco.getChild("precio");
                Element cantidad = disco.getChild("cantidad");
                Element promocion = disco.getChild("promocion");
                Element iva = disco.getChild("iva");
                
                //construye parametros de la query
                preparedStatement = connection.prepareStatement(sql);           
                preparedStatement.setInt(1, id.getIntValue());
                preparedStatement.setString(2, titulo.getText());
                preparedStatement.setString(3, fechalanzamiento.getText());
                preparedStatement.setDouble(4, Double.parseDouble(precio.getText()));
                preparedStatement.setInt(5, Integer.parseInt(cantidad.getText()));
                preparedStatement.setInt(6, idioma.getIntValue());
                preparedStatement.setInt(7, pais.getIntValue());
                preparedStatement.setInt(8, disquera.getIntValue());
                preparedStatement.setInt(9, genero.getIntValue());
                preparedStatement.setInt(10, Integer.parseInt(promocion.getText()));
                preparedStatement.setInt(11, Integer.parseInt(iva.getText()));
                
                preparedStatement.execute();
            }
            
            
            connection.commit();
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
