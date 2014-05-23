/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.rmi.Naming;
import java.util.HashMap;
import java.util.Scanner;
import static obsluga.Sterownia.LINIA;

/**
 *
 * @author Agnieszka
 */
public class KlientRMI {

    Manager klient;
    HashMap<String, String> users;

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
        for (HashMap.Entry<String, String> entry : users.entrySet()) {
            System.out.println("Użytkownik      :       " + entry.getKey());
            System.out.println("Hasło           :       " + entry.getValue());
            System.out.println(LINIA);
        }

    }

    public void DodajUsera() {
        //String dane;
        try {
            String dane = "";
            users = klient.odczytajPlik();
            System.out.println("6.1.1 Dodaj użytkownika");
            System.out.print("Podaj login: ");
            Scanner wejscie = new Scanner(System.in);
            String klucz = wejscie.nextLine();
            System.out.print("Podaj hasło: ");
            String wartosc = wejscie.nextLine();
            users.put(klucz, wartosc);
            for (HashMap.Entry<String, String> entry : users.entrySet()) {
            dane = dane + entry.getKey() + "/" + entry.getValue() + "\n";
            }
            klient.zapiszDoPliku(dane);
            klient.napiszKomunikat("Dodano użytkownika");
            System.out.println("Dodano użytkownika " + klucz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UsunUsera() {
        String dane = "";
        try {
            users = klient.odczytajPlik();
            System.out.println("6.1.2 Usuń użytkownika");
            System.out.print("Podaj login: ");
            Scanner wejscie = new Scanner(System.in);
            String klucz = wejscie.nextLine();
            while (!(users.containsKey(klucz))) {
                System.out.println("Nie ma takiego użytkownika!");
                System.out.print("Podaj login: ");
                klucz = wejscie.nextLine();
            }
            users.remove(klucz);
            for (HashMap.Entry<String, String> entry : users.entrySet()) {
                dane = dane + entry.getKey() + "/" + entry.getValue() + "\n";
            }
            klient.zapiszDoPliku(dane);
            klient.napiszKomunikat("Usunięto użytkownika");
            System.out.println("Usunięto użytkownika " + klucz);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ZapiszDane() {
        try {
            users = klient.odczytajPlik();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
