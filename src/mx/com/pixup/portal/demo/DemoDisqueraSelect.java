/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JAVA-07
 */
public class DemoDisqueraSelect {

    public static void main(String[] args) {
        // Entrada estándar -> consola
        //InputStreamReader isr = new InputStreamReader(System.in);
        // Objeto de ayuda para leer de la Entrada estándar
        //BufferedReader br = new BufferedReader(isr);
        // Método para leer línea a línea de la Entrada estándar

        System.out.println("Bienvenido a Pixup");
        System.out.println("Mantenimiento de Catálogo Disquera");
        System.out.println("Listado de Disqueras");

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
            dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");

            connection = dataSource.getConnection();

            statement = connection.createStatement();
            
            String sql = "select * from disquera";

            //Ejecutar sentencia sql
            rs = statement.executeQuery(sql);
            System.out.println("ID:\t     NOMBRE      \t");
            while (rs.next()){
                System.out.print(rs.getInt("id")+"\t");
                System.out.println(rs.getString("nombre")+"\t");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No disponible, intente más tarde!!!");
        } finally {
            //liberar recursos en  orden, result set, statement y connection
            if (rs != null)
                try {
                    rs.close();
            } catch (SQLException ex) {
                
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

}
