/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serwer;

import java.io.*;
import java.net.*;

/**
 *
 * @author Agnieszka
 */
public class Serwer {

    public static void main(String args[]) {
        ServerSocket gnd = null;
        Socket polaczenie = null;
        OutputStream wyjscie = null;
        PrintWriter komunikat = null;
        try {
            gnd = new ServerSocket(6666);
            System.out.println("Serwer testowy. Nasłuch na porcie 6666.");
        } catch (IOException e) {
            System.out.println("Problem z utworzeniem gniazda na serwerze");
        }
        try {
            polaczenie = gnd.accept();
        } catch (IOException e) {
            System.out.println("Nie udało się nawiązać połączenia z klientem");
        }
        try {
            wyjscie = polaczenie.getOutputStream();
        } catch (IOException e) {
            System.out.println("Nie ma danych wyjściowych");
        }
        try {
            if (wyjscie != null) {
                komunikat = new PrintWriter(wyjscie);
            }
        } catch (Exception e) {
            System.out.println("Nie można utworzyć obiektu wyjściowego");
        }
        try {
            if (komunikat!=null) {
                komunikat.print("Tu serwer. Czym mogę słuzyć?");
            }
        }
        catch(Exception e) {
            System.out.println("Nie można zapisać danych do strumienia wyjściowego");
        }

        try {
            polaczenie.close();
            wyjscie.close();
            komunikat.close();
        } catch (IOException e) {
            System.out.println("Problem z zamknięciem połączenia lub strumienia");
        }
    }

}