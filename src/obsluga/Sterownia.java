/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import model.Pracownik;

/**
 *
 * @author Agnieszka
 */
public class Sterownia {

    private final String DB_USER = "jarek";
    private final String DB_PASS = "1234";
    private final String DB_NAME = "baza_pracownikow";

    public static String DB_URL = "jdbc:derby://localhost:1527/baza_pracownikow;create=true";
    public static String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection polaczenie;
    private Statement zapytanie;

    public static String LINIA = "--------------------------------------------------";

    public Sterownia() {
        try {
            Class.forName(Sterownia.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Nie ma sterownika JDBC!");
            e.printStackTrace();
        }
        try {
            polaczenie = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            zapytanie = polaczenie.createStatement();
            System.out.println("Połączenie z bazą nawiazane.");
        } catch (SQLException e) {
            System.err.println("Problem z połączeniem z bazą danych!");
        }
    }

    public void closeConnection() {
        try {
            polaczenie.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknięciem połączenia!");
            e.printStackTrace();
        }
    }

    public void pokazPracownikow() {
        try {
            ResultSet lista = zapytanie.executeQuery("SELECT * FROM pracownicy");            
            //String imie, nazwisko, wynagrodzenie, stanowisko, telefon, dodatek, prowizja, karta, limit;
            while (lista.next()) {
                System.out.println(LINIA);
                System.out.println("Id               : "+lista.getInt("id_pracownika"));
                System.out.println("Imię             : "+lista.getString("imie"));
                System.out.println("Nazwisko         : "+lista.getString("nazwisko"));
                System.out.println("Wynagrodzenie    : "+lista.getString("pensja"));
                System.out.println("Stanowisko       : "+lista.getString("stanowisko"));
                System.out.println("Telefon          : "+lista.getString("telefon"));

                System.out.println(LINIA);
                System.out.println("[Enter] - dalej");
                System.out.println("[Q] - porzuć");

                Scanner odczyt = new Scanner(System.in);
                String wybor = odczyt.nextLine();

                System.out.println(wybor);
                while (!(wybor.equals("Q")) && !(wybor.equals(""))) {
                    wybor = odczyt.nextLine();
                }
                if (wybor.equals("Q")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String createPracownicy = "CREATE TABLE pracownicy (id_pracownika INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), imie varchar(16), nazwisko varchar(32), pensja varchar(16), stanowisko varchar(16), telefon varchar(32), dodatek varchar(16), karta_nr varchar(16), limit varchar(16), prowizja varchar(16))";
        ResultSet test = null;
        DatabaseMetaData meta = null;
        try {
            meta = polaczenie.getMetaData();
            test = meta.getTables(null, null, "PRACOWNICY", null);
            if (!test.next()) {
                zapytanie.execute(createPracownicy);
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }

    public void dodajPracownika() {
        String wybor = "S";
        String stanowisko;
        String query;
        while (!(wybor.equals("Q"))) {
            System.out.println("    [D]yrektor/[H]andlowiec: ");
            try {
                Scanner odczyt = new Scanner(System.in);
                wybor = odczyt.nextLine();

                if (wybor.equalsIgnoreCase("D")) {
                    stanowisko = "Dyrektor";
                } else if (wybor.equalsIgnoreCase("H")) {
                    stanowisko = "Handlowiec";
                } else {
                    continue;
                }
                System.out.println(LINIA);
                System.out.print("Imię             : ");
                query = "'" + odczyt.next();
                System.out.print("Nazwisko         : ");
                query = query + "', '" + odczyt.next();
                System.out.print("Wynagrodzenie    : ");
                query = query + "', '" + odczyt.next();
                System.out.println("Stanowisko       : " + stanowisko);
                query = query + "', '" + stanowisko;
                System.out.print("Telefon          : ");
                query = query + "', '" + odczyt.next();

                if (wybor.equalsIgnoreCase("D")) {
                    System.out.print("Dodatek służbowy : ");
                    query = query + "', '" + odczyt.next();
                    System.out.print("Karta służbowa   : ");
                    query = query + "', '" + odczyt.next();
                    System.out.print("Limit kosztów    : ");
                    query = query + "', '" + odczyt.next();
                    query = "INSERT INTO pracownicy (imie, nazwisko, pensja, stanowisko, telefon, dodatek, karta_nr, limit) VALUES (" + query + "')";

                } else {
                    System.out.print("Prowizja %       : ");
                    query = query + "', '" + odczyt.next();
                    System.out.print("Limit prowizji   : ");
                    query = query + "', '" + odczyt.next();
                    query = "INSERT INTO pracownicy (imie, nazwisko, pensja, stanowisko, telefon, prowizja, limit) VALUES (" + query + "')";
                }

                System.out.println(LINIA);
                System.out.println("[Enter] - zapisz");
                System.out.println("[Q] - porzuć");
                wybor = odczyt.nextLine();

            //    System.out.println(wybor);
                while (!(wybor.equals("Q")) && !(wybor.equals(""))) {
                    wybor = odczyt.nextLine();
                }
                if (wybor.equals("")) {
                    System.out.println(query);
                    zapytanie.execute(query);
                    System.out.println("Dodano pracownika.");
                    System.out.println("Czy chcesz dodać kolejnego?");
                    System.out.println("[Enter] - kolejny");
                    System.out.println("[Q] - wyjście");
                    wybor = odczyt.nextLine();

                    while (!(wybor.equals("Q")) && !(wybor.equals(""))) {
                        wybor = odczyt.nextLine();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
