package eu.pl.snk.senseibunny.syncshop.models;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    private static Model model;
    private ApiDatabaseDriver dataBaseDriver;

    private Client client;

    private final ArrayList<Integer> clientsIdToAddToNewList;

    private Model(Context context) throws SQLException {
        //this.client = new Client(0, "", "", "", 0, 0, "x", 0, "x");

        this.dataBaseDriver = new ApiDatabaseDriver();

        this.clientsIdToAddToNewList = new ArrayList<Integer>();
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
    public Integer createList(Integer idTworcy, String nazwa, String dataPocz, String dataKon) throws IOException, InterruptedException, JSONException {
        return Integer.parseInt(dataBaseDriver.createList(idTworcy, nazwa, dataPocz, dataKon));
    }
    public void createInviteM(Integer zapraszajacy,Integer zapraszany,String username) throws IllegalStateException,IOException {
        dataBaseDriver.createInviteD(zapraszajacy, zapraszany,username);
    }
    public void updateInvitationM(Integer zapraszajacy,Integer zapraszany,String status) throws IllegalStateException,IOException {
        dataBaseDriver.updateInvitation(zapraszajacy, zapraszany,status);
    }

    public void createFriendBind(Integer f1,Integer f2) throws IllegalStateException,IOException {
        dataBaseDriver.createFriendBindD(f1,f2);
    }

    public void deleteFriendM(Integer id, Integer id2) throws IllegalStateException,IOException {
        dataBaseDriver.deleteFriendD(id,id2);
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

    public ArrayList<Client> getFriendsM(Integer id) throws IOException, InterruptedException {
        ArrayList<Client> list = dataBaseDriver.getFriendsD(id);
        System.out.println("list"+list);
        return list;
    }

    public void addClientToList(Integer id) {
        clientsIdToAddToNewList.add(id);
    }

    public void removeClientFromList(Integer id) {
        clientsIdToAddToNewList.remove(id);
    }

    public ArrayList<Integer> getClientsIdToAddToNewList() {
        return clientsIdToAddToNewList;
    }

    public void clearClientsIdToAddToNewList() {
        clientsIdToAddToNewList.clear();
    }
    public void createListBindM(Integer idK,Integer idL) throws IllegalStateException,IOException {
        dataBaseDriver.createListBindD(idK,idL);
    }
}

