package com.example.tpmovilesfinal2c.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> inmuebles;
    private Context context;


    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<List<Inmueble>> getInmuebles() {
        if (inmuebles == null) {
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;

    }

    public void mostrarInmuebles() {
        SharedPreferences sp = ApiClient.conectar(context);
        String t = sp.getString("token", "vacio");
        Call<List<Inmueble>> lista = ApiClient.getMyApiClient().listaInmuebles(t);
        lista.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmuebles.postValue(response.body());
                }else{
                    Toast.makeText(context, "No se encontraron Inmuebles", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Se produjo un error", Toast.LENGTH_LONG).show();
            }
        });
    }
}