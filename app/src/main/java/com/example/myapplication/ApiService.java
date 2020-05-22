package com.example.myapplication;

import retrofit2.Call;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiService {
    private static final String API = "https://api.privatbank.ua/p24api/";
    private static PrivateApi privateApi;

    public interface PrivateApi {
        @GET("exchange_rates")
        Call<CurrencyModel> getWeatherData(@Query("json") String json, @Query("date") String date);
    }

    static {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API)
                .client(client)
                .build();

        privateApi = retrofit.create(PrivateApi.class);
    }

    public static Call<CurrencyModel> getData(String date){
        return privateApi.getWeatherData("json", date);
    }
}