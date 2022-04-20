package com.talha.physicswallahassignment.retrofitclient;

import com.talha.physicswallahassignment.models.TeacherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserInterface {
    @GET(".")
    Call<List<TeacherModel>> getUserData();

}
