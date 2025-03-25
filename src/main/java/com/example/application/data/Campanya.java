package com.example.application.data;

import java.time.LocalDate;
import java.util.List;

public class Campanya extends AbstractEntity {

    private String nombre;
    private String objetivos;
    private String demografia;
    private LocalDate inicio;
    private LocalDate fin;
    private List<Usuario> usuarios;
    private List<Pregunta> preguntas;

    public Campanya(String nombre, String objetivos, String demografia, LocalDate inicio, LocalDate fin) {
        this.nombre = nombre;
        this.objetivos = objetivos;
        this.demografia = demografia;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getDemografia() {
        return demografia;
    }

    public void setDemografia(String demografia) {
        this.demografia = demografia;
    }

    public LocalDate getInicio() {
        return inicio;
    }
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }
    public LocalDate getFin() {
        return fin;
    }
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}

