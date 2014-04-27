/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Agnieszka
 */
public class Sterownia {

    private final String DB_USER = "jarek";
    private final String DB_PASS = "1234";
    private final String DB_NAME = "baza_pracownikow";

    public static String DB_URL = "jdbc:derby://localhost:1527/baza_pracownikow";
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

    public void createTable() {
        String createPracownicy = "CREATE TABLE pracownicy (id_pracownika INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), imie varchar(16), nazwisko varchar(32), pensja int, stanowisko varchar(16), telefon varchar(32), dodatek int, karta_nr varchar(16), limit int, prowizja int)";
        String test = "SELECT * FROM pracownicy";
        try {
            if (!(zapytanie.execute(test))) {
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
        while (!(wybor.equals("Q"))) {
            System.out.println("    [D]yrektor/[H]andlowiec: ");
            try {
                Scanner odczyt = new Scanner(System.in);
                wybor = odczyt.nextLine();

                if (wybor.equalsIgnoreCase("D")) {
                    stanowisko = "Dyrektor";
                }
                else
                if (wybor.equalsIgnoreCase("H")) {
                    stanowisko = "Handlowiec";
                } else {
                    continue;
                }
                System.out.println(LINIA);
                System.out.print("Imię             : ");
                String imie = odczyt.next();
                System.out.print("Nazwisko         : ");
                String nazwisko = odczyt.next();
                System.out.print("Wynagrodzenie    : ");
                String wynagrodzenie = odczyt.next();
                System.out.println("Stanowisko       9: " + stanowisko);
                System.out.print("Telefon          : ");
                String telefon = odczyt.next();

                if (wybor.equalsIgnoreCase("D")) {
                    System.out.print("Dodatek służbowy : ");
                    String dodatek = odczyt.next();
                    System.out.print("Karta służbowa   : ");
                    String karta = odczyt.next();
                    System.out.print("Limit kosztów    : ");
                    String limit = odczyt.next();
                }
                if (wybor.equalsIgnoreCase("H")) {
                    System.out.print("Prowizja %       : ");
                    String prowizja = odczyt.next();
                    System.out.print("Limit prowizji   : ");
                    String limit = odczyt.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(LINIA);
            System.out.println("[Enter] - zapisz");
            System.out.println("[Q] - porzuć");
            try {
                Scanner odczyt = new Scanner(System.in);
                wybor = odczyt.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(wybor);
            while(!(wybor.equals("Q")) && !(wybor.equals("")))
            {
            }
            if (wybor.equals(""))
            {
                System.out.println("Zatwierdzono!");
            }
        }
    }
}
