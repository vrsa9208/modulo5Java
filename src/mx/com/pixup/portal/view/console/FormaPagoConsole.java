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
import mx.com.pixup.portal.model.FormaPago;
import mx.com.pixup.portal.service.FormaPagoService;
import mx.com.pixup.portal.service.FormaPagoServiceImpl;

/**
 *
 * @author JAVA-07
 */
public class FormaPagoConsole {
    
    private FormaPagoService formaPagoService;
    
    public FormaPagoConsole(){
        this.formaPagoService = new FormaPagoServiceImpl();
    }
    
    public void insert(FormaPago formaPago){
        System.out.println("Inserte el nombre de la nueva FormaPago");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String nombreFormaPago = br.readLine();
            
            formaPago.setDescripcion(nombreFormaPago);
            
            FormaPago newFormaPago = this.formaPagoService.guardarFormaPago(formaPago);
            
            if(newFormaPago != null){
                System.out.println("FormaPago insertada de manera exitosa con id "+newFormaPago.getId());
            }else{
                System.out.println("No es posible añadir formaPago intente más tarde");
            }
            
            
            
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion");
        }
        
    }
    
    public void delete(FormaPago formaPago){
        System.out.println("Inserte el id de la FormaPago a eliminar");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String idFormaPago = br.readLine();
            
            formaPago = new FormaPago();
            formaPago.setId(Integer.parseInt(idFormaPago));
            
            this.formaPagoService.borrarFormaPago(formaPago);
            
            System.out.println("FormaPago eliminada de manera exitosa "+idFormaPago);
            
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion al eliminar formaPago");
        }
        
    }
    
    public void selectAll(){
        List<FormaPago> formaPagos = this.formaPagoService.obtenerFormaPagos();
        System.out.println("ID\tDescripcion");
        for(FormaPago formaPago : formaPagos){
            System.out.println(formaPago.getId() + "\t" + formaPago.getDescripcion());
        }
    }
    
    public void update(FormaPago formaPago){
        selectAll();
        System.out.println("Inserte el id de la FormaPago a actualizar");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            String idFormaPago = br.readLine();
            String nombreFormaPago;
            
            System.out.println("Inserte el nuevo nombre de la FormaPago");
            nombreFormaPago = br.readLine();
            
            formaPago = new FormaPago();
            formaPago.setId(Integer.parseInt(idFormaPago));
            formaPago.setDescripcion(nombreFormaPago);
            
            this.formaPagoService.actualizarFormaPago(formaPago);
            
            System.out.println("FormaPago actualizada de manera exitosa "+idFormaPago);
            selectAll();
        } catch (IOException ex) {
            System.out.println("Ocurrio una excepcion al actualizar formaPago");
        }
        
    }
    
    public boolean menuFormaPago(){
        FormaPago formaPago = new FormaPago();
        System.out.println("Bienvenido a PixUp");
        System.out.println("Catalogo FormaPago");
        
        System.out.println("Acción a Realizar");
        
        System.out.println("1)Insert\n2)Delete\n3)FindAll\n4)Update");
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        try {
            String line = br.readLine();
            int seleccion = Integer.parseInt(line);
            switch (seleccion){
                case 1:
                    this.insert(formaPago);
                    break;
                case 2:
                    this.selectAll();
                    this.delete(formaPago);
                    break;
                case 3:
                    this.selectAll();
                    break;
                case 4:
                    this.update(formaPago);
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
            showMenu = this.menuFormaPago();
        }while(showMenu);
    }
    
    /*public static void main(String[] args) {
        FormaPagoConsole dc = new FormaPagoConsole();
        dc.start();
    }*/
}
