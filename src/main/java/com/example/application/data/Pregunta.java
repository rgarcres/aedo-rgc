package com.example.application.data;

import java.util.List;

public class Pregunta extends AbstractEntity{
    private String enunciado;
    private List<String> respuestas;
    private int tipo;
    private Bloque bloque;

    //------------Constructor------------
    public Pregunta(String enunciado, List<String> respuestas, int tipo, Bloque bloque) {
        this.enunciado = enunciado;
        this.respuestas = respuestas;
        this.tipo = tipo;
        this.bloque = bloque;
    }

    //------------Getters y Setters------------
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

    //------------Métodos------------
    @Override
    public String toString(){
        return enunciado;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != getClass()) return false;

        Pregunta pregunta = (Pregunta) o;

        return pregunta.getBloque() == bloque && pregunta.getEnunciado() == enunciado
                && pregunta.getTipo() == tipo;
    }
}
