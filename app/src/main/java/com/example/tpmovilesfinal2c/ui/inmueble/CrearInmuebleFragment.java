package com.example.tpmovilesfinal2c.ui.inmueble;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tpmovilesfinal2c.Modelo.Tipo;
import com.example.tpmovilesfinal2c.R;
import com.example.tpmovilesfinal2c.databinding.FragmentCrearInmuebleBinding;

import java.util.List;

public class CrearInmuebleFragment extends Fragment {

    private CrearInmuebleViewModel mViewModel;
    private FragmentCrearInmuebleBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;
    public static CrearInmuebleFragment newInstance() {
        return new CrearInmuebleFragment();
    }
    private Spinner spinnerTipo;
    private Uri uriImagen;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        binding = FragmentCrearInmuebleBinding.inflate(getLayoutInflater());

        abrirGaleria();
        binding.btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });

        spinnerTipo = binding.getRoot().findViewById(R.id.spTipo);

        mViewModel.getMSpinner().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerTipo.setAdapter(adapter);
            }
        });
        mViewModel.getMTipos().observe(getViewLifecycleOwner(), new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                mViewModel.cargarSpinner(tipos);
            }
        });

        mViewModel.getMUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                binding.ivFotoDetInmu.setImageURI(uri);
                uriImagen = uri;
            }
        });

        mViewModel.cargarDatosTipo();

        binding.btnGuardarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String direccion = binding.tvDetDir.getText().toString();
                int ambientes = Integer.parseInt(binding.tvDetAmb.getText().toString());
                String uso = binding.tvDetUso.getText().toString();
                int importe = Integer.parseInt(binding.tvDetPrecio.getText().toString());
                String tipoDesc = spinnerTipo.getSelectedItem().toString();
                mViewModel.guardarInmueble(direccion, ambientes, uso, importe, tipoDesc, uriImagen);
            }
        });

        return binding.getRoot();
    }

    private void abrirGaleria() {
        intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                mViewModel.recibirFoto(result);
            }
        });

    }


}