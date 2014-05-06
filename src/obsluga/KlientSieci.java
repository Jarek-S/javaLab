/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            System.out.println("    Łączenie    :   Sukces");
            System.out.println(obsluga.Sterownia.LINIA);
            System.out.println("Czy chcesz pobrać dane? [T]/[N]");
            Scanner we = new Scanner(System.in);
            String wybor = we.nextLine();
            while (!(wybor.equalsIgnoreCase("T")) && !(wybor.equalsIgnoreCase("N"))) {
                wybor = we.nextLine();
            }
            if (wybor.equalsIgnoreCase("T")) {
                System.out.println();
                System.out.println(obsluga.Sterownia.LINIA);
                System.out.println("Komunikat z serwera: " + komunikatSerwera);
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
