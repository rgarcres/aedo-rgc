package com.example.application.views.seleccionarusuarios;

import com.example.application.data.Genero;
import com.example.application.data.NivelEstudios;
//import com.example.application.data.SamplePerson;
import com.example.application.data.SituacionLaboral;
import com.example.application.data.Usuario;
//import com.example.application.services.SamplePersonService;
import com.example.application.views.Personalizacion;
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

    public SeleccionarUsuariosView() {
        H3 h3 = new H3("Seleccionar Usuarios");
        Grid<Usuario> gridUsuario = new Grid<>(Usuario.class);
        HorizontalLayout filtrosLayout = new HorizontalLayout();
        HorizontalLayout tituloLayout = new HorizontalLayout();
        FormLayout formLayout2Col = new FormLayout();
        MultiSelectComboBox<Genero> selectGenero = new MultiSelectComboBox<>("Genero"); 
        MultiSelectComboBox<NivelEstudios> selectNivelEstudios = new MultiSelectComboBox<>("Nivel Estudios"); 
        MultiSelectComboBox<SituacionLaboral> selectSituacionLaboral = new MultiSelectComboBox<>("Situacion Laboral");
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");

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
        Personalizacion.configurarBoton(siguienteButton, "seleccionar-encuestas");
        Personalizacion.configurarBoton(cancelarButton, "");
        Personalizacion.configurarBoton(atrasButton, "crear-campanya");
        
        //Añadir componentes al layout
        getContent().add(tituloLayout);
        tituloLayout.add(atrasButton);
        tituloLayout.add(h3);
        getContent().add(filtrosLayout);
        filtrosLayout.add(selectGenero);
        filtrosLayout.add(selectNivelEstudios);
        filtrosLayout.add(selectSituacionLaboral);
        getContent().add(gridUsuario);
        getContent().add(formLayout2Col);
        formLayout2Col.add(siguienteButton);
        formLayout2Col.add(cancelarButton);
    }

    private List<Usuario> crearListaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("Ruben", "Garcia Crespo", Genero.HOMBRE, SituacionLaboral.OTRO, NivelEstudios.BACHILLERATO, "550-1000", "Situacion Personal"));
        usuarios.add(new Usuario("Juan", "Cuesta", Genero.HOMBRE, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.MASTER, "2300-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Maria", "Gomez Perez", Genero.MUJER, SituacionLaboral.ASALARIADO, NivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Lucia", "Sanchez Garcia", Genero.MUJER, SituacionLaboral.OTRO, NivelEstudios.MASTER, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Luffy", "Monkey D", Genero.NO_BINARIO, SituacionLaboral.PARO, NivelEstudios.ESO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Ben", "Tennison", Genero.HOMBRE, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.DESCONOCIDO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Sanji", "Vinsmoke", Genero.NO_BINARIO, SituacionLaboral.ASALARIADO, NivelEstudios.DOCTORADO, "2500-3300", "Situacion Personal"));
        usuarios.add(new Usuario("Bad", "Gyal", Genero.MUJER, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.GRADO_UNIVERSITARIO, "1900-2400", "Situacion Personal"));

        return usuarios;
    }

    private void actualizarFiltros(Grid<Usuario> grid){
        usuariosFiltrados.clear();
        usuariosFiltrados.addAll(usuarios.stream()
        .filter(user -> seleccionGeneros.isEmpty() || seleccionGeneros.contains(user.getGenero()))
        .filter(user -> seleccionEstudios.isEmpty() || seleccionEstudios.contains(user.getNivelEstudios()))
        .filter(user -> seleccionLaboral.isEmpty() || seleccionLaboral.contains(user.getSituacionLaboral()))
        .collect(Collectors.toList()));

        grid.setItems(usuariosFiltrados);
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
