package com.example.application.views.seleccionarusuarios;

import com.example.application.data.Genero;
import com.example.application.data.NivelEstudios;
//import com.example.application.data.SamplePerson;
import com.example.application.data.SituacionLaboral;
import com.example.application.data.Usuario;
//import com.example.application.services.SamplePersonService;
import com.example.application.views.Personalizacion;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
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
        selectSituacionLaboral.setItems(SituacionLaboral.DESCONOCIDO, SituacionLaboral.ASALARIADO, SituacionLaboral.AUTONOMO, SituacionLaboral.PARO, SituacionLaboral.TIEMPO_PARCIAL, SituacionLaboral.TIMEPO_TOTAL, SituacionLaboral.OTRO);

        //Personalizar el Grid de usuarios
        gridUsuario.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUsuario.setWidth("100%");
        gridUsuario.getStyle().set("flex-grow", "0");
        usuarios.addAll(crearListaUsuarios());
        usuariosFiltrados.addAll(usuarios);
        gridUsuario.setItems(usuariosFiltrados);

        //Filtros
        textFieldNombre.addKeyPressListener(Key.ENTER, e -> {
            nombreFiltro = textFieldNombre.getValue().toLowerCase();
            apellidoFiltro = textFieldApellido.getValue().toLowerCase();
            actualizarFiltros(gridUsuario);
        });
        textFieldApellido.addKeyPressListener(Key.ENTER, e -> {
            nombreFiltro = textFieldNombre.getValue().toLowerCase();
            apellidoFiltro = textFieldApellido.getValue().toLowerCase();
            actualizarFiltros(gridUsuario);
        });
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

        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buscarButton.setWidth("min-content");
        limpiarButton.setWidth("min-content");
        atrasButton.setWidth("min-content");
        Personalizacion.configurarBoton(siguienteButton, "seleccionar-encuestas");
        Personalizacion.configurarBoton(cancelarButton, "");
        Personalizacion.configurarBoton(atrasButton, "crear-campanya");
        buscarButton.addClickListener(e -> {
            nombreFiltro = textFieldNombre.getValue().toLowerCase();
            apellidoFiltro = textFieldApellido.getValue().toLowerCase();
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
            actualizarFiltros(gridUsuario);
        });
        
        //Añadir componentes al layout
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
        botonesFiltrosLayout.add(buscarButton);
        botonesFiltrosLayout.add(limpiarButton);
        getContent().add(gridUsuario);
        getContent().add(formLayout2Col);
        formLayout2Col.add(siguienteButton);
        formLayout2Col.add(cancelarButton);
    }


    private void actualizarFiltros(Grid<Usuario> grid){
        usuariosFiltrados.clear();
        usuariosFiltrados.addAll(usuarios.stream()
        .filter(user -> seleccionGeneros.isEmpty() || seleccionGeneros.contains(user.getGenero()))
        .filter(user -> seleccionEstudios.isEmpty() || seleccionEstudios.contains(user.getNivelEstudios()))
        .filter(user -> seleccionLaboral.isEmpty() || seleccionLaboral.contains(user.getSituacionLaboral()))
        .filter(user -> nombreFiltro.isBlank() || buscarCoincidencias(user.getNombre().toLowerCase(), nombreFiltro))
        .filter(user -> apellidoFiltro.isBlank() || buscarCoincidencias(user.getApellido().toLowerCase(), apellidoFiltro))
        .collect(Collectors.toList()));

        grid.setItems(usuariosFiltrados);
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

    private List<Usuario> crearListaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("Ruben", "Garcia Crespo", Genero.HOMBRE, SituacionLaboral.OTRO, NivelEstudios.BACHILLERATO, "550-1000", "Situacion Personal"));
        usuarios.add(new Usuario("Juan", "Cuesta", Genero.HOMBRE, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.MASTER, "2300-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Pedro", "Martínez López", Genero.HOMBRE, SituacionLaboral.ASALARIADO, NivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Carlos", "Sánchez Ruiz", Genero.HOMBRE, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.FORMACION_PROFESIONAL, "1800-2200", "Situacion Personal"));
        usuarios.add(new Usuario("Frank", "Sinatra", Genero.HOMBRE, SituacionLaboral.AUTONOMO, NivelEstudios.DOCTORADO, "3000-3500", "Situacion Personal"));
        usuarios.add(new Usuario("Antonio", "Hernández Pérez", Genero.HOMBRE, SituacionLaboral.PARO, NivelEstudios.ESO, "0", "Situacion Personal"));

        usuarios.add(new Usuario("Maria", "Gomez Perez", Genero.MUJER, SituacionLaboral.ASALARIADO, NivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Lucia", "Sanchez Garcia", Genero.MUJER, SituacionLaboral.OTRO, NivelEstudios.MASTER, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Patricia", "Sánchez Gómez", Genero.MUJER, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.MASTER, "2500-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Laura", "Fernández Ruiz", Genero.MUJER, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.FORMACION_PROFESIONAL, "1700-2000", "Situacion Personal"));
        usuarios.add(new Usuario("Sandra", "García Pérez", Genero.MUJER, SituacionLaboral.AUTONOMO, NivelEstudios.GRADO_UNIVERSITARIO, "2100-2700", "Situacion Personal"));
        usuarios.add(new Usuario("Bad", "Gyal", Genero.MUJER, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.GRADO_UNIVERSITARIO, "1900-2400", "Situacion Personal"));

        usuarios.add(new Usuario("Alex", "Ruiz Fernández", Genero.NO_BINARIO, SituacionLaboral.ASALARIADO, NivelEstudios.MASTER, "2200-2800", "Situacion Personal"));
        usuarios.add(new Usuario("Taylor", "Smith", Genero.NO_BINARIO, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.BACHILLERATO, "1500-1800", "Situacion Personal"));
        usuarios.add(new Usuario("Robin", "Nico", Genero.NO_BINARIO, SituacionLaboral.PARO, NivelEstudios.ESO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Jordan", "Williams", Genero.NO_BINARIO, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.FORMACION_PROFESIONAL, "1600-2000", "Situacion Personal"));
        usuarios.add(new Usuario("Sanji", "Vinsmoke", Genero.NO_BINARIO, SituacionLaboral.ASALARIADO, NivelEstudios.DOCTORADO, "2500-3300", "Situacion Personal"));

        usuarios.add(new Usuario("Chris", "Evans", Genero.OTRO, SituacionLaboral.DESCONOCIDO, NivelEstudios.DESCONOCIDO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Jamie", "Lee Curtis", Genero.OTRO, SituacionLaboral.AUTONOMO, NivelEstudios.GRADO_UNIVERSITARIO, "2200-2800", "Situacion Personal"));
        usuarios.add(new Usuario("Sam", "Taylor-Johnson", Genero.OTRO, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.FORMACION_PROFESIONAL, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Alex", "Turner", Genero.OTRO, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.MASTER, "2000-2600", "Situacion Personal"));
        usuarios.add(new Usuario("Jordan", "Peterson", Genero.OTRO, SituacionLaboral.OTRO, NivelEstudios.BACHILLERATO, "1700-2300", "Situacion Personal"));

        usuarios.add(new Usuario("Anónimo", "Sin datos", Genero.DESCONOCIDO, SituacionLaboral.DESCONOCIDO, NivelEstudios.DESCONOCIDO, "0", "Situacion Personal"));

        return usuarios;
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
