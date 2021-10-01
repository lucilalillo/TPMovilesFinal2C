package com.example.tpmovilesfinal2c.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.R;
import com.example.tpmovilesfinal2c.ui.inquilino.InquilinosAdapter;

import java.util.ArrayList;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {
    private ArrayList<Inmueble> lista;
    private View root;
    private LayoutInflater inflater;

    public ContratosAdapter(ArrayList<Inmueble> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ContratosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contrato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratosAdapter.ViewHolder holder, int position) {
        Inmueble i = lista.get(position);
        Glide.with(root.getContext())
                .load(lista.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivfotoInq);
        holder.tvDirec.setText(lista.get(position).getDireccion());
        holder.btInqVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", i);
                Navigation.findNavController(root).navigate(R.id.contratoDetalleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivfotoInq;
        TextView tvDirec;
        Button btInqVer;

        public ViewHolder(View itemview){
            super(itemview);
            ivfotoInq = itemview.findViewById(R.id.ivContratoFoto);
            tvDirec = itemview.findViewById(R.id.tvContratoDireccion);
            btInqVer = itemview.findViewById(R.id.btContratoVer);
        }
    }
}
