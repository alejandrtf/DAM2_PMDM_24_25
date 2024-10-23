package com.example.ejemplosubirbajarbotones2024;

import android.content.Intent;
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
    // CONSTANTS
    protected static final String EXTRA_TEXTO = "texto_escrito";

    // VIEWS
    EditText etPalabraArriba, etPalabraAbajo;
    Button btSubir, btBajar, btEnviar;


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
        setListenersToButtons();
    }


    /**
     * Método que inicializa las referencias de las vistas XML
     */
    private void initReferences() {
        etPalabraArriba = findViewById(R.id.etPalabraArriba);
        etPalabraAbajo = findViewById(R.id.etPalabraAbajo);
        btBajar = findViewById(R.id.btBajar);
        btSubir = findViewById(R.id.btSubir);
        btEnviar = findViewById(R.id.btEnviar);

    }

    /**
     * Método que asigna los escuchadores a los botones
     */
    private void setListenersToButtons() {
        btBajar.setOnClickListener(this);
        btSubir.setOnClickListener(this);
        btEnviar.setOnClickListener(this);
    }


    /**
     * Método que se ejecuta al pulsar botones de subir y bajar
     *
     * @param origen  el cuadro de texto que contiene el texto a mover
     * @param destino el cuadro de texto destino
     */
    private void moverTexto(EditText origen, EditText destino) {
        String texto; // texto de los cuadros de texto

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
            moverTexto(etPalabraArriba, etPalabraAbajo);
        } else if (v.getId() == R.id.btSubir) {
            // he pulsado el botón SUBIR
            moverTexto(etPalabraAbajo, etPalabraArriba);
        } else {
            // he pulsado el botón ENVIAR
            String textoAEnviar = "";
            if (!etPalabraArriba.getText().toString().isEmpty()) {
                textoAEnviar = etPalabraArriba.getText().toString();
            } else if (!etPalabraAbajo.getText().toString().isEmpty()) {
                textoAEnviar = etPalabraAbajo.getText().toString();
            } else {
                // ambos texto vacíos
                Toast.makeText(this, "DEBES ESCRIBIR ALGO", Toast.LENGTH_SHORT).show();
            }

            if (!textoAEnviar.isEmpty()) {
                // Si hay texto para enviar (no está vacío)
                // código para lanzar segunda pantalla

                lanzarPantallaDetalle(textoAEnviar);
            }
        }

    }

    /**
     * Método que lanza la pantalla de detalle
     *
     * @param texto texto a enviar a la segunda pantalla
     */
    private void lanzarPantallaDetalle(String texto) {
        //creamos un intent indicando qué pantalla queremos lanzar
        Intent iPantallaDetalle = new Intent(this, PantallaDetalle.class);
        // añadimos como extra al intent, el texto que se quiere enviar a la segunda pantalla
        iPantallaDetalle.putExtra(EXTRA_TEXTO, texto);
        // se lanza la segunda pantalla
        startActivity(iPantallaDetalle);
    }
}