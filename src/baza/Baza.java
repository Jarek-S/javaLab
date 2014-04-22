/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

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
            System.out.println(" ");
            System.out.println("    9. Koniec");
            System.out.println(" ");
            System.out.println("Wybierz numer i naciśnij ENTER");

            try {
                Scanner odczyt = new Scanner(System.in);
                wybor = odczyt.nextInt();

                switch (wybor) {
                    case 1: {
                        System.out.println("wybrales 1");
                        break;
                    }
                    case 2: {
                        System.out.println("wybrales 2");
                        break;
                    }
                    case 3: {
                        System.out.println("wybrales 3");
                        break;
                    }
                    case 4: {
                        System.out.println("wybrales 4");
                        break;
                    }
                    case 5: {
                        System.out.println("wybrales 5");
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
            }
        }
    }

}