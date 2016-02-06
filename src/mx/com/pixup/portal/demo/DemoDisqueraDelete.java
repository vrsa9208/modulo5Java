/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author JAVA-07
 */
public class DemoDisqueraDelete {

    public static void main(String[] args) {
        // Entrada estándar -> consola
        InputStreamReader isr = new InputStreamReader(System.in);
        // Objeto de ayuda para leer de la Entrada estándar
        BufferedReader br = new BufferedReader(isr);
        // Método para leer línea a línea de la Entrada estándar

        System.out.println("Bienvenido a Pixup");
        System.out.println("Mantenimiento de Catálogo Disquera");
        System.out.println("Por favor ingrese la disquera que se quiere eliminar");

        Connection connection = null;
        Statement statement = null;
        try {
            String disquera = br.readLine();
            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
            dataSource.setUrl("jdbc:mysql://localhost:3306/pixup");

            connection = dataSource.getConnection();

            statement = connection.createStatement();
            String sql = "delete from disquera where nombre = '"
                    + disquera + "'";
            statement.execute(sql);
            
            System.out.println(disquera + " Eliminada correctamente");
            //cargando driver
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No disponible, intente más tarde!!!");
        } finally {
            //cerrar en orden result set, statement y luego conexion
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    
                }
            }
            if (connection != null) {

                try {
                    connection.close();
                } catch (SQLException ex) {
                    
                }
            }

        }

    }

}
