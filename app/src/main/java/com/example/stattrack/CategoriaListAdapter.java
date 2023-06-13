package com.example.stattrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stattrack.models.Categoria;
import com.example.stattrack.models.Gasto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoriaListAdapter extends RecyclerView.Adapter<CategoriaListAdapter.CategoriaViewHolder>{

    private final LayoutInflater mInflater; //mInflater permite mostrar la lista de elementos en la vista
    private List<Categoria> listaCategorias; //obtiene la lista de elementos de la base de datos
    private Context context;  //almacena el contexto de la actividad

    //constructor de la clase
    public CategoriaListAdapter(Context context){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * recibe una lista de categorias, los asigna a la variable
     * listaCategoria y actualiza la vista
     * @param categorias
     */
    public void setCategorias(List<Categoria> categorias){
        listaCategorias = categorias;
        notifyDataSetChanged();
    }

    /**
     * ListAdapter de Categoria
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public CategoriaListAdapter.CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_cat, parent, false);
        return new CategoriaListAdapter.CategoriaViewHolder(itemView);
    }

    /**
     * Permite obtener un elemento en base a la posicion
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CategoriaListAdapter.CategoriaViewHolder holder, int position) {
        //obtiene el elemento actual
        Categoria categoria = listaCategorias.get(position);


    }

    /**
     * Devuelve la lista de categorias si no esta vacia
     * @return
     */
    @Override
    public int getItemCount() {
        if(listaCategorias!=null){
            return listaCategorias.size();
        }else {
            return 0;
        }
    }

    /**
     * Declaracion de la clase interna ViewHolder
     */
    class CategoriaViewHolder extends RecyclerView.ViewHolder{
        private final TextView categoriaItemView;

        public CategoriaViewHolder(@NotNull View itemView){
            super(itemView);
            categoriaItemView = itemView.findViewById(R.id.gastoFecha);
        }
    }

}
