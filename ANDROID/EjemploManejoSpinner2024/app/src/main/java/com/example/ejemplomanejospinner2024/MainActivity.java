package com.example.ejemplomanejospinner2024;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // VIEWS
    Spinner spProcesadores,         // TOMA LOS DATOS DE UN String[] de java
            spEstadoCivil,          // TOMA LOS DATOS DE UN <array-string> de XML
            spPaises,               // TOMA LOS DATOS DE UN <string-arrray> de XML y los convierte a String [] de java
            spProductosAComprar,    // TOMA LOS DATOS DE UN ArrayList<String>
            spCarritoCompra;        // SE VA RELLENANDO EN TIEMPO DE EJECUCIÓN AL PULSAR EL BOTÓN AÑADIR CARRITO
    Button btAddCarrito, btBorrarCarrito;

    // FUENTE DE DATOS: ARRAYS
    private String[] tiposProcesadores;
    private List<String> productosAComprar;
    private List<String> carritoCompra;

    // ADAPTADORES
    private ArrayAdapter<String> adapterProcesadores;
    private ArrayAdapter<CharSequence> adapterEstadosCiviles;
    private ArrayAdapter<String> adapterPaises;
    private ArrayAdapter<String> adapterProductosComprar;
    private ArrayAdapter<String> adapterCarrito;


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
        setListenerToButtons();

        // SPINNER PROCESADORES: Ej. de cómo cargar un spinner con datos de un String[] java
        configurarSpinnerProcesadores();
        // SPINNER ESTADOS CIVILES: OPCIÓN1: Ej de cómo cargar un spinner con datos de un <string-array> de XML
        configurarSpinnerEstadosCiviles();
        // SPINNER PAISES: OPCIÓN2: Ej de cómo cargar un spinner con datos de un <string-array> de XML pero
        // convertimos a String[] de java antes de asignarlo al adapter;
        configurarSpinnerPaises();
        // SPINNER PRODUCTOS A COMPRAR: Ej de cómo cargar un spinner con datos de un ArrayList<String>
        configurarSpinnerProductosAComprar();

        // SPINNER CARRITO COMPRA: Ej de cómo ir rellenando un spinner en tiempo de ejecución y de
        // borra datos de un spinner en tiempo de ejecución según se pulse botón ADD o DELETE
        configurarSpinnerCarritoComprar();
    }

    /**
     * Método inicializa las vistas xml
     */
    private void initReferences() {
        spProcesadores = findViewById(R.id.spProcesadores);
        spPaises = findViewById(R.id.spPaises);
        spEstadoCivil = findViewById(R.id.spEstadoCivil);
        spProductosAComprar = findViewById(R.id.spProductosDisponibles);
        spCarritoCompra = findViewById(R.id.spCarrito);
        btAddCarrito = findViewById(R.id.btAniadirCesta);
        btBorrarCarrito = findViewById(R.id.btBorrarDelCarrito);
    }


    /**
     * Método que asigna los escuchadores a los botones
     */
    private void setListenerToButtons() {
        btBorrarCarrito.setOnClickListener(this);
        btAddCarrito.setOnClickListener(this);
    }


    /**
     * Método que configura el spinner procesadores
     */
    private void configurarSpinnerProcesadores() {
        // inicializar los datos
        tiposProcesadores = new String[]{"Elige uno", "i3", "i5", "i7", "i9"};

        // configurar el spinner
        adapterProcesadores = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposProcesadores);
        adapterProcesadores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProcesadores.setAdapter(adapterProcesadores);
        spProcesadores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String elegido = parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * Método que configura spinner estados civiles
     */
    private void configurarSpinnerEstadosCiviles() {
        adapterEstadosCiviles = ArrayAdapter.createFromResource(this, R.array.estados_civiles, android.R.layout.simple_spinner_item);
        adapterEstadosCiviles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstadoCivil.setAdapter(adapterEstadosCiviles);
        spEstadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String elegido = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método que configura spinner países
     */
    private void configurarSpinnerPaises() {
        // recuperar los datos del XML
        String[] paisesArray = getResources().getStringArray(R.array.paises);
        // creamos adaptador
        adapterPaises = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paisesArray);
        adapterPaises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPaises.setAdapter(adapterPaises);
        spPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String elegido = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();

            }
        });
    }


    /**
     * Método que configura spinner productos a comprar
     */
    private void configurarSpinnerProductosAComprar() {
        // inicializar el ArrayList
        productosAComprar = new ArrayList<>();
        productosAComprar.add("Leche");
        productosAComprar.add("Pan");
        productosAComprar.add("Detergente lavadora");
        productosAComprar.add("Champú");
        productosAComprar.add("Café");
        productosAComprar.add("Galletas");

        // configurar el spinner
        adapterProductosComprar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productosAComprar);
        adapterProductosComprar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProductosAComprar.setAdapter(adapterProductosComprar);
        spProductosAComprar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String elegido = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();

            }
        });

    }


    /**
     * Configura el spinner del carrito de la compra
     */
    private void configurarSpinnerCarritoComprar() {
        // inicializar el ArrayList
        carritoCompra = new ArrayList<>();


        // configurar el spinner
        adapterCarrito = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carritoCompra);
        adapterCarrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCarritoCompra.setAdapter(adapterCarrito);
        spCarritoCompra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String elegido = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Has elegido " + elegido, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No has elegido nada", Toast.LENGTH_SHORT).show();

            }
        });
    }



    /**
     * Método que se ejectua al pulsar botones de añadir y borrar del carrito
     *
     * @param view el botón que fue pulsado
     */

    @Override
    public void onClick(View view) {
        String productoElegido=(String)spProductosAComprar.getSelectedItem();
        int pos=adapterCarrito.getPosition(productoElegido);
        if(view.getId()==R.id.btAniadirCesta){
            if(pos<0){
                // no existe en el carrito ese producto aún
                adapterCarrito.add(productoElegido);
                adapterCarrito.notifyDataSetChanged();
                spCarritoCompra.setSelection(adapterCarrito.getPosition(productoElegido));
            }else{
                Toast.makeText(this, "Ese producto ya está en el carrito" , Toast.LENGTH_SHORT).show();
            }
        }else{
            if(pos>=0){
                // ese producto existe en el carrito y por tanto, puedo borrarlo
                adapterCarrito.remove(productoElegido);
                adapterCarrito.notifyDataSetChanged();
            }else{
                Toast.makeText(this, "Ese producto no está en el carrito", Toast.LENGTH_SHORT).show();
            }
        }

    }
}