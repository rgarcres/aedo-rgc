package com.example.application.data;

public class Region extends AbstractEntity {
    private String nombre;
    private String provincia;

    //------------Constructor------------
    public Region(String nombre, String provincia){
        this.nombre = nombre;
        this.provincia = provincia;
    }

    //------------Getters y Setters------------
    //Nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Provincia
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
}
