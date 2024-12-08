package com.example.ejemplofragmentsestaticos;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements GrisFragment.OnInvertirPulsado,
        RosaFragment.OnBotonContarPulsadoListener {

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


    }

    /**
     * Método que se ejecuta al pulsar el botón INVERTIR en el fragment gris
     *
     * @param palabra palabra invertida
     */
    @Override
    public void onInvertirPulsado(String palabra) {
        RosaFragment rosaFragment= (RosaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRosa);
        if(rosaFragment!=null){
            // estoy en una pantalla con los dos fragments (multipanel)
            rosaFragment.mostrarPalabra(palabra);
        }
    }


    /**
     * Método que se ejecuta al pulsar el botón CONTAR LETRAS del fragment rosa
     *
     * @param numero es el nº de letras de la palabra escrita
     */
    @Override
    public void onBotonContarPulsado(int numero) {
        TextView tvNumeroLetras=findViewById(R.id.tvNumeroLetras);
        tvNumeroLetras.setText(String.valueOf(numero));
        tvNumeroLetras.setVisibility(View.VISIBLE);
    }
}