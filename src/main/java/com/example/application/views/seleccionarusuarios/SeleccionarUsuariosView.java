package com.example.application.views.seleccionarusuarios;

import com.example.application.data.Genero;
import com.example.application.data.NivelEstudios;
//import com.example.application.data.SamplePerson;
import com.example.application.data.SituacionLaboral;
import com.example.application.data.Usuario;
//import com.example.application.services.SamplePersonService;
import com.example.application.views.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
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
//import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Seleccionar Usuarios")
@Route("seleccionar-usuarios")
@Menu(order = 3, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class SeleccionarUsuariosView extends Composite<VerticalLayout> {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Usuario> usuariosFiltrados = new ArrayList<>();

    private Set<Genero> seleccionGeneros = new HashSet<>();
    private Set<NivelEstudios> seleccionEstudios = new HashSet<>();
    private Set<SituacionLaboral> seleccionLaboral = new HashSet<>();
    private String nombreFiltro;
    private String apellidoFiltro;
    private String rangoMinFiltro;
    private String rangoMaxFiltro;

    public SeleccionarUsuariosView() {
        H3 h3 = new H3("Seleccionar Usuarios");
        Grid<Usuario> gridUsuario = new Grid<>(Usuario.class);
        HorizontalLayout filtrosLayout = new HorizontalLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout botonesFiltrosLayout = new HorizontalLayout(); 
        FormLayout formLayout2Col = new FormLayout();

        MultiSelectComboBox<Genero> selectGenero = new MultiSelectComboBox<>("Genero"); 
        MultiSelectComboBox<NivelEstudios> selectNivelEstudios = new MultiSelectComboBox<>("Nivel Estudios"); 
        MultiSelectComboBox<SituacionLaboral> selectSituacionLaboral = new MultiSelectComboBox<>("Situacion Laboral");
        TextField textFieldNombre = new TextField("Nombre");
        TextField textFieldApellido = new TextField("Apellidos");
        TextField textFieldRangoMin = new TextField("Rango Salarial Min");
        TextField textFieldRangoMax = new TextField("Rango Salarial Max");

        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");

        //Establecer valores de las MultiSelectComboBox
        selectGenero.setItems(Genero.DESCONOCIDO, Genero.HOMBRE, Genero.MUJER, Genero.NO_BINARIO, Genero.OTRO);
        selectNivelEstudios.setItems(NivelEstudios.DESCONOCIDO, NivelEstudios.ESO, NivelEstudios.BACHILLERATO, NivelEstudios.FORMACION_PROFESIONAL, NivelEstudios.GRADO_UNIVERSITARIO, NivelEstudios.MASTER, NivelEstudios.DOCTORADO, NivelEstudios.OTRO);
        selectSituacionLaboral.setItems(SituacionLaboral.DESCONOCIDO, SituacionLaboral.ASALARIADO, SituacionLaboral.AUTONOMO, SituacionLaboral.PARO, SituacionLaboral.TIEMPO_PARCIAL, SituacionLaboral.TIEMPO_TOTAL, SituacionLaboral.OTRO);

        //PERSONALIZAR GRID
        gridUsuario.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUsuario.setWidth("100%");
        gridUsuario.getStyle().set("flex-grow", "0");
        usuarios.addAll(Utilidades.crearListaUsuarios());
        usuariosFiltrados.addAll(usuarios);
        gridUsuario.setItems(usuariosFiltrados);

        //Filtros
        selectGenero.addValueChangeListener(e -> {
            seleccionGeneros = e.getValue();
            actualizarFiltros(gridUsuario);
        });
        selectNivelEstudios.addValueChangeListener(e -> {
            seleccionEstudios = e.getValue();
            actualizarFiltros(gridUsuario);
        });
        selectSituacionLaboral.addValueChangeListener(e -> {
            seleccionLaboral = e.getValue();
            actualizarFiltros(gridUsuario);
        });

        formLayout2Col.setWidth("100%");
        filtrosLayout.setWidth("100%");
        tituloLayout.setWidth("100%");

        //BOTONES
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.setWidth("min-content");
        limpiarButton.setWidth("min-content");
        atrasButton.setWidth("min-content");
        Utilidades.configurarBoton(siguienteButton, "seleccionar-preguntas");
        Utilidades.configurarBoton(cancelarButton, "");
        Utilidades.configurarBoton(atrasButton, "crear-campanya");
        buscarButton.getStyle().set("cursor", "pointer");
        limpiarButton.getStyle().set("cursor", "pointer");
        buscarButton.addClickListener(e -> {
            nombreFiltro = textFieldNombre.getValue().toLowerCase();
            apellidoFiltro = textFieldApellido.getValue().toLowerCase();
            rangoMinFiltro = textFieldRangoMin.getValue();
            rangoMaxFiltro = textFieldRangoMax.getValue();
            actualizarFiltros(gridUsuario);
        });
        limpiarButton.addClickListener(e -> {
            textFieldNombre.clear();
            textFieldApellido.clear();
            nombreFiltro = "";
            apellidoFiltro = "";
            selectGenero.clear();
            selectNivelEstudios.clear();
            selectSituacionLaboral.clear();
            textFieldRangoMin.clear();
            textFieldRangoMax.clear();
            rangoMinFiltro = "";
            rangoMaxFiltro = "";
            actualizarFiltros(gridUsuario);
        });
        
        //AÑADIR COMPONENENTES AL LAYOUT
        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton);
        tituloLayout.add(h3);
        getContent().add(filtrosLayout);
        filtrosLayout.add(textFieldNombre);
        filtrosLayout.add(textFieldApellido);
        filtrosLayout.add(selectGenero);
        filtrosLayout.add(selectNivelEstudios);
        filtrosLayout.add(selectSituacionLaboral);
        getContent().add(botonesFiltrosLayout);
        botonesFiltrosLayout.add(textFieldRangoMin);
        botonesFiltrosLayout.add(textFieldRangoMax);
        botonesFiltrosLayout.add(buscarButton);
        botonesFiltrosLayout.add(limpiarButton);
        getContent().add(gridUsuario);
        getContent().add(formLayout2Col);
        formLayout2Col.add(siguienteButton);
        formLayout2Col.add(cancelarButton);
    }


    private void actualizarFiltros(Grid<Usuario> grid){
        //LIMPAR LA LISTA DE USUARIOS FILTRADOS
        usuariosFiltrados.clear();

        //AÑADE A LA LISTA LOS USUARIOS SEGÚN LOS FILTROS APLICADOS
        usuariosFiltrados.addAll(usuarios.stream()
        .filter(user -> seleccionGeneros.isEmpty() || seleccionGeneros.contains(user.getGenero()))
        .filter(user -> seleccionEstudios.isEmpty() || seleccionEstudios.contains(user.getNivelEstudios()))
        .filter(user -> seleccionLaboral.isEmpty() || seleccionLaboral.contains(user.getSituacionLaboral()))
        .filter(user -> nombreFiltro.isBlank() || buscarCoincidencias(user.getNombre().toLowerCase(), nombreFiltro))
        .filter(user -> apellidoFiltro.isBlank() || buscarCoincidencias(user.getApellido().toLowerCase(), apellidoFiltro))
        .filter(user -> rangoMaxFiltro.isBlank() || filtrarRangoMax(rangoMaxFiltro, user.getRangoSalarial()))
        .filter(user -> rangoMinFiltro.isBlank() || filtrarRangoMin(rangoMinFiltro, user.getRangoSalarial()))
        .collect(Collectors.toList()));

        //PONE LOS USUARIOS FILTRADOS EN EL GRID
        grid.setItems(usuariosFiltrados);
    }

    private boolean filtrarRangoMax(String max, String rangoCompleto){
        if(rangoCompleto == "0"){
            return true;
        } 

        if (max == null || rangoCompleto == null){
            return false;
        }

        String[] rangoDividido = rangoCompleto.split("\\-");
        if(Double.parseDouble(rangoDividido[1]) <= Double.parseDouble(max)){
            return true;
        }
        return false;
    }

    private boolean filtrarRangoMin(String min, String rangoCompleto){
        if (min == null || rangoCompleto == null){
            return false;
        }

        String[] rangoDividido = rangoCompleto.split("\\-");
        if(Double.parseDouble(rangoDividido[0]) >= Double.parseDouble(min)){
            return true;
        }
        return false;
    }

    private boolean buscarCoincidencias(String valorCompleto, String filtro){
        if(valorCompleto == null || filtro == null){
            return false;
        }

        //Si hay dos apellidos dividimos el string en dos mediante el espacio en blanco
        String[] palabras = valorCompleto.split("\\s+");

        for(String p : palabras){
            if(p.contains(filtro)){
                return true;
            }
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

    /* 
    public static class UserFilters extends Div implements Specification<Usuario> {

        @Override
        public Predicate toPredicate(Root<Usuario> arg0, CriteriaQuery<?> arg1, CriteriaBuilder arg2) {
            // TODO Auto-generated method stub

            return null;
        }
    }
    */
}
