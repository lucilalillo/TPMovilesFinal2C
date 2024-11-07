package com.example.tpmovilesfinal2c.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private Inmueble i;
    private Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public MutableLiveData<Inmueble> getInmueble(){
        if(inmueble == null)
        {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void setInmueble(Bundle bundle){
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void guardarEstado(int id){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "no token");
        Call<Inmueble> inmuebleCall = ApiClient.getMyApiClient().editarEstado(token, id);
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "El estado se guardó con éxito", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Error: "+response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}