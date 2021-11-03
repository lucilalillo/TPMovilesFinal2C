package com.example.tpmovilesfinal2c.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpmovilesfinal2c.Modelo.Pago;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;
import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel pViewModel;
    private RecyclerView rvPagos;
    private PagosAdapter adapter;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        pViewModel = new ViewModelProvider(this).get(PagosViewModel.class);
        View root = inflater.inflate(R.layout.pagosfragment, container, false);

        rvPagos = root.findViewById(R.id.rvPagos);
        pViewModel.getPagos().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rvPagos.setLayoutManager(gridLayoutManager);
                adapter = new PagosAdapter(pagos, root, getLayoutInflater());
                rvPagos.setAdapter(adapter);


            }
        });
        pViewModel.mostrarPagos(getArguments());
        return root;

    }


}