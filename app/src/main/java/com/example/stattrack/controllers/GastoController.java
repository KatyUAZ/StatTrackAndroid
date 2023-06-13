package com.example.stattrack.controllers;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.stattrack.models.Gasto;
import com.example.stattrack.models.GastoDao;
import com.example.stattrack.GastoListAdapter;

import java.util.List;

public class GastoController {
    private Context context; //declaracion de objeto Context

    /**
     * Constructor de la clase
     * @param context
     */
    public GastoController(Context context){
        this.context = context;
    }

    /**
     * Muestra los gastos en la aplicacion
     * @param recyclerView
     * @param adapter
     */
    public void mostrarGastos(RecyclerView recyclerView, GastoListAdapter adapter){
        //llamamos al método obtenerGastos
        GastoDao gastoDao = new GastoDao(this.context).obtenerGastos(new GastoDao.CallBack() {
            @Override
            public void onSuccess(List<Gasto> gastos) {
                recyclerView.setAdapter(adapter);
                adapter.setGastos(gastos);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(recyclerView.getContext(), msg.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
