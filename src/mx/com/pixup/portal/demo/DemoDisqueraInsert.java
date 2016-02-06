/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author JAVA-07
 */
public class DemoDisqueraInsert {

    public static void main(String[] args) {
        // Entrada estándar -> consola
        InputStreamReader isr = new InputStreamReader(System.in);
        // Objeto de ayuda para leer de la Entrada estándar
        BufferedReader br = new BufferedReader(isr);
        // Método para leer línea a línea de la Entrada estándar

        System.out.println("Bienvenido a Pixup");
        System.out.println("Mantenimiento de Catálogo Disquera");
        System.out.println("Por favor ingrese la disquera que se quiere insertar");

        Connection connection = null;
        Statement statement = null;
        try {
            String disquera = br.readLine();

            //cargando driver
            Class.forName("com.mysql.jdbc.Driver");

            //estableciendo conexion
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pixup", "root", "admin");

            //Interactuando con la base
            statement = connection.createStatement();

            String sql = "insert into disquera (nombre) values ('"
                    + disquera
                    + "')";

            //Ejecutar sentencia sql
            statement.execute(sql);
            System.out.println("Disquera "
                    + disquera
                    + " insertada de forma exitosa");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No disponible, intente más tarde!!!");
        } finally {
            //liberar recursos
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
