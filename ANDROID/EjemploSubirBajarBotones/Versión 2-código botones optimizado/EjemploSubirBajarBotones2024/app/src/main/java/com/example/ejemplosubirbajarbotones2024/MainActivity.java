package com.example.ejemplosubirbajarbotones2024;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPalabraArriba, etPalabraAbajo;
    Button btSubir, btBajar;
    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initReferences();

        btSubir.setOnClickListener(this);
        btBajar.setOnClickListener(this);
    }


    /**
     * Método que inicializa las referencias de las vistas XML
     */
    private void initReferences() {
        etPalabraArriba = findViewById(R.id.etPalabraArriba);
        etPalabraAbajo = findViewById(R.id.etPalabraAbajo);
        btBajar = findViewById(R.id.btBajar);
        btSubir = findViewById(R.id.btSubir);
    }


    /**
     * Método que se ejecuta al pulsar botones de subir y bajar
     *
     * @param origen  el cuadro de texto que contiene el texto a mover
     * @param destino el cuadro de texto destino
     */
    private void moverTexto(EditText origen, EditText destino) {
        texto = origen.getText().toString();

        if (texto.isEmpty()) {
            Toast.makeText(this, "Debes introducir un texto", Toast.LENGTH_SHORT).show();
        } else {
            destino.setText(texto);
            origen.setText("");
        }
    }


    /**
     * Este método se ejecutaría para todos los botones del MainActivity
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btBajar) {
            // he pulsado el botón BAJAR
            moverTexto(etPalabraArriba,etPalabraAbajo);
        } else {
            // he pulsado el botón SUBIR
           moverTexto(etPalabraAbajo,etPalabraArriba);
        }
    }
}