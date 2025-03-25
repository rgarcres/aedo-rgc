package com.example.application.data;

import java.util.List;

public class Pregunta {
    private String enunciado;
    private List<String> respuestas;
    private int tipo;
    
    public Pregunta(String enunciado, List<String> respuestas, int tipo) {
        this.enunciado = enunciado;
        this.respuestas = respuestas;
        this.tipo = tipo;
    }

    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return enunciado;
    }
}
