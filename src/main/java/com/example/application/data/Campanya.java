package com.example.application.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Campanya extends AbstractEntity {

    private String nombre;
    private String objetivos;
    private String demografia;
    private LocalDate inicio;
    private LocalDate fin;
    private Region region;
    private Bloque bloque;
    private List<Grupo> grupos;
    private List<Pregunta> preguntas;

    //------------Constructor------------
    public Campanya(Long ID, String nombre, String objetivos, String demografia, LocalDate inicio, LocalDate fin, Region region, Bloque bloque) {
        this.setId(ID);
        this.nombre = nombre;
        this.objetivos = objetivos;
        this.demografia = demografia;
        this.inicio = inicio;
        this.fin = fin;
        this.region = region;
        grupos = new ArrayList<>();
        preguntas = new ArrayList<>();
    }

    //------------Getters y Setters------------
    //Nombre
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}

    //Objetivos
    public String getObjetivos() { return objetivos; }
    public void setObjetivos(String objetivos) { this.objetivos = objetivos; }
    
    //Demografia
    public String getDemografia() { return demografia; }
    public void setDemografia(String demografia) { this.demografia = demografia; }

    //Fecha inicio
    public LocalDate getInicio() { return inicio; }
    public void setInicio(LocalDate inicio) { this.inicio = inicio; }

    //Fecha fin
    public LocalDate getFin() { return fin; }
    public void setFin(LocalDate fin) { this.fin = fin; }

    //Region
    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }

    //Bloque
    public Bloque getBloque() { return bloque; }
    public void setBloque(Bloque bloque) { this.bloque = bloque; }

    //Lista de grupos
    public List<Grupo> getGrupos() { return grupos; }
    public void setGrupos(List<Grupo> grupos) { this.grupos = grupos;  }

    //Lista de preguntas
    public List<Pregunta> getPreguntas() { return preguntas; }
    public void setPreguntas(List<Pregunta> preguntas) { this.preguntas = preguntas; }
}