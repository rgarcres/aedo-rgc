package com.example.application.data;

public class Usuario extends AbstractEntity{
    private String nombre;
    private String apellido;
    private EGenero genero;
    private ESituacionLaboral situacionLaboral;
    private ENivelEstudios nivelEstudios;
    private String rangoSalarial;
    private String situacionPersonal;

    //------------Getters y Setters------------
    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario(String nombre, String apellido, EGenero genero, ESituacionLaboral situacionLabroal, ENivelEstudios nivelEstudios, String rangoSalarial, String situacionPersonal){
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.situacionLaboral = situacionLabroal;
        this.situacionPersonal = situacionPersonal;
        this.nivelEstudios = nivelEstudios;
        this.rangoSalarial = rangoSalarial;
        this.situacionPersonal = situacionPersonal;
    }

    //------------Getters y Setters------------
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public EGenero getGenero() { return genero; }
    public void setGenero(EGenero genero) { this.genero = genero; }

    public ENivelEstudios getNivelEstudios() { return nivelEstudios; }
    public void setNivelEstudios(ENivelEstudios nivelEstudios) { this.nivelEstudios = nivelEstudios; }

    public String getRangoSalarial() { return rangoSalarial; }
    public void setRangoSalarial(String rangoSalarial) { this.rangoSalarial = rangoSalarial; }
    
    public ESituacionLaboral getSituacionLaboral() { return situacionLaboral; }
    public void setSituacionLaboral(ESituacionLaboral situacionLaboral) { this.situacionLaboral = situacionLaboral; }

    public String getSituacionPersonal() { return situacionPersonal; }
    public void setSituacionPersonal(String situacionPersonal) { this.situacionPersonal = situacionPersonal; }

    //------------Métodos------------
    @Override
    public String toString(){
        return this.nombre + " " + this.apellido;
    }
}
