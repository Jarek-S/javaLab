/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Set;
import static obsluga.Sterownia.LINIA;

/**
 *
 * @author Agnieszka
 */
public class KlientRMI {

    Manager klient;
    HashMap<String,String> users;

    public KlientRMI() {
        try {
            klient = (Manager) Naming.lookup("//localhost:7777/Zarzadca");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PokazDane() {
        try {
            users = klient.odczytajPlik();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
        for (HashMap.Entry<String,String> entry : users.entrySet()) {
            System.out.println("Użytkownik      :       "+entry.getKey());
            System.out.println("Hasło           :       "+entry.getValue());
            System.out.println(LINIA);
        }

    }

    public void DodajUsera() {
        try {
            users = klient.odczytajPlik();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UsunUsera() {
        try {
            users = klient.odczytajPlik();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
