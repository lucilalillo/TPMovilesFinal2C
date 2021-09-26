package com.example.tpmovilesfinal2c;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.Request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visible;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<Integer> getVisible() {
        if(visible == null){
            visible = new MutableLiveData<>();
        }
        return visible;
    }

    public void inicioSesion(String e, String c){
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(e,c);
        if (p != null){
            visible.setValue(View.INVISIBLE);
            Intent intent = new Intent(context, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("propietario", p);
            intent.putExtra("propietario", bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }else{

            visible.setValue(View.VISIBLE);
        }

    }
}
