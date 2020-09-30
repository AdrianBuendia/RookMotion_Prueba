package com.example.sdkfacebook.Model.Retrofit;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "https://api.lyrics.ovh/";

    //getAPIService
    public static JSONRequest getService() {
        return APIClientRetrofit.getClient(BASE_URL).create(JSONRequest.class);
    }
}