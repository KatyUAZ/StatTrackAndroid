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

import java.util.ArrayList;
import java.util.List;

public class GastoDao {
    private List<Gasto> gastos;
    private Context context;
    private RequestQueue mRequestQueue;

    public GastoDao(Context context){
        this.context = context;
        gastos = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this.context);
    }

    public void comprobarGasto(final int idcat, final GastoCallback callback) {
        // URL del endpoint
        String url = "http://192.168.1.101:8080/comprobargasto/" +  idcat;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int resultado = Integer.parseInt(response);
                        callback.onComprobarGastoResult(resultado);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error si es necesario
                    }
                });

        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest);
    }

    public GastoDao eliminarGasto(final Gasto gasto){
        // URL del endpoint
        String url = "http://192.168.1.101:8080/eliminar-gasto/" +  gasto.getGastoId();

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                });

        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest);
        return null;
    }

    /**
     * Permite añadir un nuevo gasto
     * @param gasto
     * @return
     */
    public GastoDao enviarGasto(final Gasto gasto) {
        // URL del endpoint
        String url = "http://192.168.1.101:8080/guardargasto/" + gasto.getGastoDescripcion() + "/" + gasto.getCategoria() + "/" + gasto.getGastoCantidad();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                    }
                });

        // Agregar la solicitud a la cola de Volley
        Volley.newRequestQueue(context).add(stringRequest);
        return null;
    }

    /**
     * Permite obtener los gastos
     * @param onCallBack
     * @return
     */
    public GastoDao obtenerGastos(final CallBack onCallBack){
        //URL del endpoint
        String url = "http:192.168.1.101:8080/gastos";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Gasto gasto = new Gasto();
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    Log.d("JSON Response", response);

                    // Recorremos el array JSON y almacenamos los datos en una lista
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray itemArray = jsonArray.getJSONArray(i);

                        int gastoId = itemArray.getInt(0);
                        String gastoDescripcion = itemArray.getString(1);
                        double gastoCantidad = itemArray.getDouble(2);
                        String gastoFecha = itemArray.getString(6);
                        int catId = itemArray.getInt(3);

                        gastos.add(new Gasto(gastoId, gastoDescripcion, gastoCantidad, gastoFecha, catId));

                        // Hacer algo con las variables
                        System.out.println(gastoId + " " + gastoDescripcion + " " + gastoCantidad + " " + gastoFecha + " " + catId);
                    }
                    onCallBack.onSuccess(gastos);
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

    public interface CallBack{
        void onSuccess(List<Gasto> gastos);
        void onFail(String msg);
    }


    public interface GastoCallback {
        void onComprobarGastoResult(int resultado);
    }


}
