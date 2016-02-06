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
public class EstadoMunicipioParserDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private SAXBuilder xmlParser;
    private Document xmlDocumento;
    
    public EstadoMunicipioParserDaoJdbc() throws IOException, JDOMException {
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
        this.xmlDocumento = this.xmlParser.build("C:\\Users\\JAVA-08\\Documents\\VOSS\\Modulo 5\\pixup-dao-xml\\src\\testXML\\municipiosBajaCaliforniaSur.xml");
    }

    //*** se utilizó void
    public void parserXML() {

        try {

            //variables BD
            String sql = "INSERT INTO estado VALUES (?,?)";
            String sqlM = "INSERT INTO municipio VALUES (?,?,?)";
            PreparedStatement preparedStatement;
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            //se obtiene elemento raiz
            Element estado = this.xmlDocumento.getRootElement();
            //elementos 2do nivel
            Element nombre = estado.getChild("nombre");
            Element municipios = estado.getChild("municipios");
            Attribute id = estado.getAttribute("id");
            //construye parametros de la query
            preparedStatement = connection.prepareStatement(sql);           
            preparedStatement.setInt(1, id.getIntValue());
            preparedStatement.setString(2, nombre.getText());
            preparedStatement.execute();
            
            List<Element> listaMunicipios = municipios.getChildren("municipio");
            Iterator<Element> i = listaMunicipios.iterator();
            
            while(i.hasNext()){
                Element municipio = i.next();
                //Elementos de tercer nivel
                Attribute idMunicipio = municipio.getAttribute("id");
                String nombreMunicipio = municipio.getText();
                //construye parametros de la query
                preparedStatement = connection.prepareStatement(sqlM);
                preparedStatement.setInt(1, idMunicipio.getIntValue());
                preparedStatement.setString(2, nombreMunicipio);
                preparedStatement.setInt(3, id.getIntValue());
                
                preparedStatement.execute();
            }
            
            connection.commit();
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
