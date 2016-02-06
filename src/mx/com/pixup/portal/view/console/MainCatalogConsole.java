/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.view.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Rodrigo
 */
public class MainCatalogConsole {
    
    public boolean menuCatalogos(){
        System.out.println("Bienvenido a PixUp");
        System.out.println("Administraciòn de Catalogos");
        
        System.out.println("Acción a Realizar");
        
        System.out.println("1)Menu Catalogo de Forma de Pago\n2)Menu Catalogo Disquera");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String line = br.readLine();
            int seleccion = Integer.parseInt(line);
            switch (seleccion){
                case 1:
                    this.catalogoFormaPago();
                    break;
                case 2:
                    this.catalogoDisquera();
                    break;
                default:
                    return false;
            }
                //dc.insert(formaPago);
        } catch (Exception ex) {
            return false;
        }
        return true;        
    }
    
    public void start(){
        boolean showMenu;
        
        do{
            System.out.println("");
            showMenu = this.menuCatalogos();
        }while(showMenu);
    }
    
    public void catalogoFormaPago(){
        new FormaPagoConsole().start();
    }
    
    public void catalogoDisquera(){
        new DisqueraConsole().start();
    }
    
    public static void main(String[] args) {
        new MainCatalogConsole().start();
    }
    
}
