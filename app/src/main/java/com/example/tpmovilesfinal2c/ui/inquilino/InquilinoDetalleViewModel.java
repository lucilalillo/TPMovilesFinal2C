package com.example.tpmovilesfinal2c.ui.inquilino;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.Request.ApiClient;

public class InquilinoDetalleViewModel extends ViewModel {
    MutableLiveData<Inquilino> inquilino;

    public InquilinoDetalleViewModel() {

        inquilino = new MutableLiveData<>();
    }

    public MutableLiveData<Inquilino> getInquilino(){
        if(inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void mostrarInquilino(Bundle bundle){
        Inmueble i = (Inmueble) bundle.getSerializable("inmueble");
        ApiClient api = ApiClient.getApi();
        inquilino.setValue(api.obtenerInquilino(i));
    }
}