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

//import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Seleccionar Usuarios")
@Route("seleccionar-usuarios")
@Menu(order = 3, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
@Uses(Icon.class)
public class SeleccionarUsuariosView extends Composite<VerticalLayout> {

    public SeleccionarUsuariosView() {
        H3 h3 = new H3();
        Grid<Usuario> gridUsuario = new Grid<>(Usuario.class);
        FormLayout formLayout2Col = new FormLayout();
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        Button atrasButton = new Button("<");
        getContent().setHeightFull();
        getContent().setWidthFull();
        h3.setText("Seleccionar Usuarios");
        h3.setWidth("max-content");
        gridUsuario.setSelectionMode(Grid.SelectionMode.MULTI);
        gridUsuario.setWidth("100%");
        gridUsuario.getStyle().set("flex-grow", "0");
        setGridUsuario(gridUsuario);
        formLayout2Col.setWidth("100%");
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Personalizacion.configurarBoton(siguienteButton, "seleccionar-encuestas");
        Personalizacion.configurarBoton(cancelarButton, "");
        Personalizacion.configurarBoton(atrasButton, "crear-campanya");
        
        getContent().add(atrasButton);
        getContent().add(h3);
        getContent().add(gridUsuario);
        getContent().add(formLayout2Col);
        formLayout2Col.add(siguienteButton);
        formLayout2Col.add(cancelarButton);
    }

    /*
    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(VaadinSpringDataHelpers.toSpringPageRequest(query)).stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
     */

     private void setGridUsuario(Grid<Usuario> grid){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Ruben", "Garcia Crespo", Genero.HOMBRE, SituacionLaboral.OTRO, NivelEstudios.BACHILLERATO, "550-1000", "Situacion Personal"));
        usuarios.add(new Usuario("Juan", "Cuesta", Genero.HOMBRE, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.MASTER, "2300-3000", "Situacion Personal"));
        usuarios.add(new Usuario("Maria", "Gomez Perez", Genero.MUJER, SituacionLaboral.ASALARIADO, NivelEstudios.GRADO_UNIVERSITARIO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Lucia", "Sanchez Garcia", Genero.MUJER, SituacionLaboral.OTRO, NivelEstudios.MASTER, "1800-2100", "Situacion Personal"));
        usuarios.add(new Usuario("Luffy", "Monkey D", Genero.HOMBRE, SituacionLaboral.PARO, NivelEstudios.ESO, "0", "Situacion Personal"));
        usuarios.add(new Usuario("Ben", "Tennison", Genero.HOMBRE, SituacionLaboral.TIEMPO_PARCIAL, NivelEstudios.DESCONOCIDO, "1200-1600", "Situacion Personal"));
        usuarios.add(new Usuario("Sanji", "Vinsmoke", Genero.NO_BINARIO, SituacionLaboral.ASALARIADO, NivelEstudios.DOCTORADO, "2500-3300", "Situacion Personal"));
        usuarios.add(new Usuario("Bad", "Gyal", Genero.MUJER, SituacionLaboral.TIMEPO_TOTAL, NivelEstudios.GRADO_UNIVERSITARIO, "1900-2400", "Situacion Personal"));

        grid.setItems(usuarios);
        //grid.setSizeFull();
     }
}
