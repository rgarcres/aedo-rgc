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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
//import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import java.util.ArrayList;
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

    public SeleccionarUsuariosView() {
        
        
        H3 h3 = new H3("Seleccionar Usuarios");
        Grid<Usuario> gridUsuario = new Grid<>(Usuario.class);
        FormLayout formLayout2Col = new FormLayout();
        MultiSelectComboBox<Genero> selectGenero = new MultiSelectComboBox<>("Genero"); 
        MultiSelectComboBox<NivelEstudios> selectNivelEstudios = new MultiSelectComboBox<>("Nivel Estudios"); 
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");

        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setWidth("max-content");
        selectGenero.setItems(Genero.DESCONOCIDO, Genero.HOMBRE, Genero.MUJER, Genero.NO_BINARIO, Genero.OTRO);
        selectNivelEstudios.setItems(NivelEstudios.DESCONOCIDO, NivelEstudios.ESO, NivelEstudios.BACHILLERATO, NivelEstudios.FORMACION_PROFESIONAL, NivelEstudios.GRADO_UNIVERSITARIO, NivelEstudios.MASTER, NivelEstudios.DOCTORADO, NivelEstudios.OTRO);

        gridUsuario.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUsuario.setWidth("100%");
        gridUsuario.getStyle().set("flex-grow", "0");
        List<Usuario> usuarios = crearListaUsuarios();
        gridUsuario.setItems(usuarios);

        selectGenero.addValueChangeListener(e -> {
            Set<Genero> generosSeleccionados = e.getValue();
            filtrarGenero(gridUsuario, generosSeleccionados, usuarios);
        });

        selectNivelEstudios.addValueChangeListener(e -> {
            Set<NivelEstudios> nivelesSeleccionados = e.getValue();
            filtrarNivelEstudios(gridUsuario, nivelesSeleccionados, usuarios);
        });

        formLayout2Col.setWidth("100%");

        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Personalizacion.configurarBoton(siguienteButton, "seleccionar-encuestas");
        Personalizacion.configurarBoton(cancelarButton, "");
        Personalizacion.configurarBoton(atrasButton, "crear-campanya");
        
        getContent().add(atrasButton);
        getContent().add(h3);
        getContent().add(selectGenero);
        getContent().add(selectNivelEstudios);
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
        usuarios.add(new Usuario("Luffy", "Monkey D", Genero.HOMBRE, SituacionLaboral.PARO, NivelEstudios.ESO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Ben", "Tennison", Genero.HOMBRE, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.DESCONOCIDO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Sanji", "Vinsmoke", Genero.NO_BINARIO, SituacionLaboral.ASALARIADO, NivelEstudios.DOCTORADO, "2500-3300", "Situacion Personal"));
        usuarios.add(new Usuario("Bad", "Gyal", Genero.MUJER, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.GRADO_UNIVERSITARIO, "1900-2400", "Situacion Personal"));

        return usuarios;
    }

    private void filtrarGenero(Grid<Usuario> grid, Set<Genero> generosSeleccionados, List<Usuario> usuarios){
        
        if(generosSeleccionados == null || generosSeleccionados.isEmpty()){
            grid.setItems(usuarios);
        } else {
            List<Usuario> filtrados = usuarios.stream()
            .filter(usuario -> generosSeleccionados.contains(usuario.getGenero()))
            .collect(Collectors.toList());
            
            grid.setItems(filtrados);
        }
        //grid.setSizeFull();
    }

    private void filtrarNivelEstudios(Grid<Usuario> grid, Set<NivelEstudios> nivelesSeleccionados, List<Usuario> usuarios) {
        if(nivelesSeleccionados == null || nivelesSeleccionados.isEmpty()){
            grid.setItems(usuarios);
        } else {
            List<Usuario> filtrados = usuarios.stream()
            .filter(usuario -> nivelesSeleccionados.contains(usuario.getNivelEstudios()))
            .collect(Collectors.toList());

            grid.setItems(filtrados);
        }
    }

    /*
    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
     */
}
