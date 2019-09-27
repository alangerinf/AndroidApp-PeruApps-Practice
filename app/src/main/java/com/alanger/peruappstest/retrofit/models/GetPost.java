package com.alanger.peruappstest.retrofit.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetPost {
    @GET("/posts")
    @Headers({"Accept: application/json"})
    Call<List<RetroPost>> getAllPosts();
    @GET("/posts/{id}")
    @Headers({"Accept: application/json"})
    Call<RetroPost> getPost(@Path("id")int id);
}
