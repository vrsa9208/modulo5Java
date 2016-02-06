/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.service;

import java.util.List;
import mx.com.pixup.portal.model.FormaPago;

/**
 *
 * @author JAVA-07
 */
public interface FormaPagoService {
    FormaPago guardarFormaPago(FormaPago formaPago);
    void borrarFormaPago(FormaPago formaPago);
    FormaPago actualizarFormaPago(FormaPago formaPago);
    List <FormaPago> consultarFormaPago(FormaPago formaPago);
    FormaPago obtenerFormaPago(Integer id);
    FormaPago obtenerFormaPago(String nombreFormaPago);
    List <FormaPago> obtenerFormaPagos();
    
}
