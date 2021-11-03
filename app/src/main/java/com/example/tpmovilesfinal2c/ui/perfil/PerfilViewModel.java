package com.example.tpmovilesfinal2c.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> propietario;
    private MutableLiveData<Integer> editarVisible;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Boolean> editable;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<Propietario> getPropietario(){
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }
        return propietario;
    }

    public MutableLiveData<Integer> getEditarVisible() {
        if (editarVisible == null) {
            editarVisible = new MutableLiveData<>();
        }
        return editarVisible;
    }

    public MutableLiveData<Integer> getGuardar() {
        if (guardar == null) {
            guardar = new MutableLiveData<>();
        }
        return guardar;
    }

    public MutableLiveData<Boolean> getEditable() {
        if (editable == null) {
            editable = new MutableLiveData<>();
        }
        return editable;
    }

    //usuario Logueado
    public void obtenerPropietarioActual() {
        SharedPreferences sp = ApiClient.conectar(context);
        String t = sp.getString("token", "vacio");
        Call<Propietario> prop = ApiClient.getMyApiClient().obtenerPropietario(t);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietario.postValue(response.body());
                    getEditable().setValue(false);
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public void editarDatos(Propietario p){
        SharedPreferences sp = ApiClient.conectar(context);
        String t = sp.getString("token", "vacio");
        Call<Propietario> prop = ApiClient.getMyApiClient().editarPropietario(t, p);
        prop.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietario.postValue(response.body());
                    Toast.makeText(context, "Se editaron los datos con éxito", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al editar "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void guardarDatos(){
        editable.setValue(true);
        editarVisible.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);

    }
}