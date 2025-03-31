package com.example.application.views.seleccionarpreguntas;

import com.example.application.data.Bloque;
import com.example.application.data.Campanya;
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
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
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
        H4 errorMsg = new H4("Selecciona alguna pregunta");
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
        Bloque bloqueSelec = (Bloque) VaadinSession.getCurrent().getAttribute("bloqueSelec");

        preguntasFiltradas.addAll(preguntasPorBloqueSeleccionado(bloqueSelec, listaPreguntas));

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
        if(campEdit == null){
            gridPreguntas.setItems(preguntasFiltradas);
            gridPreguntas.asMultiSelect().select(preguntasFiltradas);
        } else {
            gridPreguntas.setItems(campEdit.getPreguntas());
            gridPreguntas.asMultiSelect().select(campEdit.getPreguntas());
        }

        //CONFIGURAR BOTONES
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Utilidades.configurarBoton(buscarButton);
        Utilidades.configurarBoton(limpiarButton);
        Utilidades.configurarBoton(crearButton);
        Utilidades.configurarBoton(cancelarButton, "");
        Utilidades.configurarBoton(atrasButton, "seleccionar-usuarios");
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

        //CHECKBOX
        checkboxTipo.setItems(1,2,3,4);
        checkboxTipo.addValueChangeListener(e -> {
            tiposFiltro = checkboxTipo.getValue();
            actualizarGrid(gridPreguntas);
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
        
        //Cuando se pulsa en el botón de crear se comprueba si se ha seleccionado alguna pregunta
        //Si se selecciona alguna pregunta se añaden a la campaña de la lista y se navega a la siguiente ventana
        //Si no se selecciona ninguna muestra un mensaje de error
        crearButton.addClickListener(e -> {
            Set<Pregunta> seleccionadas = gridPreguntas.getSelectedItems();
            if(!seleccionadas.isEmpty()){
                listaCamps.getLast().setPreguntas(new ArrayList<>(seleccionadas));
                VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
                VaadinSession.getCurrent().setAttribute("campEdit", null);
                VaadinSession.getCurrent().setAttribute("campMedioCreada", null);
                getUI().ifPresent(ui -> ui.navigate("mis-campanyas"));
            } else {
                getContent().add(errorMsg);
            }
        });

        cancelarButton.addClickListener(e-> {
            if(campEdit == null){
                listaCamps.removeLast();
            } else {
                listaCamps.getLast().setPreguntas(campEdit.getPreguntas());
            }
            VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
        });
    }

    //Cuando se aplica un filtro se llama a este método para filtrar la lista de Preguntas que se muestra
    //en el grid
    private void actualizarGrid(Grid<Pregunta> grid){
        preguntasFiltradas.clear();
        
        preguntasFiltradas.addAll(listaPreguntas.stream()
        .filter(pregunta -> enunciadoFiltro.isBlank() || buscarCoincidencias(enunciadoFiltro, pregunta.getEnunciado()))
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

    //Realiza una comparación entre el texto que se ha introducido en el filtro
    //y el enunciado de la pregunta, solucionando conflictos de tildes y mayusculas
    private boolean buscarCoincidencias(String filtro, String enunciado){
        if(filtro == null || enunciado == null){
            return false;
        }

        String enunciadoSinTilde = Utilidades.quitarTildes(enunciado).toLowerCase();
        String filtroSinTilde = Utilidades.quitarTildes(filtro).toLowerCase();
        
        if(enunciadoSinTilde.contains(filtroSinTilde)){
            return true;
        }

        return false;
    }

    //Genera una lista de preguntas a partir de la pregunta pasada por parámetro que pertenezcan al
    //bloque pasado como primer parámetro
    private List<Pregunta> preguntasPorBloqueSeleccionado(Bloque bloque, List<Pregunta> preguntas){
        List<Pregunta> lp = new ArrayList<>();

        for(Pregunta p: preguntas){
            if(p.getBloque().getNombre().equals(bloque.getNombre())){
                lp.add(p);
            }
        }
        return lp;
    }
}
