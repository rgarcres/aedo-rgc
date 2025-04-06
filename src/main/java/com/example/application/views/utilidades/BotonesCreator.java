package com.example.application.views.utilidades;

import com.vaadin.flow.component.button.Button;

public class BotonesCreator {
    
    public static void configurarBoton(Button boton){
        boton.setWidth("min-content");
        boton.getStyle().set("cursor", "pointer");
    }

    public static void configurarBoton(Button boton, String ruta){
        boton.setWidth("min-content");
        boton.getStyle().set("cursor", "pointer");
        boton.addClickListener(e -> boton.getUI().ifPresent(
            ui -> ui.navigate(ruta)   
           ));
    }
}
