/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obsluga;

import java.rmi.*;

/**
 *
 * @author Agnieszka
 */
public class Testowa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {            
            Manager test = (Manager) Naming.lookup("//localhost:7777/Zarzadca");
            test.napiszKomunikat();
            test.zapiszDoPliku("To był test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}