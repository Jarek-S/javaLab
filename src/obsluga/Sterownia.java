/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obsluga;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Agnieszka
 */
public class Sterownia {
    private final String DB_USER = "jarek";
    private final String DB_PASS = "1234";
    private final String DB_NAME = "baza_pracownikow";
    
    public static String DB_URL = "jdbc:derby:baza_pracownikow";
    
    private Connection polaczenie;
    private Statement zapytanie;
    
    public Sterownia() {
        
    }
}
