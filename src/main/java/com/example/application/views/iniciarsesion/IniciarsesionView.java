package com.example.application.views.iniciarsesion;

import com.example.application.data.Campanya;
import com.example.application.data.Grupo;
import com.example.application.views.utilidades.BotonesCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Iniciar sesion")
@Route("iniciar-sesion")
@Menu(order = 0, icon = LineAwesomeIconUrl.USER)
public class IniciarsesionView extends Composite<VerticalLayout> {

    //Inicializar las listas que simulan la persistencia en esta demo
    private final List<Campanya> listaCamps = new ArrayList<>();
    private final List<Grupo> listaGrupos = new ArrayList<>();
    
    //---------Variables locales---------
    String nombreUsuario;

    public IniciarsesionView() {
        //---------Crear componentes---------
        VerticalLayout mainLayout = new VerticalLayout();
        H2 titulo = new H2("Iniciar sesión");
        H3 errorMsg = new H3("Introduzca un nombre de usuario para entrar");
        TextField nombreUsuarioField = new TextField("Nombre");
        Button iniciarSesionButton = new Button("Iniciar sesión");

        //Guardar los atributos en la sesión actual
        VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
        VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);

        //---------Configurar Layout---------
        configurarLayaout(mainLayout);
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, titulo);
        titulo.setWidth("max-content");
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, nombreUsuarioField);
        nombreUsuarioField.setWidth("min-content");
        errorMsg.getStyle().set("color", "#ff4e4e");
        mainLayout.add(titulo);

        //---------Botones---------
        BotonesCreator.configurarBoton(iniciarSesionButton, "");

        /*
         * ---------Iniciar Sesion---------
         * Se inicia sesión si se ha introducido un nombre de usuario
         * Si no se ha introducido se lanza un mensaje de error 
         */
        //Iniciar sesión pulsando el botón
        iniciarSesionButton.addClickListener(e -> {
            iniciarSesion(nombreUsuarioField, mainLayout, errorMsg); }
        );
        //Iniciar sesión pulsando la tecla Enter
        nombreUsuarioField.addKeyPressListener(Key.ENTER, e -> {
            iniciarSesion(nombreUsuarioField, mainLayout, errorMsg);
        });

        //Añadir componenetes al layout
        mainLayout.add(nombreUsuarioField);
        mainLayout.add(iniciarSesionButton);
    }

    /*
     * Método que realiza las comprobaciones necesarias para iniciar sesión
     */
    private void iniciarSesion(TextField field, VerticalLayout layout, H3 errorMsg){
        nombreUsuario = field.getValue();
        layout.remove(errorMsg);
       
        if(!nombreUsuario.isEmpty()){
            VaadinSession.getCurrent().setAttribute("nombreUsuario", nombreUsuario);
            getUI().ifPresent(ui -> ui.navigate(""));
        } else {
            layout.add(errorMsg);
        }   
    }

    private void configurarLayaout(VerticalLayout mainLayout){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        mainLayout.setWidthFull();
        getContent().setFlexGrow(1.0, mainLayout);
        mainLayout.setWidth("100%");
        mainLayout.getStyle().set("flex-grow", "1");
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);
        getContent().add(mainLayout);
    }
}
