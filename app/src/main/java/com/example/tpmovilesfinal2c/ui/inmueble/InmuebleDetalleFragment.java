package com.example.tpmovilesfinal2c.ui.inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.R;

public class InmuebleDetalleFragment extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private ImageView ivFotoDetInmu;
    private TextView tvDetDir, tvdetUso, tvDetAmb, tvDetTipo, tvDetPrecio, tvDetId;
    private CheckBox cbEstado;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);

        View root = inflater.inflate(R.layout.inmueble_detalle_fragment, container, false);
        inicializarVista(root);
        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                Glide.with(getContext())
                        .load("http://192.168.0.104:5001/"+inmueble.getImgUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivFotoDetInmu);
                //tvDetId.setText(inmueble.getIdInmueble()+"");
                tvDetDir.setText("Dirección: "+inmueble.getDireccion());
                tvdetUso.setText("Uso: "+inmueble.getUso());
                tvDetAmb.setText("Ambientes: "+inmueble.getAmbientes()+"");
                tvDetTipo.setText("Tipo: "+inmueble.getTipo());
                tvDetPrecio.setText(String.valueOf("Precio: $ "+inmueble.getPrecio()));
                cbEstado.setChecked(inmueble.isDisponible());
                cbEstado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.guardarEstado(inmueble.getId());
                    }
                });
            }
        });
        mViewModel.setInmueble(getArguments());
        return root;
    }

    private void inicializarVista(View root){
        ivFotoDetInmu = root.findViewById(R.id.ivFotoDetInmu);
        tvDetDir = root.findViewById(R.id.tvDetDir);
        tvdetUso = root.findViewById(R.id.tvDetUso);
        tvDetAmb = root.findViewById(R.id.tvDetAmb);
        tvDetTipo = root.findViewById(R.id.tvDetTipo);
        tvDetPrecio = root.findViewById(R.id.tvDetPrecio);
        cbEstado = root.findViewById(R.id.cbEstado);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}