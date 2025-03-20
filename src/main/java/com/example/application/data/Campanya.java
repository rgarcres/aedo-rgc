package com.example.application.data;

import java.sql.Date;

public class Campanya extends AbstractEntity {

    private String nombre;
    private String objetivos;
    private String demografia;
    private Date inicio;
    private Date fin;

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

    public Date getInicio() {
        return inicio;
    }
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFin() {
        return fin;
    }
    public void setFin(Date fin) {
        this.fin = fin;
    }
}

