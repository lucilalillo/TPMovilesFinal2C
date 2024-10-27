package com.example.tpmovilesfinal2c.ui.inmueble;

import android.content.Context;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel inmuebleViewModel;
    private RecyclerView rvInmuebles;
    private InmueblesAdapter adapter;
    private FloatingActionButton btAgregarInmueble;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inmueble, container, false);
        inmuebleViewModel =
                new ViewModelProvider(this).get(InmuebleViewModel.class);
        rvInmuebles = (RecyclerView) root.findViewById(R.id.rvInmuebles);
        btAgregarInmueble = root.findViewById(R.id.fabAgregarInmueble);
        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> inmuebles) {
                GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rvInmuebles.setLayoutManager(gridLayoutManager);
                adapter = new InmueblesAdapter(inmuebles,root,getLayoutInflater());
                rvInmuebles.setAdapter(adapter);
            }
        });

        btAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.fragment_crear_Inmueble);
            }
        });
        inmuebleViewModel.mostrarInmuebles();

        return root;
    }
}