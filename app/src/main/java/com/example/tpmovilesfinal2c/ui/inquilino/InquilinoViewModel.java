package com.example.tpmovilesfinal2c.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Contrato>> contrato;
    private Context context;

    public InquilinoViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<List<Contrato>> getContrato() {
        if(contrato == null){
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    /*public LiveData<List<Inmueble>> getInmueble(){
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }*/

    public void mostrarInmuebles() {
        SharedPreferences sp = ApiClient.conectar(context);
        String t = sp.getString("token", "vacio");

        Call<List<Contrato>> con = ApiClient.getMyApiClient().obtenerInmueblesAlquilados(t);
        con.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    contrato.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}