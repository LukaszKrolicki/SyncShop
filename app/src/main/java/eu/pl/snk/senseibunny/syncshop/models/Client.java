package eu.pl.snk.senseibunny.syncshop.models;

public class Client {
    private int idKlienta;
    private String imie;

    private String nazwisko;

    private String email;

    private String typ;
    private String username;

    public Client(int idKlienta, String imie, String nazwisko, String email,String username,String typ) {
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.typ = typ;
        this.username = username;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public String getTyp() {
        return typ;
    }

    public String getUsername() {
        return username;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
