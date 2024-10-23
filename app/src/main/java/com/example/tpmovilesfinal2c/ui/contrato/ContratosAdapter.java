package com.example.tpmovilesfinal2c.ui.contrato;

import android.content.Context;
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
import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.Modelo.Inmueble;
import com.example.tpmovilesfinal2c.R;
import com.example.tpmovilesfinal2c.ui.inquilino.InquilinosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolder> {
    private List<Contrato> lista;
    private View root;
    private LayoutInflater inflater;
    private Context context;

    public ContratosAdapter(List<Contrato> lista, View root, LayoutInflater inflater) {
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
        Contrato i = lista.get(position);
        Inmueble inmu = i.getInmueble();
        holder.tvDirec.setText(inmu.getDireccion());
        Glide.with(root.getContext())
                .load("http://192.168.0.104:5001/"+inmu.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivfotoInq);
        holder.btInqVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", i);
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
