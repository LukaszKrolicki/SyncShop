package eu.pl.snk.senseibunny.syncshop.models;

import android.content.Context;
import android.view.Display;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void register(String imie, String nazwisko, String email, String username, String haslo) throws IOException, InterruptedException {
        dataBaseDriver.createUser(imie, nazwisko, email, username, haslo);
    }
    public void createList(Integer idTworcy, String nazwa, String dataPocz, String dataKon) throws IOException, InterruptedException {
        dataBaseDriver.createList(idTworcy, nazwa, dataPocz, dataKon);
    }
    public void createInviteM(Integer zapraszajacy,Integer zapraszany,String username) throws IllegalStateException,IOException {
        dataBaseDriver.createInviteD(zapraszajacy, zapraszany,username);
    }
    public ArrayList<Client> searchUserM(String username) throws IOException, InterruptedException {
        ArrayList<Client> list = dataBaseDriver.searchUser(username);
        System.out.println("list"+list);
        return list;
    }

    public ArrayList<Invitation> getInvitations(Integer id) throws IOException, InterruptedException {
        ArrayList<Invitation> list = dataBaseDriver.friendRequests(id);
        System.out.println("list"+list);
        return list;
    }
}

