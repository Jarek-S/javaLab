/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jarek
 */
public abstract class Pracownik {

    private Integer id;
    private String imie;
    private String nazwisko;
    private String wynagrodzenie;
    private String stanowisko;
    private String telefon;

    public Integer getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(String wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Pracownik() {
    }

    public Pracownik(Integer id, String imie, String nazwisko, String wynagrodzenie, String stanowisko, String telefon) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wynagrodzenie = wynagrodzenie;
        this.stanowisko = stanowisko;
        this.telefon = telefon;
    }

}
