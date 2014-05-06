/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsluga;

import java.net.*;
import java.io.*;

/**
 *
 * @author Agnieszka
 */
public class KlientSieci {

    private Socket gnd = null;
    private InputStream wejscie = null;
    private BufferedReader odczyt = null;

    public KlientSieci() {
        try {
            gnd = new Socket("127.0.0.1", 6666);
        } catch (UnknownHostException e) {
            System.out.println("Nieznany host");
        } catch (IOException e) {
            System.out.println("Nie udało się utworzyć gniazda klienta");
        }

    }

    public void PobierzDane() {
        try {
            wejscie = gnd.getInputStream();
        } catch (IOException e) {
            System.out.println("Nie udało się otworzyć strumienia wejścia");
        }
        try {
            if (wejscie != null) {
                odczyt = new BufferedReader(new InputStreamReader(wejscie));
            }
        } catch (Exception e) {
            System.out.println("Nie można otworzyć strumienia odczytu wejścia");
        }
        try {
            if (odczyt!=null) {
                String komunikat = odczyt.readLine();
                System.out.println("Komunikat serwera: "+komunikat);
            }
        }
catch(Exception e) {
    System.out.println("Nie można odczytać komunikatu z serwera");
}
        try {
            odczyt.close();
            wejscie.close();
        } catch (IOException e) {
            System.out.println("Nie można zamknąć strumieni wejściowych");
        }

        try {
            gnd.close();
        } catch (IOException e) {
            System.out.println("Nie można zamknąć gniazda sieciowego klienta");
        }
    }
}
