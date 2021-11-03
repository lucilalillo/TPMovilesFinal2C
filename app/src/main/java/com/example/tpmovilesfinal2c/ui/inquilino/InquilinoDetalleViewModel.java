package com.example.tpmovilesfinal2c.ui.inquilino;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private Context contexto;

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
        Inquilino i = (Inquilino) bundle.getSerializable("inquilino");
        //ApiClient api = ApiClient.getApi();
        inquilino.setValue(i);

    }
}