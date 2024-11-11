package com.example.tpmovilesfinal2c;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> visible;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<Integer> getVisible() {
        if (visible == null) {
            visible = new MutableLiveData<>();
        }
        return visible;
    }

    public void inicioSesion(String mail, String clave) {
        Bundle bundle = new Bundle();
        Call<String> token = ApiClient.getMyApiClient().login(mail, clave);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    SharedPreferences sp = ApiClient.conectar(context);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "Bearer " + response.body());
                    editor.commit();
                    Call<Propietario> pCall = ApiClient.getMyApiClient().obtenerPropietario("Bearer " + response.body());
                    pCall.enqueue(new Callback<Propietario>() {
                        @Override
                        public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                            if (response.isSuccessful()) {
                                bundle.putSerializable("propietario", response.body());
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("propietario", bundle);
                                context.startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Propietario> call, Throwable t) {
                            Toast.makeText(context, "Error en Login " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    Toast.makeText(context, "Error en Login ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> token, Throwable t) {
                Toast.makeText(context, "Error en Login " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RecuperarPass(String email){
        Call<String> call = ApiClient.getMyApiClient().resetearpass(email);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context, "Email enviado con exito, revise su correo.",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(context, "Error al enviar el Email.",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}

