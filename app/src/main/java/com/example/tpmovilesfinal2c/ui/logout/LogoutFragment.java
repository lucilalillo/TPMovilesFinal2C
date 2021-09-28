package com.example.tpmovilesfinal2c.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpmovilesfinal2c.R;

public class LogoutFragment extends Fragment {

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.logout_fragment, container, false);
        notificacion();
        return root;

    }
    public void notificacion() {
        new AlertDialog.Builder(getContext())
                .setTitle("Cerrar Sesión")
                .setMessage("¿Desea cerrar la Sesión?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Navigation.findNavController(getActivity(), R.id.nav_home).navigate(R.id.nav_home);
                    }
                }).show();
    }
}