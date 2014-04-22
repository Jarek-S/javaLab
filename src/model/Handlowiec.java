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
public class Handlowiec extends Pracownik {

    private String prowizja;
    private String limit_prowizji;

    public String getProwizja() {
        return prowizja;
    }

    public void setProwizja(String prowizja) {
        this.prowizja = prowizja;
    }

    public String getLimit_prowizji() {
        return limit_prowizji;
    }

    public void setLimit_prowizji(String limit_prowizji) {
        this.limit_prowizji = limit_prowizji;
    }

    public Handlowiec() {
    }

    public Handlowiec(String prowizja, String limit_prowizji, Integer id, String imie, String nazwisko, String wynagrodzenie, String stanowisko, String telefon) {
        super(id, imie, nazwisko, wynagrodzenie, stanowisko, telefon);
        this.prowizja = prowizja;
        this.limit_prowizji = limit_prowizji;
    }
    
    

}
