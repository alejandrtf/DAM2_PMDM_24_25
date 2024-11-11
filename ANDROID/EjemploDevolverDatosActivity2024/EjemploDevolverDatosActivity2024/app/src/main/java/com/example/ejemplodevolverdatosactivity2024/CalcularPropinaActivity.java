package com.example.ejemplodevolverdatosactivity2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class CalcularPropinaActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_PROPINA = "propina";
    public static final String EXTRA_TOTAL_PAGAR = "total_pagar";
    // VIEWS
    TextView tvImporteRecibido;
    RadioGroup rgPorcentajePropina;
    Button btCalcularPropina, btCancelar;

    // DATOS
    double importeSinPropina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcular_propina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calcularPropina), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initReferences();
        // RECOGER DATOS
        Intent iDatos = getIntent();
        if (iDatos.hasExtra(ImporteFacturaActivity.EXTRA_IMPORTE)) {
            importeSinPropina = iDatos.getDoubleExtra(ImporteFacturaActivity.EXTRA_IMPORTE, 0.0);
        }
        setListenerToButtons();
    }


    private void setListenerToButtons() {
        btCancelar.setOnClickListener(this);
        btCalcularPropina.setOnClickListener(this);
    }


    /**
     * Método que asigna las referencias a las vistas XML
     */
    private void initReferences() {
        tvImporteRecibido = findViewById(R.id.tvImporteSinPropina);
        rgPorcentajePropina = findViewById(R.id.rgPorcentajesPropina);
        btCalcularPropina = findViewById(R.id.btCalcularPropinaPorcentaje);
        btCancelar = findViewById(R.id.btCancelar);
    }

    /**
     * Método que se ejecuta al pulsar cualquier botón de la pantalla
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.btCalcularPropinaPorcentaje){
            // botón calcular propina
            Bundle infoPropinaDevolver = calcularPropinaSegunPorcentaje(importeSinPropina);
            // devolver datos a la primera pantalla
            Intent i=new Intent();
            i.putExtras(infoPropinaDevolver);
            setResult(RESULT_OK,i);
            finish();
        } else{
            // botón cancelar
            finish();
        }
    }


    /** Método que calcula la propina correspondiente al importe que se le pasa en
     *  función del RadioButton elegido y devuelve un Bundle con la propina y el total a pagar
     *
     * @param importeSinPropina importe sin propina
     * @return Bundle con la información de la propina (valor, total a pagar)
     */
    private Bundle calcularPropinaSegunPorcentaje(double importeSinPropina) {
        int idRadioButtonElegido = rgPorcentajePropina.getCheckedRadioButtonId();
        RadioButton rbElegido =findViewById(idRadioButtonElegido);
        String textoPorcentaje = rbElegido.getText().toString().split("%")[0];
        double porcentajeAplicar=Double.parseDouble(textoPorcentaje);
        double propina=(importeSinPropina*porcentajeAplicar)/100;
        double totalPagar=importeSinPropina+propina;

        Bundle b=new Bundle();
        b.putDouble(EXTRA_PROPINA,propina);
        b.putDouble(EXTRA_TOTAL_PAGAR,totalPagar);
        return b;
    }
}