package com.example.stattrack.controllers;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.stattrack.CategoriaListAdapter;
import com.example.stattrack.EditarActivity;
import com.example.stattrack.R;
import com.example.stattrack.models.Categoria;
import com.example.stattrack.models.CategoriaDao;
import com.example.stattrack.models.Gasto;
import com.example.stattrack.models.GastoDao;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController implements GastoDao.GastoCallback{

    private Context context;//declaracion de objeto Context
    private EditarActivity view; //declaracion de view
    private Spinner spinner; //declaracion de objeto spinner

    private ArrayList<String> spinnerDataList; //arraylist de tipo String
    private EditText editText;

    /**
     * Constructor de la clase con dos parametros
     * @param context
     * @param view
     */
    public CategoriaController(Context context, EditarActivity view){
        this.context = context;
        this.view = view;
        spinner = view.findViewById(R.id.spinner3);
        editText = view.findViewById(R.id.editTextNumber);

        editText.setText("00");
    }

    /**
     * Permite añadir el gasto
     * @param gasto
     */
    public void subirGasto(Gasto gasto) {
        GastoDao gastoDao = new GastoDao(context).enviarGasto(gasto);
    }

    /**
     * Permite comprobar si los gastos sobrepasan el presupuesto asignado
     * @param id
     */
    public void comprobarGasto(int id) {
        GastoDao gastoDao = new GastoDao(context);
        gastoDao.comprobarGasto(id, this);
    }

    /**
     * Muestra mensaje al usuario si esta a punto de alcanzar el presupuesto
     * @param resultado
     */
    @Override
    public void onComprobarGastoResult(int resultado) {
        if(resultado == 0 ){
            System.out.println("jojo");
        }else{
            System.out.println("ijiji");
            Toast.makeText(context, "cantidad de Gastos Alta", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Muestra las categorias
     */
    public void mostrarCategorias( ){
        CategoriaDao categoriaDao = new CategoriaDao(context).obtenerCategorias(new CategoriaDao.CallBack() {
            @Override
            public void onSuccess(List<Categoria> categorias) {
                List<String> nombresCategorias = new ArrayList<>();

                for (Categoria categoria : categorias) {
                    nombresCategorias.add(categoria.getCatNombre());
                }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, nombresCategorias);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();
                        // Realiza las acciones necesarias con el elemento seleccionado
                        // ...
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // No se seleccionó ningún elemento
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
