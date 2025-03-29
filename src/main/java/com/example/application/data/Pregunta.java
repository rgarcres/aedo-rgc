package com.example.application.data;

import java.util.List;

public class Pregunta {
    private String enunciado;
    private List<String> respuestas;
    private int tipo;
    private Bloque bloque;

    //CONSTRUCTOR
    public Pregunta(String enunciado, List<String> respuestas, int tipo, Bloque bloque) {
        this.enunciado = enunciado;
        this.respuestas = respuestas;
        this.tipo = tipo;
        this.bloque = bloque;
    }

    //GETTERS Y SETTERS
    //Enunciado
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    //Respuestas
    public List<String> getRespuestas() { return respuestas; }
    public void setRespuestas(List<String> respuestas) { this.respuestas = respuestas; }
    //Tipo
    public int getTipo() { return tipo; }
    public void setTipo(int tipo) { this.tipo = tipo; }
    //Bloque 
    public Bloque getBloque() { return bloque; }
    public void setBloque(Bloque bloque) { this.bloque = bloque; }

    //Una pregunta es representada como String por su enunciado
    public String toString(){
        return enunciado;
    }
}
