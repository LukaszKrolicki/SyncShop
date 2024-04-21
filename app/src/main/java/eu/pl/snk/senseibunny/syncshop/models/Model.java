package eu.pl.snk.senseibunny.syncshop.models;

import android.content.Context;
import android.view.Display;

import java.io.IOException;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private ApiDatabaseDriver dataBaseDriver;

    private Client client;

    private Model(Context context) throws SQLException {
        //this.client = new Client(0, "", "", "", 0, 0, "x", 0, "x");

        this.dataBaseDriver = new ApiDatabaseDriver();
    }
    public ApiDatabaseDriver getDataBaseDriver() {
        return dataBaseDriver;
    }

    public static synchronized Model getInstance(Context context) throws SQLException {
        if (model == null) {
            model = new Model(context);
        }
        return model;
    }

    public static synchronized Model getInstanceWC() throws SQLException {
        return model;
    }

    public void login(String username, String password) throws IOException, InterruptedException {
        this.client= dataBaseDriver.login(username, password);
    }

    public Client getClient() {
        return client;
    }
//    public void evaluateClient(String username, String password, String rola){
//        ResultSet resultSet = dataBaseDriver.getClientData(username,password,rola);
//        try{
//            if(resultSet.next()){
//                //
//                // System.out.println("rep1");
//                this.client.setIdPracownika(resultSet.getInt("idPracownika"));
//                this.client.setImiePracownika(resultSet.getString("imie"));
//                this.client.setNazwiskoPracownika(resultSet.getString("nazwisko"));
//                this.client.setNazwaUzytkownika(resultSet.getString("nazwaUżytkownika"));
//                this.client.setEmailPracownika(resultSet.getString("e-mail"));
//                this.client.setNrTelefonu(resultSet.getInt("nrTelefonu"));
//                this.client.setWiekPracownika(resultSet.getInt("wiek"));
//                this.client.setCzyUprawniony(resultSet.getInt("czyUprawniony"));
//                this.client.setRola(resultSet.getString("rola"));
//                this.setClientLoginFlag(true);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

