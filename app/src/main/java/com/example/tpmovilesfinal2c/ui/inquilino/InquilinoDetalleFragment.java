package com.example.tpmovilesfinal2c.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.R;
import com.example.tpmovilesfinal2c.ui.inmueble.InmuebleDetalleViewModel;

public class InquilinoDetalleFragment extends Fragment {

    private InquilinoDetalleViewModel inquilinoDetalleViewModel;
    private TextView tvCod, tvNombre, tvApellido, tvDni, tvMail, tvTel;

    public static InquilinoDetalleFragment newInstance() {
        return new InquilinoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        View root = inflater.inflate(R.layout.inquilino_detalle_fragment, container, false);
        inicializarVista(root);
        inquilinoDetalleViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                tvCod.setText(inquilino.getIdInquilino()+"");
                tvNombre.setText(inquilino.getNombre());
                tvApellido.setText(inquilino.getApellido());
                tvDni.setText(inquilino.getDNI()+"");
                tvMail.setText(inquilino.getEmail());
                tvTel.setText(inquilino.getTelefono());

            }
        });
        inquilinoDetalleViewModel.mostrarInquilino(getArguments());
        return root;
    }

    public void inicializarVista(View root){
        tvCod = root.findViewById(R.id.tvCod);
        tvNombre = root.findViewById(R.id.tvDetInqNombre);
        tvApellido = root.findViewById(R.id.tvDetInqAp);
        tvDni = root.findViewById(R.id.tvDetInqDni);
        tvMail = root.findViewById(R.id.tvDetInqMail);
        tvTel = root.findViewById(R.id.tvDetInqTel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}