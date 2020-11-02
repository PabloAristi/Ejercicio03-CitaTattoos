package com.pabloaristi.ejercicio03_citatattoos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pabloaristi.ejercicio03_citatattoos.configuraciones.Configuraciones;
import com.pabloaristi.ejercicio03_citatattoos.modelos.CitaTattoo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditCitaActivity extends AppCompatActivity {


    private EditText txtNombre, txtApellidos, txtFechaNacimiento, txtFechaCita, txtAdelanto;
    private Switch swColor, swAutorizado;
    private Button btnEliminar, btnGuardar;

    private CitaTattoo citaTattoo;
    private int posicion;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cita2);

        citaTattoo = getIntent().getExtras().getParcelable("CITA");
        posicion = getIntent().getExtras().getInt("POS");
        inicializaInterfaz();
        rellenaInformacion();

        final LocalDate hoy = LocalDate.now();

        swAutorizado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnGuardar.setEnabled(isChecked);
            }
        });

        txtFechaNacimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fecha = Configuraciones.simpleDateFormat.parse(s.toString());
                    LocalDate fechaNacimiento = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (hoy.getYear() - fechaNacimiento.getYear() < 18){
                        swAutorizado.setVisibility(View.VISIBLE);
                        swAutorizado.setChecked(true);
                        btnGuardar.setEnabled(false);
                    }
                    else{
                        swAutorizado.setVisibility(View.GONE);
                        swAutorizado.setChecked(false);
                        btnGuardar.setEnabled(true);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtFechaCita.getText().toString().isEmpty() &&
                        !txtFechaNacimiento.getText().toString().isEmpty()){
                    CitaTattoo citaTattoo = new CitaTattoo();
                    citaTattoo.setNombre(txtNombre.getText().toString());
                    citaTattoo.setApellidos(txtApellidos.getText().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/aaaa");
                    try {
                        citaTattoo.setFechaCita(sdf.parse(txtFechaCita.getText().toString()));
                        citaTattoo.setFechaNacimiento(sdf.parse(txtFechaNacimiento.getText().toString()));
                        if (txtAdelanto.getText().toString().isEmpty())
                            citaTattoo.setAdelanto(0);
                        else
                            citaTattoo.setAdelanto(Float.parseFloat(txtAdelanto.getText().toString()));
                        citaTattoo.setaColor(swColor.isChecked());
                        citaTattoo.setAutorizado(swAutorizado.isChecked());

                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CITA", citaTattoo);
                        bundle.putInt("POS", posicion);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (ParseException e) {
                        System.err.println(Toast.makeText(EditCitaActivity.this, "Fechas incorrectas", Toast.LENGTH_SHORT));
                    }

                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("POS", posicion);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void rellenaInformacion() {
        txtNombre.setText(citaTattoo.getNombre());
        txtApellidos.setText(citaTattoo.getApellidos());
        txtFechaCita.setText(Configuraciones.simpleDateFormat.format(citaTattoo.getFechaCita()));
        txtFechaNacimiento.setText(Configuraciones.simpleDateFormat.format(citaTattoo.getFechaNacimiento()));
        txtAdelanto.setText(String.valueOf(citaTattoo.getAdelanto()));
        if (citaTattoo.isAutorizado()){
            swAutorizado.setVisibility(View.VISIBLE);
            swAutorizado.setChecked(citaTattoo.isAutorizado());
        }
        swColor.setChecked(citaTattoo.isaColor());
    }

    private void inicializaInterfaz() {
        txtNombre = findViewById(R.id.txtNombreCita);
        txtApellidos = findViewById(R.id.txtApellidosCita);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimientoCita);
        txtFechaCita = findViewById(R.id.txtFechaCitaCita);
        txtAdelanto = findViewById(R.id.txtAdelantoCita);
        swAutorizado = findViewById(R.id.swAutorizadoCita);
        swColor = findViewById(R.id.swColorCita);
        btnEliminar = findViewById(R.id.btnEliminarCita);
        btnGuardar = findViewById(R.id.btnGuardarCita);

        if (citaTattoo.isAutorizado()) {
            swAutorizado.setVisibility(View.VISIBLE);
            swAutorizado.setSelected(citaTattoo.isAutorizado());
        }
        else {
            swAutorizado.setVisibility(View.GONE);
            swAutorizado.setSelected(false);
        }



    }
}
