package eu.pl.snk.senseibunny.syncshop.models;

public class Product {
    private int idProduktu;
    private int idListy;
    private int idKlienta;
    private String nazwaTworzacego;
    private String nazwa;
    private String cena;
    private String typ;
    private String ilosc;
    private String notatka;
    private String sklep;
    private String status;

    private String nazwaRezerwujacego;

    private String nazwaKupujacego;

    public Product(int idProduktu, int idListy, int idKlienta, String nazwaTworzacego, String nazwa, String cena, String ilosc, String notatka, String sklep, String status) {
        this.idProduktu = idProduktu;
        this.idListy = idListy;
        this.idKlienta = idKlienta;
        this.nazwaTworzacego = nazwaTworzacego;
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
        this.notatka = notatka;
        this.sklep = sklep;
        this.status = status;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public int getIdListy() {
        return idListy;
    }

    public void setIdListy(int idListy) {
        this.idListy = idListy;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getNazwaTworzacego() {
        return nazwaTworzacego;
    }

    public void setNazwaTworzacego(String nazwaTworzacego) {
        this.nazwaTworzacego = nazwaTworzacego;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        typ = typ;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
    }

    public String getSklep() {
        return sklep;
    }

    public void setSklep(String sklep) {
        this.sklep = sklep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNazwaRezerwujacego() {
        return nazwaRezerwujacego;
    }

    public String getNazwaKupujacego() {
        return nazwaKupujacego;
    }

    public void setNazwaRezerwujacego(String nazwaRezerwujacego) {
        this.nazwaRezerwujacego = nazwaRezerwujacego;
    }

    public void setNazwaKupujacego(String nazwaKupujacego) {
        this.nazwaKupujacego = nazwaKupujacego;
    }
}
