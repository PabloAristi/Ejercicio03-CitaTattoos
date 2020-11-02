package com.pabloaristi.ejercicio03_citatattoos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pabloaristi.ejercicio03_citatattoos.adapters.AdapterCitasTattoo;
import com.pabloaristi.ejercicio03_citatattoos.modelos.CitaTattoo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_CITA = 23 ;
    private static final int EDIT_CITA = 100;
    //Modelo Datos
    private ArrayList<CitaTattoo> listaCitas;
    // Contenedor - Adapter asociado
    private ListView contenedorCitas;
    private AdapterCitasTattoo adapterCitasTattoo;

    //Fila o elemento que se repite
    private int filaCita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaCitas = new ArrayList<>();
        contenedorCitas = findViewById(R.id.contenedorCitas);
        filaCita = R.layout.fila_cita_tattoo;
        adapterCitasTattoo = new AdapterCitasTattoo(this, filaCita, listaCitas);
        contenedorCitas.setAdapter(adapterCitasTattoo);

        contenedorCitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("POS", position);
                CitaTattoo citaTattoo = listaCitas.get(position);
                bundle.putParcelable("CITA", citaTattoo);
                Intent intent = new Intent(MainActivity.this, EditCitaActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_CITA);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivityForResult(new Intent(MainActivity.this,NewCitaActivity2.class), ADD_CITA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CITA && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null){
                CitaTattoo citaTattoo = data.getExtras().getParcelable("CITA");
                listaCitas.add(citaTattoo);
                adapterCitasTattoo.notifyDataSetChanged();
            }
        }
        if (requestCode == EDIT_CITA && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null){
                int posicion = data.getExtras().getInt("POS");
                CitaTattoo citaTattoo = data.getExtras().getParcelable("CITA");
                if (citaTattoo == null){
                    listaCitas.remove(posicion);
                }
                else {
                    listaCitas.set(posicion, citaTattoo);
                }
                adapterCitasTattoo.notifyDataSetChanged();
            }
        }
    }
}