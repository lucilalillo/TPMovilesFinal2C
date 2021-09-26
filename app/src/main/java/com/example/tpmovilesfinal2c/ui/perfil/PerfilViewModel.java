package com.example.tpmovilesfinal2c.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.Request.ApiClient;

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
        ApiClient api = ApiClient.getApi();
        Propietario p = api.obtenerUsuarioActual();
        propietario.setValue(p);

    }

    public void editarDatos(Propietario p){
        ApiClient api = ApiClient.getApi();
        api.actualizarPerfil(p);
        editarVisible.setValue(View.VISIBLE);
        guardar.setValue(View.INVISIBLE);
    }

    public void guardarDatos(){
        editable.setValue(true);
        editarVisible.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);

    }
}