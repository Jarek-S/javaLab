/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.util.Scanner;
import obsluga.Backup;
import obsluga.KlientRMI;
import obsluga.KlientSieci;
import obsluga.Sterownia;
import static obsluga.Sterownia.LINIA;

/**
 *
 * @author Agnieszka
 */
public class Baza {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int wybor = 0;
        while (wybor != 9) {
            System.out.println("MENU");
            System.out.println("    1. Lista pracowników");
            System.out.println("    2. Dodaj pracownika");
            System.out.println("    3. Usuń pracownika");
            System.out.println("    4. Kopia zapasowa");
            System.out.println("    5. Pobierz dane z sieci");
            System.out.println("    6. Zarządzaj uprawnieniami transferu");
            System.out.println(" ");
            System.out.println("    9. Koniec");
            System.out.println(" ");
            System.out.println("Wybierz numer i naciśnij ENTER");

            try {
                Scanner odczyt = new Scanner(System.in);
                wybor = odczyt.nextInt();

                switch (wybor) {
                    case 1: {
                        System.out.println("1.1 Pokaż pracowników");
                        System.out.println("");
                        Sterownia s = new Sterownia();
                        s.pokazPracownikow();
                        s.closeConnection();
                        break;
                    }
                    case 2: {
                        System.out.println("2.1 Dodaj pracownika");
                        System.out.println("");
                        Sterownia s = new Sterownia();
                        s.createTable();
                        s.dodajPracownika();
                        s.closeConnection();
                        break;
                    }
                    case 3: {
                        System.out.println("3.1 Usuń pracownika");
                        System.out.println("");
                        Sterownia s = new Sterownia();
                        s.usunPracownika();
                        s.closeConnection();
                        break;
                    }
                    case 4: {
                        System.out.println("4.1 Kopia zapasowa");
                        System.out.println("");
                        System.out.print("[Z]achowaj/[O]dzyskaj : ");
                        Scanner wejscie = new Scanner(System.in);
                        String decyzja = wejscie.nextLine();
                        while (!(decyzja.equalsIgnoreCase("Z")) && !(decyzja.equalsIgnoreCase("O"))) {
                            decyzja = wejscie.nextLine();
                        }
                        if (decyzja.equalsIgnoreCase("Z")) {
                            Backup b = new Backup();
                            b.wykonajKopie();
                        } else {
                            Backup b = new Backup();
                            b.odzyskajDane();
                        }
                        break;
                    }
                    case 5: {
                        KlientSieci kS = new KlientSieci();
                        System.out.println("5.1 Pobierz dane z sieci");
                        kS.PobierzDane();
                        break;
                    }
                    case 6: {
                        System.out.println("6.1 Zarządzaj uprawnieniami transferu danych");
                        String decyzja = "X";
                        while (!(decyzja.equalsIgnoreCase("Z"))) {
                            KlientRMI kR = new KlientRMI();
                            kR.PokazDane();
                            System.out.println(LINIA);
                            System.out.println("[D]odaj/[U]suń/[Z]akończ");
                            Scanner wejscie = new Scanner(System.in);
                            decyzja = wejscie.nextLine();
                            while (!(decyzja.equalsIgnoreCase("Z")) && !(decyzja.equalsIgnoreCase("D")) && !(decyzja.equalsIgnoreCase("U"))) {
                                decyzja = wejscie.nextLine();
                            }
                            if (decyzja.equalsIgnoreCase("D")) {
                                kR.DodajUsera();
                            } else if (decyzja.equalsIgnoreCase("U")) {
                                kR.UsunUsera();
                            }
                        }
                        break;
                    }
                    case 9: {
                        break;
                    }
                    default: {
                        System.out.println("Nie umiesz czytać?");
                        System.out.println("Spróbuj jeszcze raz...");
                    }
                }
            } catch (Exception e) {
                System.out.println("Masz wybrać numer!");
                System.out.println("Spróbuj jeszcze raz...");
                e.printStackTrace();
            }
        }
    }

}
