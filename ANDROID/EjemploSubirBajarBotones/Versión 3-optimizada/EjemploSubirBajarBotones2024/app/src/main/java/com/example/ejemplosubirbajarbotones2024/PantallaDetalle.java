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
    TextView tvTextoRecibido;

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

        initReferences();

        // recojo los datos que me llegan
        String textoQueLlega=getDatosFromMainActivity();
        if(!textoQueLlega.isEmpty()){
            // muestro el texto en la pantalla
            tvTextoRecibido.setText(textoQueLlega);
        }


    }

    /**
     * Método que recoge los datos que se reciben en el intent que envía
     * MainActivity para lanzar esta segunda pantalla
     *
     * @return  el texto recibido o un string vacío si no se recibe nada
     */

    private String getDatosFromMainActivity() {
        // recojo el intent que llega de la primera pantalla
        Intent intent=getIntent();
        if(intent.hasExtra(MainActivity.EXTRA_TEXTO)){
            // extraigo el texto que viene como extra en el intent
            String texto=intent.getStringExtra(MainActivity.EXTRA_TEXTO);
            return texto;
        }else return "";
    }

    /** Método que inicializa las referencias a las vistas XML
     */
    private void initReferences() {
        tvTextoRecibido =findViewById(R.id.tvTextoRecibido);
    }
}