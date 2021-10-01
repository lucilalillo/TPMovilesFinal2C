package com.example.tpmovilesfinal2c.ui.contrato;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmovilesfinal2c.Modelo.Pago;
import com.example.tpmovilesfinal2c.R;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
    private ArrayList<Pago> lista;
    private View root;
    private LayoutInflater inflater;

    public PagosAdapter(ArrayList<Pago> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pagos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        Pago p = lista.get(position);
        holder.cod.setText(p.getIdPago());
        holder.num.setText(p.getNumero());
        holder.codCon.setText(p.getContrato().getIdContrato());
        holder.Imp.setText(p.getImporte()+"");
        holder.fecha.setText(p.getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText cod, num, codCon, Imp, fecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cod = itemView.findViewById(R.id.etPagosCod);
            num = itemView.findViewById(R.id.etPagosNumPago);
            codCon = itemView.findViewById(R.id.etPagosCodCon);
            Imp = itemView.findViewById(R.id.etPagosImp);
            fecha = itemView.findViewById(R.id.etPagosFecha);
        }
    }
}
