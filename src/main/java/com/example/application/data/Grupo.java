package com.example.application.data;

import java.util.ArrayList;
import java.util.List;

public class Grupo extends AbstractEntity{
    private String nombre;
    private String descripcion;
    private List<Usuario> usuarios;

    //------------Constructor------------
    public Grupo(Long ID, String nombre){
        this.setId(ID);
        this.nombre = nombre;
        usuarios = new ArrayList<>();
    }
    public Grupo(Long ID, String nombre, String descripcion){
        this.setId(ID);
        this.nombre = nombre;
        this.descripcion = descripcion;
        usuarios = new ArrayList<>();
    }

    //------------Getters y Setters------------
    //Nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Descripcion
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    //Usuarios
    public List<Usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

    //------------Métodos------------
    @Override
    public String toString(){
        return nombre;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        //Pertenecen a la misma clase
        if(o.getClass() != getClass()) return false;
        Grupo g = (Grupo) o;

        //Tienen el mismo nombre
        if(!g.getNombre().equals(nombre)) return false;

        //Tienen exactamente los mismos usuarios
        return g.getUsuarios().size() == usuarios.size() && g.getUsuarios().containsAll(usuarios);
    }

    @Override
    public int hashCode(){
        return nombre.hashCode() + descripcion.hashCode() + usuarios.hashCode();
    }
}
