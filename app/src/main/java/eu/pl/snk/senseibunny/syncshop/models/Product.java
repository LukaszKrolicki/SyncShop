package eu.pl.snk.senseibunny.syncshop.models;

public class Product {
    private int idProduktu;
    private int idListy;
    private int idKlienta;
    private String nazwaTworzacego;
    private String Nazwa;
    private String Cena;
    private String Typ;
    private String Ilosc;
    private String Notatka;
    private String Sklep;
    private String Status;

    private String nazwaRezerwujacego;

    private String nazwaKupujacego;

    public Product(int idProduktu, int idListy, int idKlienta, String nazwaTworzacego, String nazwa, String cena, String typ, String ilosc, String notatka, String sklep, String status) {
        this.idProduktu = idProduktu;
        this.idListy = idListy;
        this.idKlienta = idKlienta;
        this.nazwaTworzacego = nazwaTworzacego;
        Nazwa = nazwa;
        Cena = cena;
        Typ = typ;
        Ilosc = ilosc;
        Notatka = notatka;
        Sklep = sklep;
        Status = status;
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
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getCena() {
        return Cena;
    }

    public void setCena(String cena) {
        Cena = cena;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String typ) {
        Typ = typ;
    }

    public String getIlosc() {
        return Ilosc;
    }

    public void setIlosc(String ilosc) {
        Ilosc = ilosc;
    }

    public String getNotatka() {
        return Notatka;
    }

    public void setNotatka(String notatka) {
        Notatka = notatka;
    }

    public String getSklep() {
        return Sklep;
    }

    public void setSklep(String sklep) {
        Sklep = sklep;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
