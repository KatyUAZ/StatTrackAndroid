package com.example.stattrack.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    //declaracion de variables
    private List<Categoria> categorias;
    private Context context;
    private RequestQueue mRequestQueue;

    /**
     * Constrcutor con un parametro
     * @param context
     */
    public CategoriaDao(Context context){
        this.context = context;
        categorias = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this.context);
    }

    /**
     * Permite devolver la lista de categorias
     * @param onCallBack
     * @return
     */
    public CategoriaDao obtenerCategorias(final CategoriaDao.CallBack onCallBack){
        //URL del endpoint
        String url = "http:192.168.1.101:8080/categoria";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {

                Categoria categoria = new Categoria();


                try{
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("JSON Response", response);

                    // Recorremos el array JSON y almacenamos los datos en una lista
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //obtenemos los datos con base a las claves del JSON
                        int catId = jsonObject.getInt("catId");
                        String catNombre = jsonObject.getString("catNombre");
                        double catPresupuesto = jsonObject.getDouble("catPresupuesto");

                        System.out.println("catId: " + catId + ", catNombre: " + catNombre + ", catPresupuesto: " + catPresupuesto);

                        //agregamos los datos a la lista
                        categorias.add(new Categoria(catId, catNombre, catPresupuesto));
                    }//fin del for
                    onCallBack.onSuccess(categorias);
                } catch (JSONException e) {
                    e.printStackTrace();
                    onCallBack.onFail(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(stringRequest);
        return null;
    }


    /**
     * Interface CallBack
     */
    public interface CallBack{
        void onSuccess(List<Categoria> categorias);
        void onFail(String msg);
    }
}
