package com.example.application.views.miscampanyas;

import com.example.application.data.Campanya;
import com.example.application.views.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Mis Campañas")
@Route("mis-campanyas")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILTER_SOLID)
@Uses(Icon.class)
public class MisCampanyasView extends Composite<VerticalLayout> {

    private final List<Campanya> listaCamps = new ArrayList<>();
    private Campanya camp = (Campanya) VaadinSession.getCurrent().getAttribute("nuevaCampCompleta");

    public MisCampanyasView (){
        H3 h3 = new H3("Mis Campañas");
        HorizontalLayout tituloLayout = new HorizontalLayout();
        Grid<Campanya> gridCamps = new Grid<>();
        Button atrasButton = new Button("<");

        Utilidades.configurarBoton(atrasButton, "");
        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");

        if(camp != null){
            listaCamps.add(camp);
        }
        gridCamps.setItems(listaCamps);
        configurarGrid(gridCamps);

        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton, h3);
        getContent().add(gridCamps);
    }

    private void configurarGrid(Grid<Campanya> grid){
        grid.setWidth("100%");
        grid.getStyle().set("flex-grow", "0");
        grid.addColumn(Campanya::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Campanya::getInicio).setHeader("Inicio").setAutoWidth(true);
        grid.addColumn(Campanya::getFin).setHeader("Fin").setAutoWidth(true);
        grid.addColumn(Campanya::getObjetivos).setHeader("Objetivos").setAutoWidth(true);
        grid.addColumn(Campanya::getDemografia).setHeader("Demografia").setAutoWidth(true);
        grid.addColumn(c -> c.getUsuarios()).setHeader("Usuarios").setAutoWidth(true);
        grid.addColumn(c -> c.getPreguntas()).setHeader("Preguntas").setAutoWidth(true);
    }

}
