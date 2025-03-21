package com.example.application.data;

public class Region extends AbstractEntity {
    private String nombre;
    private String provincia;

    public Region(String nombre, String provincia){
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
