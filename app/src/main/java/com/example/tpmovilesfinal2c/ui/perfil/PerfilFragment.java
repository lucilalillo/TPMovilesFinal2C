package com.example.tpmovilesfinal2c.ui.perfil;

import static java.lang.Integer.parseInt;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpmovilesfinal2c.Modelo.Propietario;
import com.example.tpmovilesfinal2c.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel pvm;
    private EditText etId, etDni, etNombre, etApellido, etMail, etPass, etTel, etContra, etRepetirContra, etClaveActual;
    private Button btGuardar, btEditar, btCambiarClave, btGuardarClave;
    private ImageView ivProp;
    private TextView tvClaveActual;
    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        pvm = new ViewModelProvider(this).get(PerfilViewModel.class);

        View vistaPerfil = inflater.inflate(R.layout.perfil_fragment, container, false);
        inicializarVista(vistaPerfil);

        pvm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etDni.setText(propietario.getDni());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etMail.setText(propietario.getEmail());
                etTel.setText(propietario.getTelefono());

                Glide.with(getContext())
                        .load("http://192.168.0.104:5001/"+propietario.getAvatarUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL) //cada vez q cargues imagen de propietario queda en cache
                        .into(ivProp);
            }
        });

        pvm.getEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                etDni.setEnabled(aBoolean);
                etNombre.setEnabled(aBoolean);
                etApellido.setEnabled(aBoolean);
                etMail.setEnabled(aBoolean);
                etTel.setEnabled(aBoolean);
            }
        });

        pvm.getEditarVisible().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btEditar.setVisibility(integer);
            }
        });

        pvm.getGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                btGuardar.setVisibility(integer);
            }
        });
        pvm.obtenerPropietarioActual();
        return vistaPerfil;
    }

    private void inicializarVista(View vistaPerfil) {
        etDni = vistaPerfil.findViewById(R.id.etDni);
        etNombre = vistaPerfil.findViewById(R.id.etNombre);
        etApellido = vistaPerfil.findViewById(R.id.etApellido);
        etMail = vistaPerfil.findViewById(R.id.etMail);
        etTel = vistaPerfil.findViewById(R.id.etTel);
        ivProp = vistaPerfil.findViewById(R.id.ivProp);
        btEditar = vistaPerfil.findViewById(R.id.btnEditar);
        btGuardar = vistaPerfil.findViewById(R.id.btnGuardar);
        btCambiarClave = vistaPerfil.findViewById(R.id.btnCClave);
        etContra = vistaPerfil.findViewById(R.id.etContra);
        etRepetirContra = vistaPerfil.findViewById(R.id.etRepetirContra);
        btGuardarClave = vistaPerfil.findViewById(R.id.btnGuardarClave);
        etClaveActual = vistaPerfil.findViewById(R.id.etClaveActual);
        //editar habilita la edicion
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvm.guardarDatos();

            }
        });

        //guarda cambios y deshabilita la edicion
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
                p.setDni(etDni.getText().toString());
                p.setNombre(etNombre.getText().toString());
                p.setApellido(etApellido.getText().toString());
                p.setEmail(etMail.getText().toString());
                p.setTelefono(etTel.getText().toString());

                pvm.editarDatos(p);
            }
        });

        btCambiarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContra.setVisibility(View.VISIBLE);
                etRepetirContra.setVisibility(View.VISIBLE);
                etClaveActual.setVisibility(View.VISIBLE);
                btCambiarClave.setVisibility(View.GONE);
                btGuardar.setVisibility(View.GONE);
                btGuardarClave.setVisibility(View.VISIBLE);
                btEditar.setVisibility(View.GONE);
            }
        });

        btGuardarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String claveActual = etClaveActual.getText().toString();
                String contra = etContra.getText().toString();
                String rep = etRepetirContra.getText().toString();
                pvm.cambiarPass(contra, rep, claveActual);

                etContra.setVisibility(View.GONE);
                etRepetirContra.setVisibility(View.GONE);
                etClaveActual.setVisibility(View.GONE);
                btCambiarClave.setVisibility(View.VISIBLE);
                btGuardar.setVisibility(View.INVISIBLE);
                btGuardarClave.setVisibility(View.GONE);
                btEditar.setVisibility(View.VISIBLE);
            }
        });
    }

}