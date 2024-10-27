package com.example.tpmovilesfinal2c.ui.inmueble;

import static androidx.recyclerview.widget.RecyclerView.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;
import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter <InmueblesAdapter.ViewHolder> {
    private List<Inmueble> lista;
    private View root;
    private LayoutInflater inflater;

    public InmueblesAdapter(List<Inmueble> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root= root;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    //Referenciar a la vista item y pasarsela al viewholder
    public InmueblesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.item_inmueble, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmueblesAdapter.ViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        holder.tvDireccion.setText(lista.get(position).getDireccion());
        holder.tvprecio.setText("$ " + lista.get(position).getPrecio()+"");
        Glide.with(root.getContext())
                .load("http://192.168.0.108:5001/" + lista.get(position).getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFotoInmu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", i);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFotoInmu;
        private TextView tvDireccion, tvprecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFotoInmu = itemView.findViewById(R.id.ivFotoInmu);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvprecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
