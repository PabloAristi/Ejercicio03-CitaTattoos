package com.pabloaristi.ejercicio03_citatattoos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pabloaristi.ejercicio03_citatattoos.R;
import com.pabloaristi.ejercicio03_citatattoos.configuraciones.Configuraciones;
import com.pabloaristi.ejercicio03_citatattoos.modelos.CitaTattoo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class AdapterCitasTattoo extends ArrayAdapter<CitaTattoo> {
    private Context context;
    private int resource;
    private ArrayList<CitaTattoo> objects;


    public AdapterCitasTattoo(@NonNull Context context, int resource, @NonNull ArrayList<CitaTattoo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View fila = LayoutInflater.from(context).inflate(resource, null);
        CitaTattoo citaTattoo = objects.get(position);

        TextView txtNombre = fila.findViewById(R.id.txtNombreFila);
        TextView txtFechaCita = fila.findViewById(R.id.txtFechaFila);

        txtNombre.setText(citaTattoo.getNombre());

        txtFechaCita.setText(Configuraciones.simpleDateFormat.format(citaTattoo.getFechaCita()));

        return fila;
    }
}
