package com.example.sdkfacebook.Repositorio;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sdkfacebook.Model.POJOS.DataResouesta;
import com.example.sdkfacebook.Model.Retrofit.APIUtils;
import com.example.sdkfacebook.Model.Retrofit.JSONRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositorio {

    private static Repositorio newsRepository;

    public static Repositorio getInstance(){
        if (newsRepository == null){
            newsRepository = new Repositorio();
        }
        return newsRepository;
    }

    private JSONRequest jsonRequest;

    public Repositorio(){
        jsonRequest = APIUtils.getService();
    }

    public MutableLiveData<DataResouesta> getNewsSongs(String search_term){
        MutableLiveData<DataResouesta> dataResouestaMutable = new MutableLiveData<>();

        jsonRequest.getDataRequest( search_term ).enqueue(new Callback<DataResouesta>() {
            @Override
            public void onResponse(Call<DataResouesta> call, Response<DataResouesta> response) {
                if(!response.isSuccessful()){
                    //el codigo de respuesTP de respuesta para ver el problema
                 Log.e("En Repositorio","No se pudo:\n"+response.code());
                    return;
                }

                dataResouestaMutable.setValue( response.body() );


                Log.e("AQUIIIII","bIEEEN ");
            }

            @Override
            public void onFailure(Call<DataResouesta> call, Throwable t) {
                Log.e("Repositorio_onFailure",t.getMessage());
                dataResouestaMutable.setValue( null );
            }
        });
        return dataResouestaMutable;
    }

//    public MutableLiveData<Lyrics> getNewLyrics(String artista,String song){
//        MutableLiveData<Lyrics> lyricsMutableLiveData = new MutableLiveData<>();
//
//        jsonRequest.getLyricsRequest(artista,song).enqueue(new Callback<Lyrics>() {
//            @Override
//            public void onResponse(Call<Lyrics> call, Response<Lyrics> response) {
//                if(!response.isSuccessful()){
//                    //el codigo de respuesTP de respuesta para ver el problema
//                    Log.e("En Repositorio","No se pudo:\n"+response.code());
//                    return;
//                }
//
//                lyricsMutableLiveData.setValue( response.body() );
//
//                Log.e("AQUIIIII","bIEEEN lYRICS");
//                Log.e("a ver","---"+response.body().getLyrics());
//
//            }
//
//            @Override
//            public void onFailure(Call<Lyrics> call, Throwable t) {
//                Log.e("Repositorio_onFailure",t.getMessage());
//                lyricsMutableLiveData.setValue( null );
//            }
//        });
//
//        return lyricsMutableLiveData;
//    }

}
