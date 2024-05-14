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

    private final ArrayList<Integer> clientCreatedLists;

    ArrayList<ShoppingList> shoppingLists;

    ArrayList<Client> friends;

    ArrayList<Product> currentListAddedProducts;

    ArrayList<Product> currentListReservedProducts;

    ArrayList<Product> currentListBoughtProducts;

    ShoppingList currentList;


    private Model(Context context) throws SQLException {
        //this.client = new Client(0, "", "", "", 0, 0, "x", 0, "x");

        this.dataBaseDriver = new ApiDatabaseDriver();

        this.clientsIdToAddToNewList = new ArrayList<Integer>();

        this.clientCreatedLists= new ArrayList<Integer>();

        this.currentListAddedProducts = new ArrayList<Product>();

        this.currentListReservedProducts=new ArrayList<Product>();

        this.currentListBoughtProducts=new ArrayList<Product>();

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

    public boolean login(String username, String password) throws IOException, InterruptedException {
        this.client= dataBaseDriver.login(username, password);
        if(client == null){
            return false;
        }
        else{
            return true;
        }
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

    public void createShoppingInviteM(Integer zapraszajacy,Integer idListy, Integer idZapraszanego) throws IllegalStateException,IOException {
        dataBaseDriver.createShoppingBindD(zapraszajacy, idListy, idZapraszanego);
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
    public ArrayList<ShoppingList> getLists(Integer id) throws IOException, InterruptedException {
        shoppingLists = dataBaseDriver.getLists(id);
        System.out.println("list"+shoppingLists);
        return shoppingLists;
    }
    public void deleteList(Integer idKli, Integer idListy) throws IllegalStateException,IOException {
        dataBaseDriver.deleteList(idKli,idListy);
    }

    public ArrayList<Invitation> getInvitations(Integer id) throws IOException, InterruptedException {
        ArrayList<Invitation> list = dataBaseDriver.friendRequests(id);
        System.out.println("list"+list);
        return list;
    }


    public ArrayList<ShoppingInvitation> getShoppingInvitations(Integer id) throws IOException, InterruptedException {
        shoppingLists = dataBaseDriver.getLists(id);
        friends = dataBaseDriver.getFriendsD(id);

        ArrayList<ShoppingInvitation> list = dataBaseDriver.shoppingRequests(id);
        System.out.println("list"+list);
        return list;
    }

    public ArrayList<Client> getFriendsM(Integer id) throws IOException, InterruptedException {
        friends = dataBaseDriver.getFriendsD(id);
        System.out.println("list"+friends);
        return friends;
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
    public void updateUserM(Integer idUser, String email, String name, String surname) throws IllegalStateException,IOException {
        dataBaseDriver.updateUserD(idUser,name,surname,email);
    }

    public void updateUserPassM(Integer idUser, String password) throws IllegalStateException,IOException {
        dataBaseDriver.updateUserPasswordD(idUser,password);
    }

    public boolean checkUserPassM(String username, String password) throws IllegalStateException,IOException {
        return dataBaseDriver.checkUserPasswordD(username,password);
    }

    public String getFriendName(Integer id) throws IllegalStateException,IOException {
        for(Client c: friends){
            if(c.getIdKlienta()==id){
                return c.getUsername();
            }
        }

        return "";
    }

    public String getListName(Integer id) throws IllegalStateException,IOException {
        for(ShoppingList c: shoppingLists){
            if(c.getIdListy()==id){
                return c.getNazwa();
            }
        }

        return "";
    }

    public void deleteListNotificationM(Integer id, Integer id2) throws IllegalStateException,IOException {
        dataBaseDriver.deleteListNotificationD(id,id2);
    }

    public ShoppingList getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ShoppingList currentList) {
        this.currentList = currentList;
    }

    public Integer addProductM(Integer idListy, Integer idKlienta,String nazwaTworzacego, String nazwa,String cena, String ilosc, String notatka, String sklep, String status) throws IllegalStateException, IOException, JSONException {
        return Integer.parseInt(dataBaseDriver.addProducktD(idListy, idKlienta,nazwaTworzacego,nazwa,cena,ilosc,notatka,sklep,status));
    }

    public ArrayList<Product> getCurrentListAddedProducts() {
        return currentListAddedProducts;
    }

    public ArrayList<Product> getCurrentListReservedProducts() {
        return currentListReservedProducts;
    }

    public ArrayList<Product> getCurrentListBoughtProducts() {
        return currentListBoughtProducts;
    }

    public void addToCurrentListAddedProducts(Product product) {
        currentListAddedProducts.add(product);
    }

    public void addToCurrentListReservedProducts(Product product) {
        currentListReservedProducts.add(product);
    }

    public void addToCurrentListBoughtProducts(Product product) {
        currentListBoughtProducts.add(product);
    }

    public void setShoppingProducts(Integer listId, String status) throws IOException, InterruptedException {
        if(status.equals("dodane")){
            currentListAddedProducts = dataBaseDriver.getShoppingProductsD(listId, status);

        }
        else if(status.equals("reserved")){
            currentListReservedProducts = dataBaseDriver.getShoppingProductsD(listId, status);

        }
        else if(status.equals("bought")){
            currentListBoughtProducts = dataBaseDriver.getShoppingProductsD(listId, status);
        }
    }

    public void updateProductM(Integer idListy, Integer idProduktu,String nazwa, String status) throws IllegalStateException,IOException {
        dataBaseDriver.updateProduct(idListy,idProduktu,nazwa,status);
    }

    public void deleteProductM(Integer idProduct, Integer idList) throws IllegalStateException,IOException {
        dataBaseDriver.deletProductD(idProduct,idList);
    }

}

