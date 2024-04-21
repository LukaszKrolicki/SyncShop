package eu.pl.snk.senseibunny.syncshop.models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;

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

    public void login(String username, String password) throws IOException {

        // Make sure you're making a POST request
        Call<Void> call = api.login(username, password);

        // Execute the request and get the response
        Response<Void> response = call.execute();

        // Print the response for debugging
        System.out.println(response);
        System.out.println(response.headers());

        // Handle the response
        if (response.isSuccessful()) {
            sessionCookie = response.headers().get("set-cookie");
            System.out.println("Zalogowano pomyślnie. Cookie: " + sessionCookie);
        } else {
            System.out.println("Błąd logowania. Kod odpowiedzi HTTP: " + response.code());
        }
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
    }




