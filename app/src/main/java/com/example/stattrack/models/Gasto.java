package com.example.stattrack.models;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Gasto {
    private int gastoId;// declaracion del id de gasto
    private String gastoDescripcion;//declaracion de la descripcion del gasto
    private double gastoCantidad;//declaracion de la cantidad del gasto
    private String gastoFecha;//fecha en que fue añadido el gasto
    private int idCategoria;//id de la categoria

    /**
     * Constructor con 5 parametros
     * @param gastoId
     * @param gastoDescripcion
     * @param gastoCantidad
     * @param gastoFecha
     * @param idCategoria
     */
    public Gasto (@NotNull int gastoId, String gastoDescripcion, double gastoCantidad, String gastoFecha, int idCategoria){
        this.gastoId = gastoId;
        this.gastoDescripcion = gastoDescripcion;
        this.gastoCantidad = gastoCantidad;
        this.gastoFecha = gastoFecha;
        this.idCategoria = idCategoria;
    }

    /**
     * Constructor vacio
     */
    public Gasto(){

    }

    /**
     * Devuelve el valor del id del gasto
     * @return gastoId
     */
    public int getGastoId() {
        return gastoId;
    }

    /**
     * Establece el id del gasto
     * @param gastoId
     */
    public void setGastoId(int gastoId) {
        this.gastoId = gastoId;
    }

    /**
     * Devuelve la descripcion del gasto
     * @return gastoDescripcion
     */
    public String getGastoDescripcion() {
        return gastoDescripcion;
    }

    /**
     * Establece la descripcion del gasto
     * @param gastoDescripcion
     */
    public void setGastoDescripcion(String gastoDescripcion) {
        this.gastoDescripcion = gastoDescripcion;
    }

    /**
     * Devuelve la cantidad del gasto
     * @return gastoCantidad
     */
    public double getGastoCantidad() {
        return gastoCantidad;
    }

    /**
     * Establece la cantidad del gasto
     * @param gastoCantidad
     */
    public void setGastoCantidad(double gastoCantidad) {
        this.gastoCantidad = gastoCantidad;
    }

    /**
     * Devuelve la fecha del gasto
     * @return gastoFecha
     */
    public String getGastoFecha() {
        return gastoFecha;
    }

    /**
     * Establece la fecha del gasto
     * @param gastoFecha
     */
    public void setGastoFecha(String gastoFecha) {
        this.gastoFecha = gastoFecha;
    }

    /**
     * Devuelve la categoria a la que pertence el gasto
     * @return categoria
     */
    public int getCategoria() {
        return idCategoria;
    }

    /**
     * Establece la categoria a la que pertenece el gasto
     * @param categoria
     */
    public void setCategoria(int categoria) {
        this.idCategoria = categoria;
    }
}
