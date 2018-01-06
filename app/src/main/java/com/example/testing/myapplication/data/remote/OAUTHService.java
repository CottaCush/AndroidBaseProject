package com.example.testing.myapplication.data.remote;

import com.google.gson.JsonElement;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OAUTHService {

    /*
     TODO Ensure this end point conforms to the one used in the api docs
     TODO This endpoint can be should be to the one used in the api docs
     */
    @POST("/oauth/token")
    Call<JsonElement> getAccessToken(@Body Map body);
}
