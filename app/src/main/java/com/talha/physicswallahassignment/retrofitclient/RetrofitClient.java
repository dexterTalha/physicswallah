package com.talha.physicswallahassignment.retrofitclient;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static RetrofitClient adapter = null;
    private UserInterface userInterface;
    public RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userInterface = retrofit.create(UserInterface.class);
    }
    public static final String BASE_URL = "https://my-json-server.typicode.com/easygautam/data/users/";

    public static synchronized RetrofitClient getInstance(){
        if (adapter == null) {
            adapter = new RetrofitClient();
        }
        return adapter;
    }
    public UserInterface getApi() {
        return userInterface;
    }
}
