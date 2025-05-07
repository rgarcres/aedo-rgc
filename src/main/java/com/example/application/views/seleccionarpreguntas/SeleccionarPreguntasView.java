package com.example.application.views.seleccionarpreguntas;

import com.example.application.data.Campanya;
import com.example.application.data.Pregunta;
import com.example.application.views.utilidades.BotonesCreator;
import com.example.application.views.utilidades.ListaCreator;
import com.example.application.views.utilidades.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

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

    @SuppressWarnings("unchecked")
    private final List<Campanya> listaCamps = (List<Campanya>) VaadinSession.getCurrent().getAttribute("listaCamps");
    private Campanya campEdit = (Campanya) VaadinSession.getCurrent().getAttribute("campEdit");

    public SeleccionarPreguntasView() {
        H3 h3 = new H3("Seleccionar Preguntas");
        h3.setWidth("max-content");
        H4 errorMsg = new H4("Selecciona alguna pregunta");
        Grid<Pregunta> gridPreguntas = new Grid<>();
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout filtrosLayout = new HorizontalLayout();
        HorizontalLayout botonesFinalLayout = new HorizontalLayout();

        Button crearButton = new Button("Crear Campaña");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");
        TextField textFieldEnunciado = new TextField("Enunciado");
        CheckboxGroup<Integer> checkboxTipo = new CheckboxGroup<>("Bloque");
        
        listaPreguntas.addAll(ListaCreator.crearListaPreguntas());
        preguntasFiltradas.addAll(listaPreguntas);

    //-----------Grid-----------
        configurarGrid(gridPreguntas);

    //-----------Configurar Botones-----------
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(buscarButton);
        BotonesCreator.configurarBoton(limpiarButton);
        BotonesCreator.configurarBoton(crearButton);
        BotonesCreator.configurarBoton(cancelarButton, "home");
        BotonesCreator.configurarBoton(atrasButton, "seleccionar-grupo");
        atrasButton.addClickListener(e-> {
            listaCamps.getLast().setPreguntas(null);
            VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
        });
        buscarButton.addClickListener(e -> {
            enunciadoFiltro = textFieldEnunciado.getValue();
            actualizarGrid(gridPreguntas);
        });
        limpiarButton.addClickListener(e -> {
            textFieldEnunciado.clear();
            checkboxTipo.clear();
            enunciadoFiltro = "";
            actualizarGrid(gridPreguntas);
        });

    //-----------Checkbox-----------
        checkboxTipo.setItems(1,2,3,4);
        checkboxTipo.addValueChangeListener(e -> {
            tiposFiltro = checkboxTipo.getValue();
            actualizarGrid(gridPreguntas);
        });

    //-----------Layout-----------
        configurarLayout(mainLayout);
        //Layout del titulo
        tituloLayout.setWidth("100%");
        tituloLayout.setAlignItems(Alignment.CENTER);
        tituloLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.add(tituloLayout);
        tituloLayout.add(atrasButton);
        tituloLayout.add(h3);
        //Layout de los filtros
        mainLayout.add(filtrosLayout);
        filtrosLayout.setAlignItems(Alignment.CENTER);
        filtrosLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        filtrosLayout.add(textFieldEnunciado);
        filtrosLayout.add(checkboxTipo);
        filtrosLayout.add(buscarButton);
        filtrosLayout.add(limpiarButton);

        mainLayout.add(gridPreguntas);
        //Layout de los botones del final
        mainLayout.add(botonesFinalLayout);
        botonesFinalLayout.setWidth("100%");
        botonesFinalLayout.setAlignItems(Alignment.CENTER);
        botonesFinalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        botonesFinalLayout.add(crearButton);
        botonesFinalLayout.add(cancelarButton);

    /*
    * Cuando se pulsa en el botón de crear se comprueba si se ha seleccionado alguna pregunta
    *      Si se selecciona alguna pregunta se añaden a la campaña de la lista y se navega a la siguiente ventana
    *      Si no se selecciona ninguna muestra un mensaje de error
    */
        crearButton.addClickListener(e -> {
            Set<Pregunta> seleccionadas = gridPreguntas.getSelectedItems();
            if(!seleccionadas.isEmpty()){
                listaCamps.getLast().setPreguntas(new ArrayList<>(seleccionadas));
                VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
                VaadinSession.getCurrent().setAttribute("campEdit", null);
                VaadinSession.getCurrent().setAttribute("campMedioCreada", null);
                VaadinSession.getCurrent().setAttribute("campMedioEditada", null);
                getUI().ifPresent(ui -> ui.navigate("mis-campanyas"));
            } else {
                getContent().add(errorMsg);
            }
        });
    //-----------Boton Cancelar-----------
        cancelarButton.addClickListener(e-> {
            listaCamps.removeLast();
            if(campEdit != null){
                listaCamps.add(campEdit);
            }
            VaadinSession.getCurrent().setAttribute("campEdit", null);
            VaadinSession.getCurrent().setAttribute("campMedioCreada", null);
            VaadinSession.getCurrent().setAttribute("campMedioEditada", null);
            VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
        });
    }

    //Cuando se aplica un filtro se llama a este método para filtrar la lista de Preguntas que se muestra
    //en el grid
    private void actualizarGrid(Grid<Pregunta> grid){
        preguntasFiltradas.clear();
        
        preguntasFiltradas.addAll(listaPreguntas.stream()
        .filter(pregunta -> enunciadoFiltro.isBlank() || Utilidades.buscarCoincidencias(enunciadoFiltro, pregunta.getEnunciado()))
        .filter(pregunta -> tiposFiltro.isEmpty() || buscarTipos(tiposFiltro, pregunta.getTipo()))
        .collect(Collectors.toList())
        );

        grid.setItems(preguntasFiltradas);
    }

    //Busca de toda la lista de preguntas las que tengan el tipo
    //que se ha seleccionado en los filtros
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

    //Configura el grid
    private void configurarGrid(Grid<Pregunta> gridPreguntas){
        gridPreguntas.setSelectionMode(Grid.SelectionMode.MULTI);
        gridPreguntas.setWidth("100%");
        gridPreguntas.getStyle().set("flex-grow", "0");
        //Enunciado
        gridPreguntas.addColumn(Pregunta::getEnunciado).setHeader("Pregunta").setAutoWidth(true);
        //Respuesta
        gridPreguntas.addColumn(p -> String.join(",", p.getRespuestas())).setHeader("Respuesta").setAutoWidth(true);
        //Tipo
        gridPreguntas.addColumn(Pregunta::getTipo).setHeader("Bloque").setAutoWidth(true);
        if(campEdit == null){
            gridPreguntas.setItems(preguntasFiltradas);
        } else {
            gridPreguntas.setItems(campEdit.getPreguntas());
            gridPreguntas.asMultiSelect().select(campEdit.getPreguntas());
        }
    }

    //Configura el layout
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
