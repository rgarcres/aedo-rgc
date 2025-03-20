package com.example.application.views;

import com.vaadin.flow.component.button.Button;

public class Personalizacion {
    
    public static void configurarBoton(Button boton, String ruta){
        boton.setWidth("min-content");
        boton.getStyle().set("cursor", "pointer");
        boton.addClickListener(e -> boton.getUI().ifPresent(
            ui -> ui.navigate(ruta)   
           ));
    }
}
