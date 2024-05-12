package eu.pl.snk.senseibunny.syncshop.models;

public class ShoppingInvitation {

    private int idZapraszajacego;

    private int idListy;
    private int idZapraszanego;

    public ShoppingInvitation(int idZapraszajacego, int idListy, int idZapraszanego) {
        this.idZapraszajacego = idZapraszajacego;
        this.idListy = idListy;
        this.idZapraszanego = idZapraszanego;
    }

    public int getIdZapraszajacego() {
        return idZapraszajacego;
    }

    public void setIdZapraszajacego(int idZapraszajacego) {
        this.idZapraszajacego = idZapraszajacego;
    }

    public int getIdListy() {
        return idListy;
    }

    public void setIdListy(int idListy) {
        this.idListy = idListy;
    }

    public int getIdZapraszanego() {
        return idZapraszanego;
    }

    public void setIdZapraszanego(int idZapraszanego) {
        this.idZapraszanego = idZapraszanego;
    }
}
