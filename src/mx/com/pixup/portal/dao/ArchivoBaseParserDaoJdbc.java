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
/**
 *
 * @author eljugo
 */
public class ArchivoBaseParserDaoJdbc {

    private DataSource dataSource;

    //declaracion objetos XML
    public ArchivoBaseParserDaoJdbc() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();

        //seccion de la DB
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        //dataSource.setPassword("mysqlroot");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");
        this.dataSource = dataSource;

        //seccion generate XML
    }

    //*** se utilizó void
    public void parserXML() {

        try {

            //variables BD
            String sql = "INSERT INTO LATABLA VALUES (?)";
            PreparedStatement preparedStatement;
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            //se obtiene elemento raiz
            //elementos 2do nivel
            //construye parametros de la query
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
