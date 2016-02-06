/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.pixup.portal.demo;

import java.io.IOException;
import mx.com.pixup.portal.dao.MunicipioGenerateDaoJdbc;

/**
 *
 * @author JAVA-08
 */
public class DemoMunicipioGenerateXML {

    public static void main(String[] args) throws IOException {
        MunicipioGenerateDaoJdbc municipioGenerator = new MunicipioGenerateDaoJdbc();
        
        municipioGenerator.generateXML(101);
    }
}
