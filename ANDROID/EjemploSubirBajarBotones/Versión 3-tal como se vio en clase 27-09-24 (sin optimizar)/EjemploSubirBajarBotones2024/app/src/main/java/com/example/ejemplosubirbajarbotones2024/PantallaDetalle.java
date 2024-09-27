package com.example.ejemplosubirbajarbotones2024;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PantallaDetalle extends AppCompatActivity {
    TextView tvTextoMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_detalle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTextoMostrar=findViewById(R.id.tvTextoRecibido);
        // recojo el intent que llega de la primera pantalla
        Intent i=getIntent();
        // extraigo el texto que viene como extra en el intent
        String texto=i.getStringExtra("texto");
        // muestro el texto
        tvTextoMostrar.setText(texto);
    }
}