package eu.pl.snk.senseibunny.syncshop.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiDatabaseDriver {
    ////////////////////////////////////////////////////////////////
    //Connecting with db with api

    static String baseUrl = "http://10.0.2.2:8080";
    String sessionCookie = null;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Api api = retrofit.create(Api.class);

    public <type> ArrayList<type> getArrayData(Type listType, String jsonString) throws IOException, InterruptedException {

        Gson gson = new Gson();

        ArrayList<type> list = gson.fromJson(jsonString, listType);

        return list;
    }


    public Client login(String username, String password) throws IOException, InterruptedException {


        Call<ResponseBody> call = api.login(username, password);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            sessionCookie = response.headers().get("set-cookie");
            System.out.println("Zalogowano pomyślnie. Cookie: " + sessionCookie);
            Type listType = new TypeToken<ArrayList<Client>>(){}.getType();
            assert response.body() != null;
            ArrayList<Client> x = getArrayData(listType,response.body().string());
            return x.get(0);
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }

        return null;
    }

    public void getProtectedRoute2() throws IOException {
        Call<ResponseBody> call = api.getProtectedRoute2(sessionCookie);
        Response<ResponseBody> response = call.execute();
        System.out.println(response);
        if (response.isSuccessful()) {
            assert response.body() != null;
            String responseBody = response.body().string(); // Get the response body as string
            System.out.println(responseBody);
        } else {
            System.out.println("Failed to fetch protected route. HTTP response code: " + response.code());
        }
    }

    public void getProtectedRoute() throws IOException {
        Call<ResponseBody> call = api.getProtectedRoute(sessionCookie);
        Response<ResponseBody> response = call.execute();
        System.out.println(response);
        if (response.isSuccessful()) {
            assert response.body() != null;
            String responseBody = response.body().string(); // Get the response body as string
            System.out.println(responseBody);
        } else {
            System.out.println("Failed to fetch protected route. HTTP response code: " + response.code());
        }
    }

    public String createUser(String imie, String nazwisko, String email,String username, String haslo) throws IOException {
        Call<Void> call = api.register(imie, nazwisko, email, username, haslo);

        // Execute the request and get the response
        Response<Void> response = call.execute();
        if (response.isSuccessful()) {

            return "Utworzono użytkownika pomyślnie";
        } else {
            throw new IllegalStateException("Błąd podczas tworzenia użytkownika. Kod odpowiedzi HTTP: " + response.code());
        }
    }
    public String createList(Integer idTworcy, String nazwa, String dataPocz, String dataKon) throws IOException, JSONException {
        Call<ResponseBody> call = api.createList(sessionCookie, idTworcy, nazwa, dataPocz, dataKon);
        System.out.println("xd");
        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();
        System.out.println(response);
        if (response.isSuccessful()) {
            // Parse the response body
            String responseBody = response.body().string();
            System.out.println(responseBody);
            JSONObject jsonObject = new JSONObject(responseBody);
            String listId = jsonObject.getString("listazakupowid");
            System.out.println(listId);
            return listId;
        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public void createInviteD(Integer zapraszajacy, Integer zapraszany,String username) throws IOException {
        Call<Void> call = api.createInvite(sessionCookie,zapraszajacy,zapraszany,username);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public ArrayList<Client> searchUser(String username) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.getUsers(sessionCookie, username);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println("JSON Response: " + jsonString); // Print JSON for debugging

            Type listType = new TypeToken<ArrayList<Client>>(){}.getType();
            try {
                ArrayList<Client> x = getArrayData(listType, jsonString);
                System.out.println("Parsed Clients: " + x); // Print parsed clients for debugging
                return x;
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }
        return new ArrayList<>(); // Return an empty list if response is not successful
    }

    public ArrayList<Invitation> friendRequests(Integer userId) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.getInvitations(sessionCookie, userId);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println("JSON Response: " + jsonString); // Print JSON for debugging

            Type listType = new TypeToken<ArrayList<Invitation>>(){}.getType();
            try {
                ArrayList<Invitation> x = getArrayData(listType, jsonString);
                System.out.println("Parsed Clients: " + x); // Print parsed clients for debugging
                return x;
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }
        return new ArrayList<>(); // Return an empty list if response is not successful
    }

    public void updateInvitation(Integer zapraszajacy, Integer zapraszany,String status) throws IOException {
        Call<Void> call = api.updateInvitation(sessionCookie,zapraszajacy,zapraszany,status);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public void createFriendBindD(Integer f1, Integer f2) throws IOException {
        Call<Void> call = api.createFriendBind(sessionCookie,f1,f2);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        }
        else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public ArrayList<Client> getFriendsD(Integer userId) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.getFriends(sessionCookie, userId);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println("JSON Response: " + jsonString); // Print JSON for debugging

            Type listType = new TypeToken<ArrayList<Client>>(){}.getType();
            try {
                ArrayList<Client> x = getArrayData(listType, jsonString);
                System.out.println("Parsed Clients: " + x); // Print parsed clients for debugging
                return x;
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }
        return new ArrayList<>(); // Return an empty list if response is not successful
    }

    public void deleteFriendD(Integer id, Integer id2) throws IOException {
        Call<Void> call = api.deleteFriend(sessionCookie,id,id2);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }
    public void deleteList(Integer idKli, Integer idListy) throws IOException {
        Call<Void> call = api.deleteList(sessionCookie,idKli,idListy);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas usuwania Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }
    public void createListBindD(Integer idK, Integer idL) throws IOException {
        Call<Void> call = api.createListBind(sessionCookie,idK,idL);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {
            System.out.println("Utworzono powiązanie listy");
        }
        else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public void updateUserD(Integer id, String name, String surname, String email) throws IOException {
        Call<Void> call = api.updateUser(sessionCookie,id,email,name, surname);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public void updateUserPasswordD(Integer id,String password) throws IOException {
        Call<Void> call = api.updateUserPass(sessionCookie,id,password);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public boolean checkUserPasswordD(String username,String password) throws IOException {
        Call<Void> call = api.checkPass(username,password);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        return response.code() == 200;
    }
    public ArrayList<ShoppingList> getLists(Integer userId) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.getLists(sessionCookie, userId);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println("JSON Response: " + jsonString); // Print JSON for debugging

            Type listType = new TypeToken<ArrayList<ShoppingList>>(){}.getType();
            try {
                ArrayList<ShoppingList> x = getArrayData(listType, jsonString);
                System.out.println("Parsed Lists: " + x); // Print parsed clients for debugging
                return x;
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Błąd przy pobraniu list. Kod odpowiedzi HTTP: " + response.code());
        }
        return new ArrayList<>(); // Return an empty list if response is not successful
    }

    public void createShoppingBindD(Integer f1, Integer f2, Integer f3) throws IOException {
        Call<Void> call = api.createShoppingInvite(sessionCookie,f1,f2,f3);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        }
        else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public ArrayList<ShoppingInvitation> shoppingRequests(Integer userId) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.getUserShoppingInvitation(sessionCookie, userId);

        // Execute the request and get the response
        Response<ResponseBody> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            assert response.body() != null;
            String jsonString = response.body().string();
            System.out.println("JSON Response: " + jsonString); // Print JSON for debugging

            Type listType = new TypeToken<ArrayList<ShoppingInvitation>>(){}.getType();
            try {
                ArrayList<ShoppingInvitation> x = getArrayData(listType, jsonString);
                System.out.println("Parsed Clients: " + x); // Print parsed clients for debugging
                return x;
            } catch (JsonSyntaxException e) {
                System.err.println("Error parsing JSON: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }
        return new ArrayList<>(); // Return an empty list if response is not successful
    }

    public void deleteListNotificationD(Integer id, Integer id2) throws IOException {
        Call<Void> call = api.deleteListNotification(sessionCookie,id,id2);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        if (response.isSuccessful()) {

        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }
}




