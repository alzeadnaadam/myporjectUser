package com.example.myuserapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api")
    Call<RandomUserResponse> getRandomUser();
}
