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

//clases para  XML

/**
 *
 * @author eljugo
 */
public class ArchivoBaseGenerateDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    public ArchivoBaseGenerateDaoJdbc() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();

        //seccion de la DB
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        //dataSource.setPassword("mysqlroot");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");
        this.dataSource = dataSource;

        //seccion XML
    }

    //*** se utilizó void
    public void generateXML() {

        //querys
        String sql = "select * from LATABLA";

        try {
            //seccion de preparacion de la query
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //seccion de nodo raiz

            while (resultSet.next()) {

                //aquí se hace la magia para el XML
            }

            //se genera el xml físico
        } catch (Exception e) {
            //*** se quitó el return porque el método es void
            System.out.println(e.getMessage());
        }

    }

}
