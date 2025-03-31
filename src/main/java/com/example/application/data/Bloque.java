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

    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;
        Bloque bloque = (Bloque) o;
        return this.nombre == bloque.getNombre();
    }

    @Override
    public int hashCode(){
        return nombre.hashCode();
    }

    @Override
    public String toString(){
        return nombre;
    }
}
