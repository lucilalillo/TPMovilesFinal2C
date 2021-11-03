package com.example.tpmovilesfinal2c.ui.inquilino;

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

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;
import java.util.List;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private RecyclerView rvInquilinos;
    private InquilinosAdapter adapter;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        rvInquilinos = (RecyclerView) root.findViewById(R.id.rvInquilinos);

        inquilinoViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvInquilinos.setLayoutManager(gridLayoutManager);
                adapter = new InquilinosAdapter(contratos, root, getLayoutInflater());
                rvInquilinos.setAdapter(adapter);
            }
        });
        inquilinoViewModel.mostrarInmuebles();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}