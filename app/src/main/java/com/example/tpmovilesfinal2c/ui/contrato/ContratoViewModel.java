package com.example.tpmovilesfinal2c.ui.contrato;

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
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Contrato>> contrato;
    private Context context;

    public ContratoViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<List<Contrato>> getContratos() {
       if(contrato == null){
           contrato = new MutableLiveData<>();
       }
        return contrato;
    }

    public void inmueblesAlquilados(){
       /* ApiClient api = ApiClient.getApi();
        inmuebles.setValue(api.obtenerPropiedadesAlquiladas());*/
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "-1");
        Call<List<Contrato>> con = ApiClient.getMyApiClient().obtenerInmueblesAlquilados(token);
        con.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    contrato.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Ocurrió un error "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}