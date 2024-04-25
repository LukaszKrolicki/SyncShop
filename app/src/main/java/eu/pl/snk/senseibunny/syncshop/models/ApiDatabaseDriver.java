package eu.pl.snk.senseibunny.syncshop.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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
    public String createList(Integer idTworcy, String nazwa, String dataPocz, String dataKon) throws IOException {
        Call<Void> call = api.createList(sessionCookie, idTworcy, nazwa, dataPocz, dataKon);

        // Execute the request and get the response
        Response<Void> response = call.execute();
        if (response.isSuccessful()) {
            return "Utworzono listę pomyślnie";
        } else {
            throw new IllegalStateException("Błąd podczas tworzenia Listy. Kod odpowiedzi HTTP: " + response.code());
        }
    }

    public void createInviteD(Integer zapraszajacy, Integer zapraszany) throws IOException {
        Call<Void> call = api.createInvite(sessionCookie,zapraszajacy,zapraszany);

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

}




