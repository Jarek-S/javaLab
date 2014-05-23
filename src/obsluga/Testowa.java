/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obsluga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.*;
import java.util.HashMap;
import java.util.Map;
import static obsluga.Sterownia.LINIA;

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
            HashMap<String,String> dane = test.odczytajPlik();
            /*File tajnyPlik = new File("C:\\daneDB\\users.txt");
            FileReader czytnikPliku = new FileReader(tajnyPlik);
            BufferedReader czytnik = new BufferedReader(czytnikPliku);
            String wiersz = null;
            String[] klocki = null;
            HashMap<String,String> mapa = new HashMap<>();
            while ((wiersz = czytnik.readLine()) != null) {
                klocki = wiersz.split("/");
                System.out.println(klocki[0]+"   "+klocki[1]);
                mapa.put(klocki[0], klocki[1]);
            }*/
            System.out.println("");
            try {
        for (HashMap.Entry<String,String> entry : dane.entrySet()) {
            System.out.println("Użytkownik      :       "+entry.getKey());
            System.out.println("Hasło           :       "+entry.getValue());
            System.out.println(LINIA);
        }
            }
        catch(Exception e) {
                e.printStackTrace();
                }
            test.napiszKomunikat("Wyświetlono dane z pliku");
            //test.zapiszDoPliku("To był test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}