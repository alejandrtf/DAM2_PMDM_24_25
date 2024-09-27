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

public class MainActivity extends AppCompatActivity {
    EditText etPalabraArriba, etPalabraAbajo;
    Button btSubir, btBajar, btEnviar;
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

        btSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto = etPalabraAbajo.getText().toString();

                if (texto.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Debes introducir un texto", Toast.LENGTH_SHORT).show();
                } else {
                    etPalabraArriba.setText(texto);
                    etPalabraAbajo.setText("");
                }
            }
        });
    }


    /**
     * Métood que inicializa las referencias de las vistas XML
     */
    private void initReferences() {
        etPalabraArriba = findViewById(R.id.etPalabraArriba);
        etPalabraAbajo = findViewById(R.id.etPalabraAbajo);
        btBajar = findViewById(R.id.btBajar);
        btSubir = findViewById(R.id.btSubir);
    }


    /** Método que se ejecuta al pulsar el botón BAJAR
     *
     * @param view la vista pulsada (en este caso el botón BAJAR)
     */
    public void bajarTexto(View view) {
        texto = etPalabraArriba.getText().toString();

        if (texto.isEmpty()) {
            Toast.makeText(MainActivity.this, "Debes introducir un texto", Toast.LENGTH_SHORT).show();
        } else {
            etPalabraAbajo.setText(texto);
            etPalabraArriba.setText("");
        }
    }
}