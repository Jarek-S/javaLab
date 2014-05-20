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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
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
                System.out.println(obsluga.Sterownia.LINIA);
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
