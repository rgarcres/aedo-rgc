package com.example.application.views.home;

import com.example.application.views.utilidades.BotonesCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("home")
@Menu(order = 1, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        VerticalLayout mainLayout = new VerticalLayout();
        H2 h2 = new H2("Admin home");
        H3 h3 = new H3();
        Button crearCampanyaButton = new Button("Crear Campaña");
        Button misCampanyaButton = new Button("Mis Campañas");
        Button crearGrupoButton = new Button("Crear Grupo");
        Button misGruposButton = new Button("Mis Grupos");

        String nombreUsuario = (String) VaadinSession.getCurrent().getAttribute("nombreUsuario");

        configurarLayout(mainLayout);
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        h3.setWidth("max-content");
        h3.getStyle().set("color","#535581");
        h3.setText("Bienvenid@ " + nombreUsuario +"!!");

        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, crearCampanyaButton);
        crearCampanyaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(crearCampanyaButton, "crear-campanya");
        
        misCampanyaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(misCampanyaButton, "mis-campanyas");

        crearGrupoButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(crearGrupoButton, "crear-grupo");

        misGruposButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(misGruposButton, "mis-grupos");

        getContent().add(mainLayout);
        mainLayout.add(h2);
        mainLayout.add(h3);
        mainLayout.add(crearCampanyaButton);
        mainLayout.add(misCampanyaButton);
        mainLayout.add(crearGrupoButton);
        mainLayout.add(misGruposButton);
    }

    private void configurarLayout(VerticalLayout mainLayout){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        mainLayout.setWidthFull();
        getContent().setFlexGrow(1.0, mainLayout);
        mainLayout.setWidth("100%");
        mainLayout.getStyle().set("flex-grow", "1");
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);
    }
}
