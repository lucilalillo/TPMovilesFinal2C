package com.example.tpmovilesfinal2c.ui.inquilino;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmueble;
    private Context context;

    public InquilinoViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getInmueble(){
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void mostrarInmuebles() {
        ApiClient api = ApiClient.getApi();
        inmueble.setValue(api.obtnerPropiedades());
    }
}