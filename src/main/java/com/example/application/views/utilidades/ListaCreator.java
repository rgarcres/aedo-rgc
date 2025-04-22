package com.example.application.views.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.application.data.Bloque;
import com.example.application.data.EGenero;
import com.example.application.data.ENivelEstudios;
import com.example.application.data.Pregunta;
import com.example.application.data.Region;
import com.example.application.data.ESituacionLaboral;
import com.example.application.data.Usuario;

public class ListaCreator {
    
    public static List<Bloque> crearListaBloques(){
        List<Bloque> bloques = new ArrayList<>();
        bloques.add(new Bloque("Bloque 1"));
        bloques.add(new Bloque("Bloque 2"));
        bloques.add(new Bloque("Bloque 3"));
        bloques.add(new Bloque("Bloque 4"));
        return bloques;
    }

    public static List<Region> crearListaRegiones(){
        List<Region> regiones = new ArrayList<>();
        regiones.add(new Region("Torremolinos", "Malaga"));
        regiones.add(new Region("Alora", "Malaga"));
        regiones.add(new Region("Nerja", "Malaga"));
        regiones.add(new Region("Velez Malaga", "Malaga"));
        return regiones;
    }

    public static List<Usuario> crearListaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("Ruben", "Garcia Crespo", EGenero.HOMBRE, ESituacionLaboral.OTRO, ENivelEstudios.BACHILLERATO, "550-1000", "Situacion Personal"));
        usuarios.add(new Usuario("Juan", "Cuesta", EGenero.HOMBRE, ESituacionLaboral.TIEMPO_TOTAL, ENivelEstudios.MASTER, "2300-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Pedro", "Martínez López", EGenero.HOMBRE, ESituacionLaboral.ASALARIADO, ENivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Carlos", "Sánchez Ruiz", EGenero.HOMBRE, ESituacionLaboral.TIEMPO_PARCIAL, ENivelEstudios.FORMACION_PROFESIONAL, "1800-2200", "Situacion Personal"));
        usuarios.add(new Usuario("Frank", "Sinatra", EGenero.HOMBRE, ESituacionLaboral.AUTONOMO, ENivelEstudios.DOCTORADO, "3000-3500", "Situacion Personal"));
        usuarios.add(new Usuario("Antonio", "Hernández Pérez", EGenero.HOMBRE, ESituacionLaboral.PARO, ENivelEstudios.ESO, "0", "Situacion Personal"));

        usuarios.add(new Usuario("Maria", "Gomez Perez", EGenero.MUJER, ESituacionLaboral.ASALARIADO, ENivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Lucia", "Sanchez Garcia", EGenero.MUJER, ESituacionLaboral.OTRO, ENivelEstudios.MASTER, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Patricia", "Sánchez Gómez", EGenero.MUJER, ESituacionLaboral.TIEMPO_TOTAL, ENivelEstudios.MASTER, "2500-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Laura", "Fernández Ruiz", EGenero.MUJER, ESituacionLaboral.TIEMPO_PARCIAL, ENivelEstudios.FORMACION_PROFESIONAL, "1700-2000", "Situacion Personal"));
        usuarios.add(new Usuario("Sandra", "García Pérez", EGenero.MUJER, ESituacionLaboral.AUTONOMO, ENivelEstudios.GRADO_UNIVERSITARIO, "2100-2700", "Situacion Personal"));
        usuarios.add(new Usuario("Bad", "Gyal", EGenero.MUJER, ESituacionLaboral.TIEMPO_TOTAL, ENivelEstudios.GRADO_UNIVERSITARIO, "1900-2400", "Situacion Personal"));

        usuarios.add(new Usuario("Alex", "Ruiz Fernández", EGenero.NO_BINARIO, ESituacionLaboral.ASALARIADO, ENivelEstudios.MASTER, "2200-2800", "Situacion Personal"));
        usuarios.add(new Usuario("Taylor", "Smith", EGenero.NO_BINARIO, ESituacionLaboral.TIEMPO_TOTAL, ENivelEstudios.BACHILLERATO, "1500-1800", "Situacion Personal"));
        usuarios.add(new Usuario("Robin", "Nico", EGenero.NO_BINARIO, ESituacionLaboral.PARO, ENivelEstudios.ESO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Jordan", "Williams", EGenero.NO_BINARIO, ESituacionLaboral.TIEMPO_PARCIAL, ENivelEstudios.FORMACION_PROFESIONAL, "1600-2000", "Situacion Personal"));
        usuarios.add(new Usuario("Sanji", "Vinsmoke", EGenero.NO_BINARIO, ESituacionLaboral.ASALARIADO, ENivelEstudios.DOCTORADO, "2500-3300", "Situacion Personal"));

        usuarios.add(new Usuario("Chris", "Evans", EGenero.OTRO, ESituacionLaboral.DESCONOCIDO, ENivelEstudios.DESCONOCIDO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Jamie", "Lee Curtis", EGenero.OTRO, ESituacionLaboral.AUTONOMO, ENivelEstudios.GRADO_UNIVERSITARIO, "2200-2800", "Situacion Personal"));
        usuarios.add(new Usuario("Sam", "Taylor-Johnson", EGenero.OTRO, ESituacionLaboral.TIEMPO_PARCIAL, ENivelEstudios.FORMACION_PROFESIONAL, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Alex", "Turner", EGenero.OTRO, ESituacionLaboral.TIEMPO_TOTAL, ENivelEstudios.MASTER, "2000-2600", "Situacion Personal"));
        usuarios.add(new Usuario("Jordan", "Peterson", EGenero.OTRO, ESituacionLaboral.OTRO, ENivelEstudios.BACHILLERATO, "1700-2300", "Situacion Personal"));

        usuarios.add(new Usuario("Anónimo", "Sin datos", EGenero.DESCONOCIDO, ESituacionLaboral.DESCONOCIDO, ENivelEstudios.DESCONOCIDO, "0", "Situacion Personal"));

        return usuarios;
    }


    public static List<Pregunta> crearListaPreguntas(){
        List<Pregunta> preguntas = new ArrayList<>();

        List<String> respuestas1 = Arrays.asList("Respuesta A", "Respuesta B", "Respuesta C", "Respuesta D");
        List<String> respuestas2 = Arrays.asList("Sí", "No", "Tal vez", "No sé");
        List<String> respuestas3 = Arrays.asList("1", "2", "3", "4");
        List<String> respuestas4 = Arrays.asList("Azul", "Rojo", "Verde", "Amarillo");
        List<String> respuestas5 = Arrays.asList("Gato", "Perro", "Conejo", "Hamster");
        List<String> respuestas6 = Arrays.asList("Invierno", "Verano", "Otoño", "Primavera");
        List<String> respuestas7 = Arrays.asList("Manzana", "Pera", "Plátano", "Sandía");
        List<String> respuestas8 = Arrays.asList("España", "Francia", "Italia", "Alemania");
        List<String> respuestas9 = Arrays.asList("Madrid", "Barcelona", "Sevilla", "Valencia");
        List<String> respuestas10 = Arrays.asList("Java", "Python", "C++", "JavaScript");
        List<String> respuestas11 = Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves");
        List<String> respuestas12 = Arrays.asList("Coche", "Moto", "Bicicleta", "Autobús");
        List<String> respuestas13 = Arrays.asList("Pizza", "Hamburguesa", "Sushi", "Tacos");
        List<String> respuestas14 = Arrays.asList("Agua", "Coca-Cola", "Zumo", "Té");
        List<String> respuestas15 = Arrays.asList("Facebook", "Instagram", "Twitter", "LinkedIn");
        List<String> respuestas16 = Arrays.asList("Frío", "Calor", "Templado", "Húmedo");
        List<String> respuestas17 = Arrays.asList("Rock", "Pop", "Reggaetón", "Clásica");
        List<String> respuestas18 = Arrays.asList("España", "México", "Argentina", "Chile");
        List<String> respuestas19 = Arrays.asList("Marte", "Júpiter", "Saturno", "Urano");
        List<String> respuestas20 = Arrays.asList("Perro", "Gato", "Pez", "Pájaro");

        preguntas.add(new Pregunta("¿Cuál es la respuesta correcta?", respuestas1, 1, 
        new Bloque("Bloque 1")));
        preguntas.add(new Pregunta("¿Estás seguro de tu respuesta?", respuestas2, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Cuál es el número más alto?", respuestas3, 4,
        new Bloque("Bloque 4")));
        preguntas.add(new Pregunta("¿Cuál es tu color favorito?", respuestas4, 3,
        new Bloque("Bloque 3")));
        preguntas.add(new Pregunta("¿Qué animal prefieres?", respuestas5, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Cuál es tu estación favorita?", respuestas6, 1, 
        new Bloque("Bloque 1")));
        preguntas.add(new Pregunta("¿Qué fruta te gusta más?", respuestas7, 4,
        new Bloque("Bloque 4")));
        preguntas.add(new Pregunta("¿Qué país visitarías primero?", respuestas8, 3,
        new Bloque("Bloque 3")));
        preguntas.add(new Pregunta("¿En qué ciudad te gustaría vivir?", respuestas9, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Cuál es tu lenguaje de programación favorito?", respuestas10, 1,
        new Bloque("Bloque 1")));
        preguntas.add(new Pregunta("¿Cuál es tu día favorito de la semana?", respuestas11, 3,
        new Bloque("Bloque 3")));
        preguntas.add(new Pregunta("¿Cuál es tu medio de transporte preferido?", respuestas12, 4,
        new Bloque("Bloque 4")));
        preguntas.add(new Pregunta("¿Qué comida prefieres?", respuestas13, 1, 
        new Bloque("Bloque 1")));
        preguntas.add(new Pregunta("¿Qué bebida prefieres?", respuestas14, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Qué red social usas más?", respuestas15, 3,
        new Bloque("Bloque 3")));
        preguntas.add(new Pregunta("¿Cuál es tu clima ideal?", respuestas16, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Qué género musical te gusta más?", respuestas17, 1,
        new Bloque("Bloque 1")));
        preguntas.add(new Pregunta("¿En qué país te gustaría vivir?", respuestas18, 4,
        new Bloque("Bloque 4")));
        preguntas.add(new Pregunta("¿Cuál es tu planeta favorito?", respuestas19, 2,
        new Bloque("Bloque 2")));
        preguntas.add(new Pregunta("¿Qué mascota prefieres?", respuestas20, 1,
        new Bloque("Bloque 1")));
        return preguntas;
    }
}
