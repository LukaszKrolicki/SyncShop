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
    Call<ResponseBody> createList(@Header("Cookie") String sessionCookie, @Field("idTworcy") Integer idTworcy, @Field("nazwa") String nazwa, @Field("dataPocz") String dataPocz, @Field("dataKon") String dataKon);

    @GET("/searchUser/{username}")
    Call<ResponseBody> getUsers(@Header("Cookie") String sessionCookie, @Path("username") String username);
    @FormUrlEncoded
    @POST("/createInvite")
    Call<Void> createInvite(@Header("Cookie") String sessionCookie, @Field("idZapraszajacego") Integer idZapraszajacego, @Field("idZapraszonego") Integer idZapraszonego,@Field("username") String username);

    @GET("/getUserInvitation/{userId}")
    Call<ResponseBody> getInvitations(@Header("Cookie") String sessionCookie, @Path("userId") Integer userId);

    @FormUrlEncoded
    @POST("/updateInvitation")
    Call<Void> updateInvitation(@Header("Cookie") String sessionCookie, @Field("idZapraszajacego") Integer idZapraszajacego, @Field("idZapraszonego") Integer idZapraszonego, @Field("status") String status);

    @FormUrlEncoded
    @POST("/createFriendBind")
    Call<Void> createFriendBind(@Header("Cookie") String sessionCookie, @Field("idZnaj1") Integer idZnaj1, @Field("idZnaj2") Integer idZnaj2);

    @GET("/getFriends/{userId}")
    Call<ResponseBody> getFriends(@Header("Cookie") String sessionCookie, @Path("userId") Integer userId);

    @FormUrlEncoded
    @POST("/deleteFriend")
    Call<Void> deleteFriend(@Header("Cookie") String sessionCookie, @Field("idZnaj") Integer idZnaj, @Field("idZnaj2") Integer idZnaj2);

    @FormUrlEncoded
    @POST("/createListBind")
    Call<Void> createListBind(@Header("Cookie") String sessionCookie, @Field("idK") Integer idK, @Field("idL") Integer idL);

    @FormUrlEncoded
    @POST("/updateUser")
    Call<Void> updateUser(@Header("Cookie") String sessionCookie, @Field("idUser") Integer idZapraszajacego, @Field("email") String email,@Field("name") String name, @Field("surname") String surname);

    @FormUrlEncoded
    @POST("/updateUserPass")
    Call<Void> updateUserPass(@Header("Cookie") String sessionCookie, @Field("idUser") Integer idZapraszajacego,@Field("password") String password);

    @FormUrlEncoded
    @POST("/checkPassword")
    Call<Void> checkPass(@Field("username") String username,@Field("password") String password);
    @GET("/getUserLists/{userId}")
    Call<ResponseBody> getLists(@Header("Cookie") String sessionCookie, @Path("userId") Integer userId);

    @FormUrlEncoded
    @POST("/deleteList")
    Call<Void> deleteList(@Header("Cookie") String sessionCookie, @Field("idKli") Integer idKlienta, @Field("idListy") Integer idListy);

    @FormUrlEncoded
    @POST("/createShoppingBind")
    Call<Void> createShoppingInvite(@Header("Cookie") String sessionCookie, @Field("idZapraszajacego") Integer idZapraszajacego, @Field("idListy") Integer idListy, @Field("idZapraszanego") Integer idZapraszanego);

    @GET("/getUserShoppingInvitation/{userId}")
    Call<ResponseBody> getUserShoppingInvitation(@Header("Cookie") String sessionCookie, @Path("userId") Integer userId);

    @FormUrlEncoded
    @POST("/deleteListNotification")
    Call<Void> deleteListNotification(@Header("Cookie") String sessionCookie, @Field("idKli") Integer idKli, @Field("idListy") Integer idListy);

    @FormUrlEncoded
    @POST("/addProduct")
    Call<ResponseBody> addProduct(@Header("Cookie") String sessionCookie, @Field("idListy") Integer idListy, @Field("idKlienta") Integer idKlienta, @Field("nazwaTworzacego") String nazwaTworzacego, @Field("nazwa") String nazwa, @Field("cena") String cena, @Field("ilosc") String ilosc, @Field("notatka") String notatka, @Field("sklep") String sklep, @Field("status") String status);

}

