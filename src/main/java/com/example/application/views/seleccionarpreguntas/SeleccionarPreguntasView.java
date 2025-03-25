package com.example.application.views.seleccionarpreguntas;

import com.example.application.data.Pregunta;
import com.example.application.views.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Seleccionar Preguntas")
@Route("seleccionar-preguntas")
@Menu(order = 4, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class SeleccionarPreguntasView extends Composite<VerticalLayout> {

    private final List<Pregunta> listaPreguntas = new ArrayList<>();
    private final List<Pregunta> preguntasFiltradas = new ArrayList<>();

    private String enunciadoFiltro = "";
    private Set<Integer> tiposFiltro = new HashSet<>();

    public SeleccionarPreguntasView() {
        H3 h3 = new H3("Seleccionar Preguntas");
        Grid<Pregunta> gridPreguntas = new Grid<>();
        FormLayout formLayout2Col = new FormLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout filtrosLayout = new HorizontalLayout();

        Button crearButton = new Button("Crear Campaña");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");
        TextField textFieldEnunciado = new TextField("Enunciado");
        CheckboxGroup<Integer> checkboxTipo = new CheckboxGroup<>("Tipo");
        
        listaPreguntas.addAll(Utilidades.crearListaPreguntas());
        preguntasFiltradas.addAll(listaPreguntas);
        gridPreguntas.setItems(preguntasFiltradas);

        //CONFIGURAR EL GRID Y AÑADIR COLUMNAS
        gridPreguntas.setSelectionMode(Grid.SelectionMode.MULTI);
        gridPreguntas.setWidth("100%");
        gridPreguntas.getStyle().set("flex-grow", "0");
        //ENUNCIADO
        gridPreguntas.addColumn(Pregunta::getEnunciado).setHeader("Pregunta").setAutoWidth(true);
        //RESPUESTA
        gridPreguntas.addColumn(p -> String.join(",", p.getRespuestas())).setHeader("Respuesta").setAutoWidth(true);
        //TIPO
        gridPreguntas.addColumn(Pregunta::getTipo).setHeader("Tipo").setAutoWidth(true);

        //CONFIGURAR BOTONES
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Utilidades.configurarBoton(buscarButton);
        Utilidades.configurarBoton(limpiarButton);
        Utilidades.configurarBoton(crearButton, "mis-campanyas");
        Utilidades.configurarBoton(cancelarButton, "");
        Utilidades.configurarBoton(atrasButton, "seleccionar-usuarios");
        buscarButton.addClickListener(e -> {
            enunciadoFiltro = textFieldEnunciado.getValue();
            actualizarFiltros(gridPreguntas);
        });
        limpiarButton.addClickListener(e -> {
            textFieldEnunciado.clear();
            checkboxTipo.clear();
            enunciadoFiltro = "";
            actualizarFiltros(gridPreguntas);
        });

        //CHECKBOX
        checkboxTipo.setItems(1,2,3,4);
        checkboxTipo.addValueChangeListener(e -> {
            tiposFiltro = checkboxTipo.getValue();
            actualizarFiltros(gridPreguntas);
        });

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");
        formLayout2Col.setWidth("100%");
        tituloLayout.setWidth("100%");
        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton);
        tituloLayout.add(h3);
        getContent().add(filtrosLayout);
        filtrosLayout.add(textFieldEnunciado);
        filtrosLayout.add(checkboxTipo);
        filtrosLayout.add(buscarButton);
        filtrosLayout.add(limpiarButton);
        getContent().add(gridPreguntas);
        getContent().add(formLayout2Col);
        formLayout2Col.add(crearButton);
        formLayout2Col.add(cancelarButton);
        
    }

    private void actualizarFiltros(Grid<Pregunta> grid){
        preguntasFiltradas.clear();
        
        preguntasFiltradas.addAll(listaPreguntas.stream()
        .filter(pregunta -> enunciadoFiltro.isBlank() || buscarCoincidencias(enunciadoFiltro, pregunta.getEnunciado()))
        .filter(pregunta -> tiposFiltro.isEmpty() || buscarTipos(tiposFiltro, pregunta.getTipo()))
        .collect(Collectors.toList())
        );

        grid.setItems(preguntasFiltradas);
    }

    private boolean buscarTipos(Set<Integer> filtro, Integer tipo){
        if(filtro == null || tipo == null){
            return false;
        }

        for(Integer t: filtro){
            if(t == tipo) {
                return true;
            }
        }
        return false;
    }

    private boolean buscarCoincidencias(String filtro, String enunciado){
        if(filtro == null || enunciado == null){
            return false;
        }

        String enunciadoSinTilde = Utilidades.quitarTildes(enunciado);
        String filtroSinTilde = Utilidades.quitarTildes(filtro);
        
        if(enunciadoSinTilde.contains(filtroSinTilde)){
            return true;
        }

        return false;
    }

    /* 
    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
    */
}
