/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao;

import com.sun.org.apache.xerces.internal.parsers.XMLParser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

//clases para XML
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.JDOMParseException;
/**
 *
 * @author eljugo
 */
public class ArtistaParserDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private SAXBuilder xmlParser;
    private Document xmlDocumento;
    
    public ArtistaParserDaoJdbc() throws IOException, JDOMException {
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
        this.xmlDocumento = this.xmlParser.build("C:\\Users\\JAVA-08\\Documents\\VOSS\\Modulo 5\\pixup-dao-xml\\src\\testXML\\artistas2.xml");
    }

    //*** se utilizó void
    public void parserXML() {

        try {

            //variables BD
            String sql = "INSERT INTO artista VALUES (?,?,?)";
            PreparedStatement preparedStatement;
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            //se obtiene elemento raiz
            Element artistas = this.xmlDocumento.getRootElement();
            //elementos 2do nivel
            List<Element> listaArtistas = artistas.getChildren();
            Iterator<Element> i = listaArtistas.iterator();
            
            while(i.hasNext()){
                Element artista = i.next();
                
                //Elementos de tercer nivel
                Attribute id = artista.getAttribute("id");
                Element nombre = artista.getChild("nombre");
                Element descripcion = artista.getChild("descripcion");
                
                //construye parametros de la query
                preparedStatement = connection.prepareStatement(sql);           
                preparedStatement.setInt(1, id.getIntValue());
                preparedStatement.setString(2, nombre.getText());
                preparedStatement.setString(3, descripcion.getText());
                
                preparedStatement.execute();
            }
            
            
            //preparedStatement = connection.prepareStatement(sql);           
            // preparedStatement.setInt(1, valor);
            //  preparedStatement.execute();
            
            connection.commit();
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
