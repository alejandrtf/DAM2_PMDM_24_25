package com.example.ejemplofragmentsestaticos;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RosaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RosaFragment extends Fragment {
    // VIEWS
    TextView tvPalabraAlReves;
    Button btContarLetras;

    // INTERFACE LISTENER
    OnBotonContarPulsadoListener mListener;

    public RosaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RosaFragment.
     */

    public static RosaFragment newInstance() {
        RosaFragment fragment = new RosaFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnBotonContarPulsadoListener){
            mListener= (OnBotonContarPulsadoListener) context;
        }else{
            throw new ClassCastException(context.toString() + " debe implementar el interface OnBotonContarPulsadoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mListener!=null)
            mListener=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_rosa, container, false);
        tvPalabraAlReves=v.findViewById(R.id.tvAlReves);
        btContarLetras=v.findViewById(R.id.btContarLetras);
        btContarLetras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBotonContarPulsado(tvPalabraAlReves.getText().toString().length());
            }
        });
        return v;
    }


    /**
     * Mostrar en un TextView la palabra que se le pasa
     *
     * @param palabra texto a mostrar
     */
    public void mostrarPalabra(String palabra) {
        tvPalabraAlReves.setText(palabra);
    }

    /**
     * Interface que comunica el fragment rosa con la activity: envía hacia afuera un número
     * Es llamado al pulsar el botón CONTARLETRAS
     */
    public interface OnBotonContarPulsadoListener{
        void onBotonContarPulsado(int numero);
    }
}