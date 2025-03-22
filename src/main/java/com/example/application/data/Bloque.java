package com.example.application.data;

public class Bloque extends AbstractEntity{
    private String nombre;
    private String descripcion;

    //CONSTRUCTOR
    public Bloque(String nombre) {
        this.nombre = nombre;
    }

    //GETTERS & SETTERS
    //nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //descripcion
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
