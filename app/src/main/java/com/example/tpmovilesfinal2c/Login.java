package com.example.tpmovilesfinal2c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    EditText etEmail, etContraseña;
    Button btIngresar;
    TextView tvError;
    ImageView foto;
    LoginViewModel lvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarVista();
        lvm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lvm.getVisible().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvError.setVisibility(integer);
            }
        });
    }
    private void inicializarVista(){
        etEmail = findViewById(R.id.etEmail);
        etContraseña = findViewById(R.id.etContraseña);
        tvError = findViewById(R.id.tvError);
        //foto es para un posible logo de la inmobiliaria
        foto = findViewById(R.id.ivFoto);

        btIngresar = findViewById(R.id.btIngresar);
        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvm.inicioSesion(etEmail.getText().toString(), etContraseña.getText().toString());

            }
        });
    }
}