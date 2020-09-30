package com.example.sdkfacebook.Repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sdkfacebook.Model.POJOS.Lyrics;
import com.example.sdkfacebook.Model.Retrofit.APIUtils;
import com.example.sdkfacebook.Model.Retrofit.JSONRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioLyrics {
    private static RepositorioLyrics newsRepository;

    public static RepositorioLyrics getInstance() {
        if (newsRepository == null) {
            newsRepository = new RepositorioLyrics();
        }
        return newsRepository;
    }

    private JSONRequest jsonRequest;

    public RepositorioLyrics() {
        jsonRequest = APIUtils.getService();
    }

    public MutableLiveData<Lyrics> getNewLyrics(String artista, String song) {
        MutableLiveData<Lyrics> lyricsMutableLiveData = new MutableLiveData<>();

        jsonRequest.getLyricsRequest(artista, song).enqueue(new Callback<Lyrics>() {
        //    jsonRequest.getLyricsRequest(artista, song).enqueue(new Callback<Lyrics>() {
            @Override
            public void onResponse(Call<Lyrics> call, Response<Lyrics> response) {
                if (!response.isSuccessful()) {
                    //el codigo de respuesTP de respuesta para ver el problema
                    Log.e("En Repositorio", "No se pudo:\n" + response.code());
                    return;
                }

                lyricsMutableLiveData.setValue(response.body());

                Log.e("AQUIIIII", "bIEEEN lYRICS");
                Log.e("a ver", "---" + response.body().getLyrics());

            }

            @Override
            public void onFailure(Call<Lyrics> call, Throwable t) {
                Log.e("Repositorio_onFailure", t.getMessage());
                lyricsMutableLiveData.setValue(null);
            }
        });

        return lyricsMutableLiveData;
    }
}