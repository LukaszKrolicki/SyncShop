package eu.pl.snk.senseibunny.syncshop.models;

public class ShoppingList {
    private int idListy;
    private int idTworcy;
    private String Nazwa;

    public ShoppingList(int idListy, int idTworcy, String nazwa) {
        this.idListy = idListy;
        this.idTworcy = idTworcy;
        Nazwa = nazwa;
    }

    public int getIdListy() {
        return idListy;
    }

    public void setIdListy(int idListy) {
        this.idListy = idListy;
    }

    public int getIdTworcy() {
        return idTworcy;
    }

    public void setIdTworcy(int idTworcy) {
        this.idTworcy = idTworcy;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }
}
