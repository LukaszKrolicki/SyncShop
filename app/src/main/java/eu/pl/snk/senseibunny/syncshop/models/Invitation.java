package eu.pl.snk.senseibunny.syncshop.models;

public class Invitation {
    private int idZapraszajacego;
    private int idZaproszonego;
    private String status;

    public Invitation(int idZapraszajacego, int idZaproszonego, String status) {
        this.idZapraszajacego = idZapraszajacego;
        this.idZaproszonego = idZaproszonego;
        this.status = status;
    }

    public int getIdZapraszajacego() {
        return idZapraszajacego;
    }

    public void setIdZapraszajacego(int idZapraszajacego) {
        this.idZapraszajacego = idZapraszajacego;
    }

    public int getIdZaproszonego() {
        return idZaproszonego;
    }

    public void setIdZaproszonego(int idZaproszonego) {
        this.idZaproszonego = idZaproszonego;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
