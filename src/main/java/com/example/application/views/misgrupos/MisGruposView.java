package com.example.application.views.misgrupos;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.data.Grupo;
import com.example.application.views.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
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
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout botonesLayout = new HorizontalLayout();
        Grid<Grupo> gridGrupos = new Grid<>();
        Button atrasButton = new Button("<");
        Button borrarButton = new Button("Borrar Grupo");

        Utilidades.configurarBoton(atrasButton, "");
        Utilidades.configurarBoton(borrarButton);
        borrarButton.getStyle().setBackgroundColor("#cd3b3b");

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");
        configurarGrid(gridGrupos);

        gridGrupos.setItems(listaGrupos);
        

        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton, h3);
        getContent().add(gridGrupos);
        getContent().add(botonesLayout);
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
}
