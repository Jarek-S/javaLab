/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obsluga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author Agnieszka
 */
public class ManagerImpl extends UnicastRemoteObject implements Manager {
    
    public ManagerImpl() throws RemoteException {
    }

    public void zapiszDoPliku(String dane) {
        try {
            BufferedWriter pisarz = new BufferedWriter(new FileWriter("C:\\daneDB\\users.txt"));
            pisarz.write(dane);
            pisarz.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap odczytajPlik() {
        HashMap<String,String> dane = null;
        try {
            File tajnyPlik = new File("C:\\daneDB\\users.txt");
            FileReader czytnikPliku = new FileReader(tajnyPlik);
            BufferedReader czytnik = new BufferedReader(czytnikPliku);
            String wiersz = null;
            String[] klocki = null;
            while ((wiersz = czytnik.readLine()) != null) {
                klocki = wiersz.split("/");
                dane.put(klocki[0], klocki[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dane;
    }
    
    public void napiszKomunikat() {
        System.out.println("To jest metoda RMI");
    }
}

