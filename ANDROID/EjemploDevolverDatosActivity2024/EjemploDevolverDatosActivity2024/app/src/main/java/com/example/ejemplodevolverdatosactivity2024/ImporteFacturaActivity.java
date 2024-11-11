package com.example.ejemplodevolverdatosactivity2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImporteFacturaActivity extends AppCompatActivity {
    public static final String EXTRA_IMPORTE = "importe";
    // VIEWS
    EditText etImporte;
    TextView tvPropina, tvTotalPagar;

    // LANZADOR
    ActivityResultLauncher <Intent> mLauncherCalcularPropinaActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // mostrar los datos que me vienen de la segunda pantalla en esta pantalla
                if(result.getResultCode()==RESULT_OK){
                    Intent data = result.getData();
                    actualiarUI(data);
                }
            }
    );

    private void actualiarUI(Intent data) {
        if(data!=null){
            if(data.hasExtra(CalcularPropinaActivity.EXTRA_PROPINA) && data.hasExtra(CalcularPropinaActivity.EXTRA_TOTAL_PAGAR)){
                Bundle infoPropina=data.getExtras();

                double propina=infoPropina.getDouble(CalcularPropinaActivity.EXTRA_PROPINA);
                double totalPagar=infoPropina.getDouble(CalcularPropinaActivity.EXTRA_TOTAL_PAGAR);
                tvTotalPagar.setText(String.valueOf(totalPagar));
                tvPropina.setText(String.valueOf(propina));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_importe_factura);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initReferences();
    }


    /** Método que inicializa las vistas del XML
     *
     */
    private void initReferences() {
        etImporte=findViewById(R.id.etImporte);
        tvPropina=findViewById(R.id.tvPropina);
        tvTotalPagar=findViewById(R.id.tvTotalPagar);
    }


    /** Método que se ejecuta al pulsar el botón CALCULAR PROPINA y que lanza la pantalla
     *  CalcularPropinaActivity
     *
     * @param view el botón pulsado
     */
    public void lanzarCalcularPropinaActivity(View view) {
        String importeTexto = etImporte.getText().toString();
        if(importeTexto.isEmpty()){
            etImporte.setError(getString(R.string.error_debes_introducir_un_importe));
        }else{
            Intent intent=new Intent(this,CalcularPropinaActivity.class);
            intent.putExtra(EXTRA_IMPORTE,Double.parseDouble(importeTexto));
            mLauncherCalcularPropinaActivity.launch(intent);
        }
    }
}