/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.dao;

import java.util.List;
import mx.com.pixup.portal.model.FormaPago;

/**
 *
 * @author JAVA-07
 */
public interface FormaPagoDao {
    
    FormaPago insertFormaPago(FormaPago formaPago);
    
    List<FormaPago> findAllFormaPagos();
    
    FormaPago findById(Integer id);
    
    FormaPago updateFormaPago(FormaPago formaPago);
    
    void deleteFormaPago(FormaPago formaPago);
    
    
}
