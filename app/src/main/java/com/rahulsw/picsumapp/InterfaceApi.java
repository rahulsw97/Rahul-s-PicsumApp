package com.rahulsw.picsumapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceApi {

    @GET("list/")

    Call<List<OutputImg>> loadImages();


}
