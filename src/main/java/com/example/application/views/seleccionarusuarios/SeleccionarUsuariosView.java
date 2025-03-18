package com.example.application.views.seleccionarusuarios;

import com.example.application.data.SamplePerson;
import com.example.application.services.SamplePersonService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Seleccionar Usuarios")
@Route("seleccionar-usuarios")
@Menu(order = 3, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class SeleccionarUsuariosView extends Composite<VerticalLayout> {

    public SeleccionarUsuariosView() {
        H3 h3 = new H3();
        Grid multiSelectGrid = new Grid(SamplePerson.class);
        FormLayout formLayout2Col = new FormLayout();
        Button siguienteButton = new Button();
        Button buttonSecondary = new Button();
        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setText("Seleccionar Usuarios");
        h3.setWidth("max-content");
        multiSelectGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        multiSelectGrid.setWidth("100%");
        multiSelectGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(multiSelectGrid);
        formLayout2Col.setWidth("100%");
        siguienteButton.setText("Siguiente");
        siguienteButton.setWidth("min-content");
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancelar");
        buttonSecondary.setWidth("min-content");
        
        siguienteButton.addClickListener(e -> siguienteButton.getUI().ifPresent(
            ui -> ui.navigate("seleccionar-encuestas")
        ));
        getContent().add(h3);
        getContent().add(multiSelectGrid);
        getContent().add(formLayout2Col);
        formLayout2Col.add(siguienteButton);
        formLayout2Col.add(buttonSecondary);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
