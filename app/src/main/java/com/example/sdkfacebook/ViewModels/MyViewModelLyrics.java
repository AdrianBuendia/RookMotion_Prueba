package com.example.sdkfacebook.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sdkfacebook.Model.POJOS.Lyrics;
import com.example.sdkfacebook.Repositorio.RepositorioLyrics;


public class MyViewModelLyrics extends ViewModel {

    private MutableLiveData<Lyrics> mutableLiveData;
    private RepositorioLyrics repositorioLyrics;

    public MyViewModelLyrics() {
        init();
    }

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        repositorioLyrics = RepositorioLyrics.getInstance();
    }

    public LiveData<Lyrics> getNewsRepository(String artista, String cancion) {
        mutableLiveData = repositorioLyrics.getNewLyrics(artista,cancion);
        return mutableLiveData;
    }
    public LiveData<Lyrics> getNewsRepository() {
        return mutableLiveData;
    }
}
