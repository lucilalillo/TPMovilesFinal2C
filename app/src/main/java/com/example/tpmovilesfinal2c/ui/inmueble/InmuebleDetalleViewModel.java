package com.example.tpmovilesfinal2c.ui.inmueble;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {
    MutableLiveData<Inmueble> inmueble;
    private Inmueble i;

    public InmuebleDetalleViewModel() {

        inmueble = new MutableLiveData<>();
    }

    public MutableLiveData<Inmueble> getInmueble(){

        return inmueble;
    }

    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void guardarCambios(Boolean cheak){
        ApiClient api = ApiClient.getApi();
        i.setEstado(cheak);
        api.actualizarInmueble(i);
    }
}