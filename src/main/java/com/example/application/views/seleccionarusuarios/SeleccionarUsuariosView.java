package com.example.application.views.seleccionarusuarios;

import com.example.application.data.Genero;
import com.example.application.data.Grupo;
import com.example.application.data.NivelEstudios;
//import com.example.application.data.SamplePerson;
import com.example.application.data.SituacionLaboral;
import com.example.application.data.Usuario;
import com.example.application.views.utilidades.BotonesCreator;
import com.example.application.views.utilidades.ListaCreator;
import com.example.application.views.utilidades.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
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
//import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.server.VaadinSession;


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

    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");
    private Grupo grupoEdit = (Grupo) VaadinSession.getCurrent().getAttribute("grupoEdit");

    public SeleccionarUsuariosView() {
        H3 h3 = new H3("Seleccionar Usuarios");
        H4 error = new H4("Selcciona algun usuario");
        error.getStyle().set("color", "#ff4e4e");
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

        Button crearButton = new Button("Crear grupo");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");    
        formLayout2Col.setWidth("100%");
        filtrosLayout.setWidth("100%");
        tituloLayout.setWidth("100%");

        //Establecer valores de las MultiSelectComboBox
        selectGenero.setItems(Genero.DESCONOCIDO, Genero.HOMBRE, Genero.MUJER, Genero.NO_BINARIO, Genero.OTRO);
        selectNivelEstudios.setItems(NivelEstudios.DESCONOCIDO, NivelEstudios.ESO, NivelEstudios.BACHILLERATO, NivelEstudios.FORMACION_PROFESIONAL, NivelEstudios.GRADO_UNIVERSITARIO, NivelEstudios.MASTER, NivelEstudios.DOCTORADO, NivelEstudios.OTRO);
        selectSituacionLaboral.setItems(SituacionLaboral.DESCONOCIDO, SituacionLaboral.ASALARIADO, SituacionLaboral.AUTONOMO, SituacionLaboral.PARO, SituacionLaboral.TIEMPO_PARCIAL, SituacionLaboral.TIEMPO_TOTAL, SituacionLaboral.OTRO);

        //PERSONALIZAR GRID
        gridUsuario.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUsuario.setWidth("100%");
        gridUsuario.getStyle().set("flex-grow", "0");
        usuarios.addAll(ListaCreator.crearListaUsuarios());
        usuariosFiltrados.addAll(usuarios);
        if(grupoEdit == null){
            gridUsuario.setItems(usuariosFiltrados);
        } else {
            gridUsuario.setItems(grupoEdit.getUsuarios());
            gridUsuario.asMultiSelect().select(grupoEdit.getUsuarios());
        }

        //FILTROS
        selectGenero.addValueChangeListener(e -> {
            seleccionGeneros = e.getValue();
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        selectNivelEstudios.addValueChangeListener(e -> {
            seleccionEstudios = e.getValue();
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        selectSituacionLaboral.addValueChangeListener(e -> {
            seleccionLaboral = e.getValue();
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });

        //TEXTLABEL COMPORTAMIENTO AL PULSAR ENTER
        textFieldNombre.addKeyPressListener(Key.ENTER, e -> {
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        textFieldApellido.addKeyPressListener(Key.ENTER, e -> {
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        textFieldRangoMin.addKeyPressListener(Key.ENTER, e -> {
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        textFieldRangoMax.addKeyPressListener(Key.ENTER, e -> {
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
        });
        //BOTONES
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(cancelarButton, "home");
        BotonesCreator.configurarBoton(limpiarButton);
        BotonesCreator.configurarBoton(buscarButton);
        BotonesCreator.configurarBoton(atrasButton);
        BotonesCreator.configurarBoton(crearButton);

        BotonesCreator.configurarBoton(atrasButton, "crear-grupo");
        atrasButton.addClickListener(e -> {
            listaGrupos.removeLast();
            VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);
        });

        buscarButton.addClickListener(e -> {
            aplicarFiltros(textFieldNombre, textFieldApellido, textFieldRangoMin, textFieldRangoMax, gridUsuario);
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
            actualizarGrid(gridUsuario);
        });

        //AÑADIR COMPONENENTES AL LAYOUT
        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton, h3);
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
        formLayout2Col.add(crearButton);
        formLayout2Col.add(cancelarButton);

        crearButton.addClickListener(e -> {
            Set<Usuario> seleccionados = gridUsuario.getSelectedItems();
            if(!seleccionados.isEmpty()){
                listaGrupos.getLast().setUsuarios(new ArrayList<>(seleccionados));
                VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);
                getUI().ifPresent(ui -> ui.navigate("mis-grupos"));
            } else {
                getContent().add(error);
            }
        });
        cancelarButton.addClickListener(e -> {
            if(grupoEdit == null){
                listaGrupos.removeLast();
            } else {
                listaGrupos.getLast().setUsuarios(grupoEdit.getUsuarios());
            }
            VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);
        });
    }



    private void actualizarGrid(Grid<Usuario> grid){
        //LIMPAR LA LISTA DE USUARIOS FILTRADOS
        usuariosFiltrados.clear();

        //AÑADE A LA LISTA LOS USUARIOS SEGÚN LOS FILTROS APLICADOS
        usuariosFiltrados.addAll(usuarios.stream()
        .filter(user -> seleccionGeneros.isEmpty() || seleccionGeneros.contains(user.getGenero()))
        .filter(user -> seleccionEstudios.isEmpty() || seleccionEstudios.contains(user.getNivelEstudios()))
        .filter(user -> seleccionLaboral.isEmpty() || seleccionLaboral.contains(user.getSituacionLaboral()))
        .filter(user -> nombreFiltro.isBlank() || Utilidades.buscarCoincidencias(user.getNombre().toLowerCase(), nombreFiltro))
        .filter(user -> apellidoFiltro.isBlank() || Utilidades.buscarCoincidencias(user.getApellido().toLowerCase(), apellidoFiltro))
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

    private void aplicarFiltros(TextField textFieldNombre,TextField textFieldApellido, 
                                TextField textFieldRangoMin,TextField textFieldRangoMax, Grid<Usuario> grid){
        nombreFiltro = textFieldNombre.getValue().toLowerCase();
        apellidoFiltro = textFieldApellido.getValue().toLowerCase();
        rangoMinFiltro = textFieldRangoMin.getValue();
        rangoMaxFiltro = textFieldRangoMax.getValue();
        actualizarGrid(grid);
    }
}
