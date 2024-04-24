package eu.pl.snk.senseibunny.syncshop.models;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    @GET("/protected-route")
    Call<ResponseBody> getProtectedRoute(@Header("Cookie") String sessionCookie);

    @GET("/protected-route2")
    Call<ResponseBody> getProtectedRoute2(@Header("Cookie") String sessionCookie);

    @FormUrlEncoded
    @POST("/createUser")
    Call<Void> register(@Field("imie") String imie, @Field("nazwisko") String nazwisko,
                        @Field("email") String email,@Field("username") String username,
                        @Field("haslo") String haslo);
    @FormUrlEncoded
    @POST("/createList")
    Call<Void> createList(@Header("Cookie") String sessionCookie, @Field("idTworcy") Integer idTworcy, @Field("nazwa") String nazwa, @Field("dataPocz") String dataPocz, @Field("dataKon") String dataKon);

    @GET("/searchUser/{username}")
    Call<ResponseBody> getUsers(@Header("Cookie") String sessionCookie, @Path("username") String username);

}

