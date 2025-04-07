package com.example.application.views.miscampanyas;

import com.example.application.data.Campanya;
import com.example.application.views.utilidades.BotonesCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Mis Campañas")
@Route("mis-campanyas")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILTER_SOLID)
@Uses(Icon.class)
public class MisCampanyasView extends Composite<VerticalLayout> {

    @SuppressWarnings("unchecked")
    private final List<Campanya> listaCamps = (List<Campanya>) VaadinSession.getCurrent().getAttribute("listaCamps");

    public MisCampanyasView (){
        H3 h3 = new H3("Mis Campañas");
        H4 errorMsg = new H4("Selecciona una campaña para borrarla");
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout botonesLayout = new HorizontalLayout();
        Grid<Campanya> gridCamps = new Grid<>();
        Button atrasButton = new Button("<");
        Button borrarButton = new Button("Borrar Campaña");

        BotonesCreator.configurarBoton(atrasButton, "home");
        BotonesCreator.configurarBoton(borrarButton);
        borrarButton.getStyle().setBackgroundColor("#cd3b3b");

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");
        configurarGrid(gridCamps);

        gridCamps.setItems(listaCamps);
        gridCamps.addItemDoubleClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("campEdit", e.getItem());
            getUI().ifPresent(ui -> ui.navigate("editar-campanya"));
        });
        

        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton, h3);
        getContent().add(gridCamps);
        getContent().add(botonesLayout);
        botonesLayout.add(borrarButton);
        borrarButton.addClickListener(e->{
            Campanya camp = gridCamps.asSingleSelect().getValue();
            if(camp != null){
                listaCamps.remove(camp);
                gridCamps.setItems(listaCamps);
                Notification.show("Se ha eleminado la campaña: "+camp.getNombre());
            } else {
                getContent().add(errorMsg);
            }
        });
    }

    private void configurarGrid(Grid<Campanya> grid){
        grid.setWidth("100%");
        grid.getStyle().set("flex-grow", "0");
        grid.addColumn(Campanya::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Campanya::getInicio).setHeader("Inicio").setAutoWidth(true);
        grid.addColumn(Campanya::getFin).setHeader("Fin").setAutoWidth(true);
        grid.addColumn(Campanya::getObjetivos).setHeader("Objetivos").setAutoWidth(true);
        grid.addColumn(Campanya::getDemografia).setHeader("Demografia").setAutoWidth(true);
        grid.addColumn(c -> c.getGrupos()).setHeader("Grupos").setAutoWidth(true);
        grid.addColumn(c -> c.getPreguntas()).setHeader("Preguntas").setAutoWidth(true);
    }

}
