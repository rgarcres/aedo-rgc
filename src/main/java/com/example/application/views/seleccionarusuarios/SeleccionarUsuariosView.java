package com.example.application.views.seleccionarusuarios;

import com.example.application.data.EGenero;
import com.example.application.data.Grupo;
import com.example.application.data.ENivelEstudios;
//import com.example.application.data.SamplePerson;
import com.example.application.data.ESituacionLaboral;
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

    private Set<EGenero> seleccionGeneros = new HashSet<>();
    private Set<ENivelEstudios> seleccionEstudios = new HashSet<>();
    private Set<ESituacionLaboral> seleccionLaboral = new HashSet<>();
    private String nombreFiltro;
    private String apellidoFiltro;
    private String rangoMinFiltro;
    private String rangoMaxFiltro;

    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");
    private Grupo grupoEdit = (Grupo) VaadinSession.getCurrent().getAttribute("grupoEdit");

    public SeleccionarUsuariosView() {
    //---------Inicialización de componentes---------
        //---------Encabezados---------
        H3 h3 = new H3("Seleccionar Usuarios");
        H4 error = new H4("Selcciona algun usuario");
        error.getStyle().set("color", "#ff4e4e");

        //---------Grid---------
        Grid<Usuario> gridUsuario = new Grid<>(Usuario.class);

        //---------Layouts---------
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout filtrosLayout = new HorizontalLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        HorizontalLayout botonesFiltrosLayout = new HorizontalLayout(); 
        HorizontalLayout botonesFinalLayout = new HorizontalLayout();

        //---------Filtros---------
        MultiSelectComboBox<EGenero> selectGenero = new MultiSelectComboBox<>("Genero"); 
        MultiSelectComboBox<ENivelEstudios> selectNivelEstudios = new MultiSelectComboBox<>("Nivel Estudios"); 
        MultiSelectComboBox<ESituacionLaboral> selectSituacionLaboral = new MultiSelectComboBox<>("Situacion Laboral");
        TextField textFieldNombre = new TextField("Nombre");
        TextField textFieldApellido = new TextField("Apellidos");
        TextField textFieldRangoMin = new TextField("Rango Salarial Min");
        TextField textFieldRangoMax = new TextField("Rango Salarial Max");

        //---------Botones---------
        Button crearButton = new Button("Crear grupo");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");

    //---------Configurar el layout---------
        configurarLayout(mainLayout);
        h3.setWidth("max-content");    
        botonesFinalLayout.setWidth("100%");
        filtrosLayout.setWidth("100%");
        tituloLayout.setWidth("100%");

    //---------Establecer valores de las MultiSelectComboBox---------
        selectGenero.setItems(EGenero.DESCONOCIDO, EGenero.HOMBRE, EGenero.MUJER, EGenero.NO_BINARIO, EGenero.OTRO);
        selectNivelEstudios.setItems(ENivelEstudios.DESCONOCIDO, ENivelEstudios.ESO, ENivelEstudios.BACHILLERATO, ENivelEstudios.FORMACION_PROFESIONAL, ENivelEstudios.GRADO_UNIVERSITARIO, ENivelEstudios.MASTER, ENivelEstudios.DOCTORADO, ENivelEstudios.OTRO);
        selectSituacionLaboral.setItems(ESituacionLaboral.DESCONOCIDO, ESituacionLaboral.ASALARIADO, ESituacionLaboral.AUTONOMO, ESituacionLaboral.PARO, ESituacionLaboral.TIEMPO_PARCIAL, ESituacionLaboral.TIEMPO_TOTAL, ESituacionLaboral.OTRO);

    //---------Grid---------
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

    //---------Filtros---------
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

    //---------Textlabel al pulsar Enter---------
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
        //---------Botones---------
        crearButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(cancelarButton, "home");
        BotonesCreator.configurarBoton(limpiarButton);
        BotonesCreator.configurarBoton(buscarButton);
        BotonesCreator.configurarBoton(crearButton);

        if(grupoEdit == null){
            BotonesCreator.configurarBoton(atrasButton, "crear-grupo");
        } else {
            BotonesCreator.configurarBoton(atrasButton, "editar-grupo");
        }
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

        //---------Añadir componentes al layout---------
        mainLayout.add(tituloLayout);
        tituloLayout.add(atrasButton, h3);
        tituloLayout.setAlignItems(Alignment.CENTER);
        tituloLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.add(filtrosLayout);
        filtrosLayout.setAlignItems(Alignment.CENTER);
        filtrosLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        filtrosLayout.add(textFieldNombre, textFieldApellido, selectGenero, selectNivelEstudios);
        mainLayout.add(botonesFiltrosLayout);
        botonesFiltrosLayout.setAlignItems(Alignment.CENTER);
        botonesFiltrosLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        botonesFiltrosLayout.add(selectSituacionLaboral, textFieldRangoMin, textFieldRangoMax, buscarButton, limpiarButton);
        getContent().add(gridUsuario);
        getContent().add(botonesFinalLayout);
        botonesFinalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        botonesFinalLayout.add(crearButton, cancelarButton);

        crearButton.addClickListener(e -> {
            Set<Usuario> seleccionados = gridUsuario.getSelectedItems();
            if(!seleccionados.isEmpty()){
                listaGrupos.getLast().setUsuarios(new ArrayList<>(seleccionados));
                VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);
                ponerAtributosNull();
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

    private void ponerAtributosNull() {
        VaadinSession.getCurrent().setAttribute("grupoMedioCreado", null);
        VaadinSession.getCurrent().setAttribute("grupoMedioEditado", null);
        VaadinSession.getCurrent().setAttribute("grupoEdit", null);
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
