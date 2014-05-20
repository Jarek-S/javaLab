/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Agnieszka
 */
public class KlientSieci {

    public void doRoboty() {
        try {
            Socket gniazdo = new Socket("localhost", 6666);
            InputStreamReader czytnikStrumienia = new InputStreamReader(gniazdo.getInputStream());
            BufferedReader czytnik = new BufferedReader(czytnikStrumienia);
            String komunikatSerwera = czytnik.readLine();
            System.out.println();
            System.out.println("    Adres       :   " + gniazdo.getInetAddress());
            System.out.println("    Port        :   " + gniazdo.getPort());
            System.out.println(obsluga.Sterownia.LINIA);
            System.out.println("    Łączenie    :   " + komunikatSerwera);
            System.out.println(obsluga.Sterownia.LINIA);
            System.out.println("Czy chcesz pobrać dane? [T]/[N]");
            Scanner we = new Scanner(System.in);
            String wybor = we.nextLine();
            while (!(wybor.equalsIgnoreCase("T")) && !(wybor.equalsIgnoreCase("N"))) {
                wybor = we.nextLine();
            }
            if (wybor.equalsIgnoreCase("T")) {
                String wiersz = null;
                try {
                ResultSet lista = null;
                ObjectInputStream ois = new ObjectInputStream(gniazdo.getInputStream());
                System.out.println(obsluga.Sterownia.LINIA);
                lista = (ResultSet) ois.readObject();
                while (lista.next()) {
                System.out.println(obsluga.Sterownia.LINIA);
                System.out.println("Id               : " + lista.getInt("id_pracownika"));
                System.out.println("Imię             : " + lista.getString("imie"));
                System.out.println("Nazwisko         : " + lista.getString("nazwisko"));
                System.out.println("Wynagrodzenie    : " + lista.getString("pensja"));
                System.out.println("Stanowisko       : " + lista.getString("stanowisko"));
                System.out.println("Telefon          : " + lista.getString("telefon"));

                if (lista.getString("stanowisko").equalsIgnoreCase("Dyrektor")) {
                    System.out.println("Dodatek służbowy : " + lista.getString("dodatek"));
                    System.out.println("Karta służbowa   : " + lista.getString("karta_nr"));
                    System.out.println("Limit kosztów    : " + lista.getString("limit"));
                } else {
                    System.out.println("Prowizja %       : " + lista.getString("prowizja"));
                    System.out.println("Limit prowizji   : " + lista.getString("limit"));
                }}
                } catch(Exception e) {
                        e.printStackTrace();
                        }
                
                while ((wiersz = czytnik.readLine()) != null) {
                    System.out.println(wiersz);
                }
                
                System.out.println(obsluga.Sterownia.LINIA);
                System.out.println("Pobieranie zakończone");
                System.out.println(obsluga.Sterownia.LINIA);

            }
            czytnik.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PobierzDane() {
        KlientSieci klient = new KlientSieci();
        klient.doRoboty();
    }
}
