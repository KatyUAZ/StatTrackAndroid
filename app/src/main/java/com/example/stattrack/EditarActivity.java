// Clase EditarActivity
package com.example.stattrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stattrack.controllers.CategoriaController;
import com.example.stattrack.models.Gasto;

import java.util.List;

public class EditarActivity extends AppCompatActivity {

    private CategoriaController categoriaController; //declaracion de CategoriaController
    private EditText editText;//declaracion de objeto EditText
    private EditText number; //declaracin de objeto EditText

    //muestra informacion de categorias
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent intent = getIntent();

        editText = (EditText) findViewById(R.id.editTextText);
        number = (EditText) findViewById(R.id.editTextNumber);

        categoriaController = new CategoriaController(this, this);
        categoriaController.mostrarCategorias();
    }

    /**
     * Agrega nuevo gasto
     * @param view
     */
    public void agregarGasto(View view) {
        Spinner spinner = findViewById(R.id.spinner3);
        int selectedItemIndex = spinner.getSelectedItemPosition();

        //establece el valor de los elementos
        Gasto gasto = new Gasto();

        String descrip = editText.getText().toString();

        String cantidadTexto = number.getText().toString();
        int cantidad = 0;

        try {
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
        }

        if((cantidad != 0) && (descrip != "")) {

            gasto.setGastoCantidad(cantidad);

            gasto.setGastoDescripcion(descrip);

            gasto.setCategoria(selectedItemIndex + 1);

            categoriaController.subirGasto(gasto);
            categoriaController.comprobarGasto(gasto.getCategoria());

            System.out.println("Cantidad: " + cantidad + ", Descripción: " + descrip + ", Categoría: " + selectedItemIndex);


            Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
        }

    }
}

