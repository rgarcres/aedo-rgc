package com.example.application.data;

public class Pregunta {
    private String enunciado;
    private String[] respuestas;
    private int tipo;
    
    public Pregunta(String enunciado, String[] respuestas, int tipo) {
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

    public String[] getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
