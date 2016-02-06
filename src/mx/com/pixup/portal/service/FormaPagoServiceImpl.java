/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.service;

import java.util.List;
import mx.com.pixup.portal.dao.FormaPagoDao;
import mx.com.pixup.portal.dao.FormaPagoDaoJdbc;
import mx.com.pixup.portal.model.FormaPago;

/**
 *
 * @author JAVA-07
 */
public class FormaPagoServiceImpl implements FormaPagoService{

    private FormaPagoDao formaPagoDao;
    
    public FormaPagoServiceImpl(){
        this.formaPagoDao = new FormaPagoDaoJdbc();
    }
    
    @Override
    public FormaPago guardarFormaPago(FormaPago formaPago) {
        return formaPagoDao.insertFormaPago(formaPago);
    }

    @Override
    public void borrarFormaPago(FormaPago formaPago) {
        formaPagoDao.deleteFormaPago(formaPago);
    }

    @Override
    public FormaPago actualizarFormaPago(FormaPago formaPago) {
        return formaPagoDao.updateFormaPago(formaPago);
    }

    @Override
    public List<FormaPago> consultarFormaPago(FormaPago formaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FormaPago obtenerFormaPago(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FormaPago obtenerFormaPago(String nombreFormaPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FormaPago> obtenerFormaPagos() {
        return formaPagoDao.findAllFormaPagos();
    }
    
}
