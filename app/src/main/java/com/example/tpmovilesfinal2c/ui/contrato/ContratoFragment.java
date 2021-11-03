package com.example.tpmovilesfinal2c.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;
import java.util.List;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private RecyclerView rvContratos;
    private ContratosAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contratoViewModel =
                new ViewModelProvider(this).get(ContratoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contrato, container, false);
        rvContratos = root.findViewById(R.id.rvContratos);


        contratoViewModel.getContratos().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> con) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvContratos.setLayoutManager(gridLayoutManager);
                adapter = new ContratosAdapter(con, root, getLayoutInflater());
                rvContratos.setAdapter(adapter);
            }
        });
        contratoViewModel.inmueblesAlquilados();
        return root;
    }
}