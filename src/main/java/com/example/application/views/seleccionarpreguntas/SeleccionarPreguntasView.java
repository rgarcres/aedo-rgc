package com.example.application.views.seleccionarpreguntas;

import com.example.application.data.Pregunta;
import com.example.application.views.Personalizacion;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Seleccionar Preguntas")
@Route("seleccionar-preguntas")
@Menu(order = 4, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class SeleccionarPreguntasView extends Composite<VerticalLayout> {

    private final List<Pregunta> listaPreguntas = new ArrayList<>();
    private final List<Pregunta> preguntasFiltradas = new ArrayList<>();

    public SeleccionarPreguntasView() {
        H3 h3 = new H3("Seleccionar Preguntas");
        Grid<Pregunta> multiSelectGrid = new Grid<>();
        FormLayout formLayout2Col = new FormLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        Button crearButton = new Button("Crear Campaña");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        
        listaPreguntas.addAll(crearListaPreguntas());
        preguntasFiltradas.addAll(listaPreguntas);

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");
        multiSelectGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        multiSelectGrid.setWidth("100%");
        multiSelectGrid.getStyle().set("flex-grow", "0");
        formLayout2Col.setWidth("100%");
        tituloLayout.setWidth("100%");
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Personalizacion.configurarBoton(crearButton, "mis-campanyas");
        Personalizacion.configurarBoton(cancelarButton, "");
        Personalizacion.configurarBoton(atrasButton, "seleccionar-usuarios");
        
        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton);
        tituloLayout.add(h3);
        getContent().add(multiSelectGrid);
        getContent().add(formLayout2Col);
        formLayout2Col.add(crearButton);
        formLayout2Col.add(cancelarButton);
    }

    private List<Pregunta> crearListaPreguntas(){
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta());
        return preguntas;
    }

    /* 
    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
    */
}
