/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.IOException;
import mx.com.pixup.portal.dao.EstadoMunicipioParserDaoJdbc;
import org.jdom2.JDOMException;

/**
 *
 * @author JAVA-08
 */
public class DemoEstadoMunicipioParserXML {

    public static void main(String[] args) throws IOException, JDOMException {
        EstadoMunicipioParserDaoJdbc ingresoEstado = new EstadoMunicipioParserDaoJdbc();
        
        ingresoEstado.parserXML();
    }
}
