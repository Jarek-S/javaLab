/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Agnieszka
 */
public class Backup {

    private final String DB_USER = "jarek";
    private final String DB_PASS = "1234";
    private final String DB_NAME = "baza_pracownikow";

    public static String DB_URL = "jdbc:derby://localhost:1527/baza_pracownikow;create=true";
    public static String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection polaczenie;
    private CallableStatement cs;

    public static String LINIA = "--------------------------------------------------";

    public Backup() {
        try {
            Class.forName(Backup.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Nie ma sterownika JDBC!");
            e.printStackTrace();
        }
    /*    try {
            polaczenie = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            //    System.out.println("Połączenie z bazą nawiazane.");
            zapytanie = polaczenie.createStatement();
            //    System.out.println("Obiekt zapytania gotowy.");
        } catch (SQLException e) {
            System.err.println("Problem z połączeniem z bazą danych!");
        } */
    }

    public void wykonajKopie() {
        SimpleDateFormat formatDaty = new SimpleDateFormat("yyyy-MM-dd");
        Calendar data = Calendar.getInstance();
        String plik = "C:\\backupDB\\" + formatDaty.format(data.getTime()) + ".bkp";
        try {
            polaczenie = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            cs = polaczenie.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)");
            cs.setString(1, plik);
            cs.execute();
            cs.close();
            System.out.println("Zapisano kopię bezpieczeństwa w pliku " + plik);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void odzyskajDane() {
        File sciezka = new File("C:\\backupDB\\");
        File[] lista = sciezka.listFiles();
        int i = 0;
        if (lista.length != 0) {
            System.out.println("Lista dostępnych kopii bezpieczeństwa");
            System.out.println(LINIA);
            try {
                for (File plik : lista) {
                    System.out.println("[" + (i++) + "] - " + plik.getName());
                }
                System.out.println(LINIA);
                System.out.print("Podaj numer pliku : ");
                Scanner numer = new Scanner(System.in);
                int nr = numer.nextInt();
                String db_restore = "jdbc:derby://localhost:1527/baza_pracownikow;restoreFrom=C:\\backupDB\\"+lista[nr].getName();
                polaczenie = DriverManager.getConnection(db_restore, DB_USER, DB_PASS);
                System.out.println(LINIA);
            System.out.println("Odzyskano kopię bazy z pliku "+lista[nr].getName());
            System.out.println(LINIA);

            } catch (InputMismatchException e) {
                System.out.println("Masz wybrać jeden z podanych numerów");
                odzyskajDane();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Masz wybrać jeden z podanych numerów");
                odzyskajDane();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Brak katalogu z kopiami bezpieczeństwa");
            }
        } else {
            System.out.println(LINIA);
            System.out.println("Brak dostępnych kopii bezpieczeństwa");
            System.out.println(LINIA);
        }
    }
}
