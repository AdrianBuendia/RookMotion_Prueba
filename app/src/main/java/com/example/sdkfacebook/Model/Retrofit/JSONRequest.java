package com.example.sdkfacebook.Model.Retrofit;

import com.example.sdkfacebook.Model.POJOS.DataResouesta;
import com.example.sdkfacebook.Model.POJOS.Lyrics;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONRequest {
    @GET("suggest/{search_term}")
    Call<DataResouesta> getDataRequest(@Path("search_term") String post_search_term);

    @GET("v1/{artist}/{song}")
    Call<Lyrics> getLyricsRequest(@Path("artist") String artist, @Path("song") String song);
}
