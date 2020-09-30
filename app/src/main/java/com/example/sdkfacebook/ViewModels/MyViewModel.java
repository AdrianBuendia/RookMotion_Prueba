package com.example.sdkfacebook.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sdkfacebook.Model.POJOS.DataResouesta;
import com.example.sdkfacebook.Repositorio.Repositorio;

public class MyViewModel extends ViewModel {
    private MutableLiveData<DataResouesta> mutableLiveData;
    private Repositorio repositorio;

    public MyViewModel() {
        init();
    }

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        repositorio = Repositorio.getInstance();
    }

    public LiveData<DataResouesta> getNewsRepository(String search_term) {
        mutableLiveData = repositorio.getNewsSongs(search_term);
        return mutableLiveData;
    }
    public LiveData<DataResouesta> getNewsRepository() {
        return mutableLiveData;
    }

}
