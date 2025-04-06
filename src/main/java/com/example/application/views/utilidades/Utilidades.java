package com.example.application.views.utilidades;

import java.text.Normalizer;

public class Utilidades {

    /*
     * A la cadena que se pasa por parámetro:
     *      - Se le quitan las tildes
     *      - Se le quitan los espcios en blanco
     *      - Se dejan todos los caracteres en minúscula
     */
    private static String limpiarCadena(String s){
        //Quitar tildes
        String sinTilde = Normalizer.normalize(s, Normalizer.Form.NFD);
        sinTilde = sinTilde.replaceAll("\\p{M}","");

        //Quitar espacios
        String sinEspacios = sinTilde.trim();
        sinEspacios = sinEspacios.replaceAll("\\s+", "");

        //Devolver con todo en minúscula
        return sinEspacios.toLowerCase();
    }

    /*
     * Comprueba que una palabra pasada como filtro está contenida en el valor completo
     */
    public static boolean buscarCoincidencias(String valorCompleto, String filtro){
        //Si alguno está vacío se devuelve false
        if(filtro == null || valorCompleto == null){
            return false;
        }
        
        //Se limpian las cadenas
        String completoLimpio = limpiarCadena(valorCompleto);
        String filtroLimpio = limpiarCadena(filtro);

        //Se comprueba que el filtro esté contenido dentro de la cadena completa
        if(completoLimpio.contains(filtroLimpio)){
            return true;
        }

        //En otro caso devuelve false
        return false;
    }

    /*
     * Comprueba si el Stirng que se pasa por parámetro es un número
     * Si es un número devuelve TRUE
     * Si no es un número devuelve FALSE
     */
    public static boolean esNumero(String s){
        if(s == null || s.isBlank()){
            return false;
        }

        try{
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
