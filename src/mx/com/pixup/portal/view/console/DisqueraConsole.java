/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.pixup.portal.model.Disquera;
import mx.com.pixup.portal.service.DisqueraService;
import mx.com.pixup.portal.service.DisqueraServiceImpl;

/**
 *
 * @author JAVA-07
 */
public class DisqueraConsole {
    
    private DisqueraService disqueraService;
    
    public DisqueraConsole(){
        this.disqueraService = new DisqueraServiceImpl();
    }
    
    public void insert(Disquera disquera){
        System.out.println("Inserte el nombre de la nueva Disquera");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String nombreDisquera = br.readLine();
            
            disquera.setNombre(nombreDisquera);
            
            Disquera newDisquera = this.disqueraService.guardarDisquera(disquera);
            
            if(newDisquera != null){
                System.out.println("Disquera insertada de manera exitosa con id "+newDisquera.getId());
            }else{
                System.out.println("No es posible añadir disquera intente más tarde");
            }
            
            
            
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion");
        }
        
    }
    
    public void delete(Disquera disquera){
        System.out.println("Inserte el id de la Disquera a eliminar");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String idDisquera = br.readLine();
            
            disquera = new Disquera();
            disquera.setId(Integer.parseInt(idDisquera));
            
            this.disqueraService.borrarDisquera(disquera);
            
            System.out.println("Disquera eliminada de manera exitosa "+idDisquera);
            
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion al eliminar disquera");
        }
        
    }
    
    public void selectAll(){
        List<Disquera> disqueras = this.disqueraService.obtenerDisqueras();
        System.out.println("ID\t\tNombre");
        for(Disquera disquera : disqueras){
            System.out.println(disquera.getId() + "\t" + disquera.getNombre());
        }
    }
    
    public void update(Disquera disquera){
        selectAll();
        System.out.println("Inserte el id de la Disquera a actualizar");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String idDisquera = br.readLine();
            String nombreDisquera;
            
            System.out.println("Inserte el nuevo nombre de la Disquera");
            nombreDisquera = br.readLine();
            
            disquera = new Disquera();
            disquera.setId(Integer.parseInt(idDisquera));
            disquera.setNombre(nombreDisquera);
            
            this.disqueraService.actualizarDisquera(disquera);
            
            System.out.println("Disquera actualizada de manera exitosa "+idDisquera);
            selectAll();
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion al actualizar disquera");
        }
        
    }

    
    public boolean menuDisquera(){
        Disquera disquera = new Disquera();
        System.out.println("Bienvenido a PixUp");
        System.out.println("Catalogo Disquera");
        
        System.out.println("Acción a Realizar");
        
        System.out.println("1)Insert\n2)Delete\n3)FindAll\n4)Update");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String line = br.readLine();
            int seleccion = Integer.parseInt(line);
            switch (seleccion){
                case 1:
                    this.insert(disquera);
                    break;
                case 2:
                    this.selectAll();
                    this.delete(disquera);
                    break;
                case 3:
                    this.selectAll();
                    break;
                case 4:
                    this.update(disquera);
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
            showMenu = this.menuDisquera();
        }while(showMenu);
    }
    
}
