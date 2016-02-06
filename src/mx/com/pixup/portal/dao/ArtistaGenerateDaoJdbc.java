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
public class ArtistaGenerateDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private Document xmlLogico;
    private XMLOutputter xmlFisico;
    private FileWriter archivoFisico;
    
    public ArtistaGenerateDaoJdbc() throws IOException {
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
        this.archivoFisico = new FileWriter("C:\\Users\\JAVA-08\\Documents\\VOSS\\Modulo 5\\pixup-dao-xml\\src\\testXML\\artistasNuevo.xml");
    }

    //*** se utilizó void
    public void generateXML() {

        //querys
        String sql = "select * from artista";

        try {
            //seccion de preparacion de la query
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //seccion de nodo raiz
            Element artistas = new Element("artistas");
            this.xmlLogico.setRootElement(artistas);

            while (resultSet.next()) {
                
                //aquí se hace la magia para el XML
                Element artista = new Element("artista");
                Attribute id = new Attribute("id", Integer.toString(resultSet.getInt("id")));
                artista.setAttribute(id);
                
                Element nombre_artistico = new Element("nombre_artistico");
                nombre_artistico.setText(resultSet.getString("nombre_artistico"));
                artista.addContent(nombre_artistico);
                
                Element descripcion = new Element("descripcion");
                descripcion.setText(resultSet.getString("descripcion"));
                artista.addContent(descripcion);
                
                artistas.addContent(artista);
            }

            //se genera el xml físico
            this.xmlFisico.setFormat(Format.getPrettyFormat());
            this.xmlFisico.output(xmlLogico, archivoFisico);
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
