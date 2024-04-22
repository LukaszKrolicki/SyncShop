package eu.pl.snk.senseibunny.syncshop.models;

public class Raport {
    private int idRaportu;
    private int idKlienta;
    private String opis;
    private String username;
    public Raport(int idRaportu, int idKlienta, String opis, String username) {
        this.idRaportu = idRaportu;
        this.idKlienta = idKlienta;
        this.opis = opis;
        this.username = username;
    }
    public int getIdRaportu() {
        return idRaportu;
    }

    public void setIdRaportu(int idRaportu) {
        this.idRaportu = idRaportu;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
