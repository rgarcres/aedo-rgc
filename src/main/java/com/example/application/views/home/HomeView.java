package com.example.application.views.home;

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
@Route("")
@Menu(order = 1, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H2 h2 = new H2("Admin home");
        H3 h3 = new H3();
        Button crearCampanyaButton = new Button("Crear campaña");
        Button misCampanyaButton = new Button("Ver campañas");

        String nombreUsuario = (String) VaadinSession.getCurrent().getAttribute("nombreUsuario");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        h3.setWidth("max-content");
        h3.getStyle().set("color","#535581");
        h3.setText("Bienvenido " + nombreUsuario +"!!");

        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, crearCampanyaButton);
        crearCampanyaButton.setWidth("min-content");
        crearCampanyaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        crearCampanyaButton.getStyle().set("cursor", "pointer");
        crearCampanyaButton.addClickListener(e -> crearCampanyaButton.getUI().ifPresent(
            ui -> ui.navigate("crear-campanya")   
           ));
        
        misCampanyaButton.setWidth("min-content");
        misCampanyaButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        misCampanyaButton.getStyle().set("cursor", "pointer");
        misCampanyaButton.addClickListener(e -> misCampanyaButton.getUI().ifPresent(
            ui -> ui.navigate("mis-campanyas")   
           ));

        getContent().add(layoutColumn2);
        layoutColumn2.add(h2);
        layoutColumn2.add(h3);
        layoutColumn2.add(crearCampanyaButton);
        layoutColumn2.add(misCampanyaButton);
        
    }
}
