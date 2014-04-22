/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Agnieszka
 */
public class Dyrektor extends Pracownik {

    private String dodatek;
    private String karta;
    private String limit;

    public String getDodatek() {
        return dodatek;
    }

    public void setDodatek(String dodatek) {
        this.dodatek = dodatek;
    }

    public String getKarta() {
        return karta;
    }

    public void setKarta(String karta) {
        this.karta = karta;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public Dyrektor() {
    }

    public Dyrektor(String dodatek, String karta, String limit, Integer id, String imie, String nazwisko, String wynagrodzenie, String stanowisko, String telefon) {
        super(id, imie, nazwisko, wynagrodzenie, stanowisko, telefon);
        this.dodatek = dodatek;
        this.karta = karta;
        this.limit = limit;
    }

}
