/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import java.io.IOException;

//clases para  XML
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

/**
 *
 * @author eljugo
 */
public class MunicipioGenerateDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private Document xmlLogico;
    private XMLOutputter xmlFisico;
    private FileWriter archivoFisico;
    
    public MunicipioGenerateDaoJdbc() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();

        //seccion de la DB
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        //dataSource.setPassword("mysqlroot");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");
        this.dataSource = dataSource;

        //seccion XML
        this.xmlLogico = new Document();
        this.xmlFisico = new XMLOutputter();
        this.archivoFisico = new FileWriter("C:\\Users\\JAVA-08\\Documents\\VOSS\\Modulo 5\\pixup-dao-xml\\src\\testXML\\municipio1.xml");
    }

    //*** se utilizó void
    public void generateXML(int idMunicipioPasado) {

        //querys
        String sql = "SELECT * from municipio WHERE id = ?";

        try {
            //seccion de preparacion de la query
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idMunicipioPasado);
            ResultSet resultSet = preparedStatement.executeQuery();
            //seccion de nodo raiz

            resultSet.next();
            /*while (resultSet.next()) {

                //aquí se hace la magia para el XML
            }*/
            Element municipio = new Element("municipio");
            Attribute id = new Attribute("id", Integer.toString(resultSet.getInt("id")));
            
            this.xmlLogico.setRootElement(municipio);
            municipio.setAttribute(id);
            
            //Elementos de 2do nivel
            Element nombre = new Element("nombre");
            nombre.setText(resultSet.getString("nombre"));
            Element id_estado = new Element("id_estado");
            id_estado.setText(Integer.toString(resultSet.getInt("id_estado")));
            
            municipio.addContent(nombre);
            municipio.addContent(id_estado);
            
            //se genera el xml físico
            this.xmlFisico.setFormat(Format.getRawFormat());
            this.xmlFisico.output(this.xmlLogico, this.archivoFisico);
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
