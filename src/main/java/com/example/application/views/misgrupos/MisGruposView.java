package com.example.application.views.misgrupos;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.data.Grupo;
import com.example.application.views.utilidades.BotonesCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Mis Grupos")
@Route("mis-grupos")
@Menu(order = 2, icon = LineAwesomeIconUrl.FILTER_SOLID)
public class MisGruposView extends Composite<VerticalLayout>{
    
    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");

    public MisGruposView(){
        H3 h3 = new H3("Mis Grupos");
        H4 errorMsg = new H4("Selecciona un grupo para borrarlo");
        errorMsg.getStyle().set("color", "#ff4e4e");
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout botonesLayout = new HorizontalLayout();
        Grid<Grupo> gridGrupos = new Grid<>();
        Button atrasButton = new Button("<");
        Button borrarButton = new Button("Borrar Grupo");

        BotonesCreator.configurarBoton(atrasButton, "home");
        BotonesCreator.configurarBoton(borrarButton);
        borrarButton.getStyle().setBackgroundColor("#cd3b3b");

        configurarLayout(mainLayout);
        h3.setWidth("max-content");
        configurarGrid(gridGrupos);

        gridGrupos.setItems(listaGrupos);
        gridGrupos.addItemDoubleClickListener(e -> {
            VaadinSession.getCurrent().setAttribute("grupoEdit", e.getItem());
            getUI().ifPresent(ui -> ui.navigate("editar-grupo"));
        });
        
        mainLayout.add(tituloLayout);
        tituloLayout.setAlignItems(Alignment.CENTER);
        tituloLayout.add(atrasButton, h3);
        mainLayout.add(gridGrupos);
        mainLayout.add(botonesLayout);
        botonesLayout.add(borrarButton);
        borrarButton.addClickListener(e->{
            Grupo grupo = gridGrupos.asSingleSelect().getValue();
            if(grupo != null){
                listaGrupos.remove(grupo);
                gridGrupos.setItems(listaGrupos);
                Notification.show("Se ha eleminado el grupo: "+ grupo.getNombre());
            } else {
                getContent().add(errorMsg);
            }
        });
    }

    private void configurarGrid(Grid<Grupo> grid) {
        grid.setWidth("100%");
        grid.getStyle().set("flex-grow", "0");
        grid.addColumn(Grupo::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Grupo::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Grupo::getDescripcion).setHeader("Descripcion").setAutoWidth(true);
        grid.addColumn(u -> u.getUsuarios()).setHeader("Usuarios").setAutoWidth(true);
    }

    private void configurarLayout(VerticalLayout mainLayout){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        mainLayout.setWidth("100%");
        mainLayout.setMaxWidth("800px");
        mainLayout.setHeight("min-content");
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);
        getContent().add(mainLayout);
    }
}
