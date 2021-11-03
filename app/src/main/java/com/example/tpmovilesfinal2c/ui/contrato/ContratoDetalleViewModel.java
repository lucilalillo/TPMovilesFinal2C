package com.example.tpmovilesfinal2c.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Context context;
    private Contrato c;

    public ContratoDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Contrato> getContrato() {
        if(contrato == null){
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void setInmueble(Bundle bundle) {
       /* ApiClient api = ApiClient.getApi();*/
        c = (Contrato) bundle.getSerializable("contrato");
        contrato.setValue(c);

    }
}