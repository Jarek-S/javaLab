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

/**
 *
 * @author Agnieszka
 */
public class Backup {
    private final String DB_USER = "jarek";
    private final String DB_PASS = "1234";
    private final String DB_NAME = "baza_pracownikow";

    public static String DB_URL = "jdbc:derby://localhost:1527/baza_pracownikow";
    public static String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private Connection polaczenie;
    private Statement zapytanie;

    public static String LINIA = "--------------------------------------------------";

    public Backup() {
        try {
            Class.forName(Backup.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Nie ma sterownika JDBC!");
            e.printStackTrace();
        }
        try {
            polaczenie = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            //    System.out.println("Połączenie z bazą nawiazane.");
            zapytanie = polaczenie.createStatement();
            //    System.out.println("Obiekt zapytania gotowy.");
        } catch (SQLException e) {
            System.err.println("Problem z połączeniem z bazą danych!");
        }
    }
}
