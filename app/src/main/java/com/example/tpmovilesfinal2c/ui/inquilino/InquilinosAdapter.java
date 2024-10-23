package com.example.tpmovilesfinal2c.ui.inquilino;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.tpmovilesfinal2c.Modelo.Inquilino;
import com.example.tpmovilesfinal2c.R;
import com.example.tpmovilesfinal2c.Request.ApiClient;
import com.example.tpmovilesfinal2c.ui.inmueble.InmueblesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class InquilinosAdapter extends RecyclerView.Adapter <InquilinosAdapter.ViewHolder> {

    private List<Contrato> lista;
    private View root;
    private LayoutInflater inflater;
    private Context context;

    public InquilinosAdapter(List<Contrato> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
        this.context = root.getContext();
    }

    @NonNull
    @Override
    public InquilinosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inquilino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinosAdapter.ViewHolder holder, int position) {
        Contrato i = lista.get(position);
        Inmueble inmu = i.getInmueble();
        Inquilino inq = i.getInquilino();
        holder.tvDirec.setText(inmu.getDireccion());
        Glide.with(context)
                .load("http://192.168.0.104:5001/"+inmu.getImgUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivfotoInq);

        holder.btInqVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inquilino", inq);
                Navigation.findNavController(root).navigate(R.id.inquilinoDetalleFragment, bundle);
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
            ivfotoInq = itemview.findViewById(R.id.ivfotoInq);
            tvDirec = itemview.findViewById(R.id.tvInqDirec);
            btInqVer = itemview.findViewById(R.id.btInqVer);
        }
    }
}
