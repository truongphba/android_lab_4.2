package com.example.lab_4_2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManagement {
    String SERVER_URL = "https://api-demo-anhth.herokuapp.com/";
    @GET("data.json")
    Call<Article> getArticleData();
}
