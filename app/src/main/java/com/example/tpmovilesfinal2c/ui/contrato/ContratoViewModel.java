package com.example.tpmovilesfinal2c.ui.contrato;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public ContratoViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {
       if(inmuebles == null){
           inmuebles = new MutableLiveData<>();
       }
        return inmuebles;
    }

    public void inmueblesAlquilados(){
        ApiClient api = ApiClient.getApi();
        inmuebles.setValue(api.obtenerPropiedadesAlquiladas());
    }
}