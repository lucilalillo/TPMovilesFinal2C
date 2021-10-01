package com.example.tpmovilesfinal2c.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Pago;
import com.example.tpmovilesfinal2c.Request.ApiClient;

import java.util.ArrayList;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Pago>> pagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<ArrayList<Pago>> getPagos() {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void mostrarPagos(Bundle bundle) {
        Contrato c = (Contrato) bundle.getSerializable("contrato");
        ApiClient api = ApiClient.getApi();
        ArrayList<Pago> p = api.obtenerPagos(c);
        pagos.setValue(p);
    }
}