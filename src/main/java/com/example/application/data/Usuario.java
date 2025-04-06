package com.example.application.data;

public class Usuario extends AbstractEntity{
    private String nombre;
    private String apellido;
    private Genero genero;
    private SituacionLaboral situacionLaboral;
    private NivelEstudios nivelEstudios;
    private String rangoSalarial;
    private String situacionPersonal;

    //CONSTRUCTORES
    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario(String nombre, String apellido, Genero genero, SituacionLaboral situacionLabroal, NivelEstudios nivelEstudios, String rangoSalarial, String situacionPersonal){
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.situacionLaboral = situacionLabroal;
        this.situacionPersonal = situacionPersonal;
        this.nivelEstudios = nivelEstudios;
        this.rangoSalarial = rangoSalarial;
        this.situacionPersonal = situacionPersonal;
    }

    //GETTERS Y SETTERS
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }

    public NivelEstudios getNivelEstudios() { return nivelEstudios; }
    public void setNivelEstudios(NivelEstudios nivelEstudios) { this.nivelEstudios = nivelEstudios; }

    public String getRangoSalarial() { return rangoSalarial; }
    public void setRangoSalarial(String rangoSalarial) { this.rangoSalarial = rangoSalarial; }
    
    public SituacionLaboral getSituacionLaboral() { return situacionLaboral; }
    public void setSituacionLaboral(SituacionLaboral situacionLaboral) { this.situacionLaboral = situacionLaboral; }

    public String getSituacionPersonal() { return situacionPersonal; }
    public void setSituacionPersonal(String situacionPersonal) { this.situacionPersonal = situacionPersonal; }

    public String toString(){
        return this.nombre + " " + this.apellido;
    }
}
