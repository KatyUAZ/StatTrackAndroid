package com.example.stattrack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stattrack.models.Gasto;
import com.example.stattrack.models.GastoDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GastoListAdapter extends RecyclerView.Adapter<GastoListAdapter.GastoViewHolder> {
    private final LayoutInflater mInflater; //mInflater permite mostrar la lista de elementos en la vista
    private List<Gasto> listaGastos; //obtiene la lista de elementos de la base de datos
    private Context context;  //almacena el contexto de la actividad

    private int position;

    //constructor de la clase
    public GastoListAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * recibe una lista de gastos, los asigna a la variable
     * listaGastos y actualiza la vista
     * @param gastos
     */
    public void setGastos(List<Gasto> gastos){
        listaGastos = gastos;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder de Gasto
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.gastocard_recyclerviewitem, parent, false);
        return new GastoViewHolder(itemView);
    }


    /**
     * View Holder para mostrar el gasto
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        //obtiene el elemento actual
        Gasto gasto = listaGastos.get(position);


        String gastoDesctext = gasto.getGastoDescripcion();
        String gastoCate = String.valueOf(gasto.getGastoCantidad());
        String gastoFecha = gasto.getGastoFecha().substring(0, 10);;
        String gastoCatID = String.valueOf(gasto.getCategoria());

        holder.gastoDesc.setText(gastoDesctext);
        holder.gastoCate.setText("$" + gastoCate);
        holder.gastoFecha.setText(gastoFecha);
        holder.catID.setText(gastoCatID);

        holder.cartaCompleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("¿Deseas Eliminar este gasto?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                GastoDao gastoDao = new GastoDao(context).eliminarGasto(gasto);
                                listaGastos.remove( position );
                                notifyDataSetChanged();
                                Toast.makeText(context, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Acción al seleccionar "Cancelar"
                                boolean aceptar = false;
                                // Lógica adicional aquí

                            }
                        });

                // Mostrar el cuadro de diálogo
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    /**
     * Muestra la lista de gastos
     * @return
     */
    @Override
    public int getItemCount() {
        if(listaGastos!=null){
            return listaGastos.size();
        }else {
            return 0;
        }
    }

    //declaracion de la clase interna ViewHolder
    class GastoViewHolder extends RecyclerView.ViewHolder{
        private final TextView gastoDesc;
        private final TextView gastoCate;
        private final TextView gastoFecha;
        private final TextView catID;
        private final CardView cartaCompleta;

        public GastoViewHolder(@NotNull View itemView){
            super(itemView);
            gastoDesc = itemView.findViewById(R.id.gastoDescrip);
            gastoCate = itemView.findViewById(R.id.gatoCategoria);
            gastoFecha = itemView.findViewById(R.id.gastoFecha);
            catID = itemView.findViewById(R.id.gatoID);
            cartaCompleta =  itemView.findViewById(R.id.cartaCompleta);

        }
    }
}
