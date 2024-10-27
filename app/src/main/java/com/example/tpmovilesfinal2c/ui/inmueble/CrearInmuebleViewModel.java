package com.example.tpmovilesfinal2c.ui.inmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Tipo;
import com.example.tpmovilesfinal2c.Request.ApiClient;
import com.example.tpmovilesfinal2c.databinding.FragmentCrearInmuebleBinding;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;

public class CrearInmuebleViewModel extends AndroidViewModel {
    private Intent intent;
    private MutableLiveData<Uri> uriMutableLiveData;
    private Uri uri;
    private FragmentCrearInmuebleBinding binding;
    private MutableLiveData<List<String>> mSpinner;
    private MutableLiveData<List<Tipo>> mTipos;
    private Context context;
    private Map<String,Integer> tipos;

    public CrearInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = getApplication();
        tipos = new HashMap<String, Integer>();
    }

    public LiveData<Uri> getMUri(){
        if(uriMutableLiveData==null){
            uriMutableLiveData = new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }

    public LiveData<List<String>> getMSpinner(){
        if(mSpinner==null){
            mSpinner = new MutableLiveData<>();

        }
        return mSpinner;
    }
    public LiveData<List<Tipo>> getMTipos(){
        if(mTipos==null){
            mTipos = new MutableLiveData<>();
        }
        return mTipos;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            uri = data.getData();
            uriMutableLiveData.setValue(uri);
        }
    }

    public void guardarInmueble(String direccion, int ambientes, String uso,int importe, String descTipo, Bitmap foto ){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "no token");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        int tipoId=0;
        Iterator<String> it = tipos.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            if(key.equals(descTipo)){
                tipoId = tipos.get(key);
            }
        }
        Call<Inmueble> inmuebleCall = ApiClient.getMyApiClient().crearInmueble(token,byteArray,
                direccion, ambientes, importe,uso, tipoId, false);
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                Toast.makeText(context, "Inmueble creado con Extio", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Toast.makeText(context, "Error al guardar inmueble", Toast.LENGTH_LONG).show();

            }
        });



    }
    //Envia peticion al servidor para traer los tipos.
    public void cargarDatosTipo(){
        SharedPreferences sp = ApiClient.conectar(context);
        String token = sp.getString("token", "no token");
        Call<List<Tipo>> lista = ApiClient.getMyApiClient().listaTipos(token);
        lista.enqueue(new Callback<List<Tipo>>() {
            @Override
            public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response) {
                mTipos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Tipo>> call, Throwable throwable) {

            }
        });
    }
    //activa en el mutable seteando el arraylist con la descripcion de los tipos y arma el Map tipos
    //que va a a ser usado para obtener el tipoId cuando guardamos el inmueble.
    public void cargarSpinner(List<Tipo> tipos){
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < tipos.size(); i++) {
            Tipo t = tipos.get(i);
            arr.add(t.getDescripcion());
            //aca seteo los valores en el Map
            this.tipos.put(t.getDescripcion(), t.getId());
        }
        mSpinner.setValue(arr);
    }
}