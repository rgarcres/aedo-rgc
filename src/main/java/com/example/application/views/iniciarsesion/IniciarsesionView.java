package com.example.application.views.iniciarsesion;

import com.example.application.data.Campanya;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

    private final List<Campanya> listaCamps = new ArrayList<>();

    public IniciarsesionView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H2 h2 = new H2("Iniciar sesión");
        TextField nombreUsuarioField = new TextField();
        Button iniciarSesionButton = new Button("Iniciar sesión");
        VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        nombreUsuarioField.setLabel("Nombre");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, nombreUsuarioField);
        nombreUsuarioField.setWidth("min-content");
        iniciarSesionButton.setWidth("min-content");
        iniciarSesionButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        iniciarSesionButton.getStyle().set("cursor", "pointer");
        H3 h3 = new H3("Introduzca un nombre de usuario para entrar");
        h3.getStyle().set("color", "#ff4e4e");

        getContent().add(layoutColumn2);
        layoutColumn2.add(h2);

        iniciarSesionButton.addClickListener(
            e -> {
                String nombreUsuario = nombreUsuarioField.getValue();
                layoutColumn2.remove(h3);
                if(!nombreUsuario.isEmpty()){
                    VaadinSession.getCurrent().setAttribute("nombreUsuario", nombreUsuario);
                    getUI().ifPresent(ui -> ui.navigate(""));
                } else {
                    layoutColumn2.add(h3);
                }     
            }
        );
        nombreUsuarioField.addKeyPressListener(Key.ENTER, e -> {
            String nombreUsuario = nombreUsuarioField.getValue();
            layoutColumn2.remove(h3);
            if(!nombreUsuario.isEmpty()){
                VaadinSession.getCurrent().setAttribute("nombreUsuario", nombreUsuario);
                getUI().ifPresent(ui -> ui.navigate(""));
            } else {
                layoutColumn2.add(h3);
            }     
        });

        layoutColumn2.add(nombreUsuarioField);
        layoutColumn2.add(iniciarSesionButton);
    }
}
