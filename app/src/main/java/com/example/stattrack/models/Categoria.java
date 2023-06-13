package com.example.stattrack.models;

public class Categoria {
    private int catId;// declaracion del id de categoria
    private String catNombre;//declaracion del nombre de la categoria
    private double catPresupuesto;//declaracion del presupuesto para la categoria

    /**
     * Constructor de la clase Categoria
     * @param catId
     * @param catNombre
     * @param catPresupuesto
     */
    public Categoria(int catId, String catNombre, double catPresupuesto){
        this.catId = catId;
        this.catNombre = catNombre;
        this.catPresupuesto = catPresupuesto;
    }


    /**
     * Constructor vacio
     */
    public Categoria(){

    }

    /**
     * Retorna el id de la categoria
     * @return
     */
    public int getCatId(){
        return catId;
    }

    /**
     * Establece el id de la categoria
     * @param catId
     */
    public void setCatId(int catId){
        this.catId = catId;
    }

    /**
     * Retorna el nombre de la categoria
     * @return
     */
    public String getCatNombre(){
        return catNombre;
    }

    /**
     * Establece el nombre de la categoria
     * @param catNombre
     */
    public void setCatNombre(String catNombre){
        this.catNombre = catNombre;
    }

    /**
     * Retorna el presupuesto de la categoria
     * @return
     */
    public double getCatPresupuesto(){
        return catPresupuesto;
    }

    /**
     * Establece el presupuesto de la categoria
     * @param catPresupuesto
     */
    public void setCatPresupuesto(double catPresupuesto) {
        this.catPresupuesto = catPresupuesto;
    }
}
