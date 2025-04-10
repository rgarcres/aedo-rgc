package com.example.application.data;

public class Bloque extends AbstractEntity{
    private String nombre;
    private String descripcion;

    //------------Constructor------------
    public Bloque(String nombre) {
        this.nombre = nombre;
    }

    //------------Getters y Setters------------
    //Nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Descripcion
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    //------------Métodos------------
    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;
        Bloque bloque = (Bloque) o;
        return this.nombre == bloque.getNombre();   //&& this.descripcion == bloque.getDescripcion();
    }

    @Override
    public int hashCode(){
        return nombre.hashCode() + descripcion.hashCode();
    }

    @Override
    public String toString(){
        return nombre;
    }
}
