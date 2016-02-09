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
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

/**
 *
 * @author vrsa9208
 */
public class DiscoPaisGenerateDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    private Document xmlLogico;
    private XMLOutputter xmlFisico;
    private FileWriter archivoFisico;
    
    public DiscoPaisGenerateDaoJdbc(String archivo) throws IOException {
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
        this.archivoFisico = new FileWriter(archivo);
    }

    //*** se utilizó void
    public void generateXML() {

        //querys
        String sql = "SELECT id_idioma, id_disquera, pais.id as id_pais, pais.nombre as pais,\n" +
                        "disco.titulo FROM disco\n" +
                        "INNER JOIN pais ON pais.id = disco.id_pais";

        try {
            //seccion de preparacion de la query
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //seccion de nodo raiz
            Element discos = new Element("discos");
            this.xmlLogico.setRootElement(discos);

            while (resultSet.next()) {
                //Elementos de Segundo Orden
                Element disco = new Element("disco");
                
                Attribute idioma = new Attribute("idioma", Integer.toString(resultSet.getInt("id_idioma")));
                disco.setAttribute(idioma);
                
                Attribute disquera = new Attribute("disquera", Integer.toString(resultSet.getInt("id_disquera")));
                disco.setAttribute(disquera);
                
                //Elementos de Tercer Orden
                Element pais = new Element("pais");
                
                Attribute id = new Attribute("id", Integer.toString(resultSet.getInt("id_pais")));
                pais.setAttribute(id);
                
                pais.setText(resultSet.getString("pais"));
                disco.addContent(pais);
                
                Element titulo = new Element("titulo");
                titulo.setText(resultSet.getString("titulo"));
                
                disco.addContent(titulo);
                
                discos.addContent(disco);
                //aquí se hace la magia para el XML
            }

            //se genera el xml físico
            this.xmlFisico.setFormat(Format.getPrettyFormat());
            this.xmlFisico.output(this.xmlLogico, this.archivoFisico);
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
