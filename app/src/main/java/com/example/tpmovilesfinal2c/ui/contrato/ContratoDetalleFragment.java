package com.example.tpmovilesfinal2c.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tpmovilesfinal2c.Modelo.Contrato;
import com.example.tpmovilesfinal2c.R;

public class ContratoDetalleFragment extends Fragment {

    private ContratoDetalleViewModel contratoDetalleViewModel;
    private EditText cod, fecInicio, fecFin, monto, nombreInq, direcInmu;
    private Button pagos;

    public static ContratoDetalleFragment newInstance() {
        return new ContratoDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contratoDetalleViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);

        View root = inflater.inflate(R.layout.contrato_detalle_fragment, container, false);
        inicializarVista(root);
        contratoDetalleViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                cod.setText(contrato.getIdContrato()+"");
                fecInicio.setText(contrato.getFechaInicio()+"");
                fecFin.setText(contrato.getFechaFin()+"");
                monto.setText(contrato.getMontoAlquiler()+"");
                nombreInq.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
                direcInmu.setText(contrato.getInmueble().getDireccion());
                pagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagos", contrato);
                        Navigation.findNavController(v).navigate(R.id.pagosFragment, bundle);
                    }
                });

            }
        });
        contratoDetalleViewModel.setInmueble(getArguments());
        return root;
    }

    public void inicializarVista(View root){
        cod = root.findViewById(R.id.etConId);
        fecInicio = root.findViewById(R.id.etConFecIni);
        fecFin = root.findViewById(R.id.etConFecFin);
        monto = root.findViewById(R.id.etConMonto);
        nombreInq = root.findViewById(R.id.etConNomInq);
        direcInmu = root.findViewById(R.id.etConDireccion);
        pagos = root.findViewById(R.id.btPagos);
    }

}