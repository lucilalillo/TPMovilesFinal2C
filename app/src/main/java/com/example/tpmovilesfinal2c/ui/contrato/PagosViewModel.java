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
import com.example.tpmovilesfinal2c.Modelo.Pago;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> pagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<List<Pago>> getPagos() {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void mostrarPagos(Bundle bundle) {
        Contrato c = (Contrato) bundle.getSerializable("pagos");

        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token","-1");
        Call<List<Pago>> pag = ApiClient.getMyApiClient().obtenerPagos(token, c.getIdContrato());
        pag.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    pagos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Ocurrió un error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}