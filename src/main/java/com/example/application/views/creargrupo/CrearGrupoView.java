package com.example.application.views.creargrupo;

import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.data.Grupo;
import com.example.application.views.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Crear Grupo")
@Route("crear-grupo")
@Menu(order=2, icon = LineAwesomeIconUrl.FROG_SOLID)
public class CrearGrupoView extends Composite<VerticalLayout>{
    private String ID;
    private String nombre;
    private String descripcion;

    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");
    public CrearGrupoView(){
        VerticalLayout mainLayout = new VerticalLayout();
        FormLayout camposLayout = new FormLayout();
        HorizontalLayout botonesLayout = new HorizontalLayout();

        H3 titulo = new H3("Crear Grupo");
        H4 errorMsg = new H4("Selecciona todos los campos obligatorios");
        errorMsg.getStyle().set("color", "#ff4e4e");
        TextField nombreTextField = new TextField("Nombre*");
        TextField idTextField = new TextField("ID* (Debe ser único)");
        TextField descripcionTextField = new TextField("Descripcion");

        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Utilidades.configurarBoton(siguienteButton);
        Utilidades.configurarBoton(cancelarButton, "");

        configurarLayout(mainLayout);
        titulo.setWidth("100%");
        camposLayout.setWidth("100%");
        descripcionTextField.setWidth("100%");

        mainLayout.add(titulo);
        mainLayout.add(camposLayout);
        camposLayout.add(nombreTextField);
        camposLayout.add(idTextField);
        mainLayout.add(descripcionTextField);
        mainLayout.add(botonesLayout);
        botonesLayout.add(siguienteButton);
        botonesLayout.add(cancelarButton);
        
        siguienteButton.addClickListener(e -> {
            ID = idTextField.getValue();
            nombre = nombreTextField.getValue();
            descripcion = descripcionTextField.getValue();

            if(!comprobarID(ID, errorMsg)){
                mainLayout.add(errorMsg);
            } else if (!comprobarCamposCompletos(ID, nombre, errorMsg)){
                mainLayout.add(errorMsg);
            } else {
                Grupo grupo = new Grupo(Long.parseLong(ID), nombre, descripcion);
                listaGrupos.add(grupo);
                VaadinSession.getCurrent().setAttribute("listaGrupos", listaGrupos);
                getUI().ifPresent(ui -> ui.navigate("seleccionar-usuarios"));
            }
        });

    }

    private boolean comprobarCamposCompletos(String ID, String nombre, H4 errorMsg) {
        if(ID == null){
            errorMsg.setText("El ID no puede estar vacío");
            return false;
        }

        if(nombre.isBlank()){
            errorMsg.setText("El nombre no puede estar vacío");
            return false;
        }
        return true;
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

    private boolean comprobarID(String ID, H4 errorMsg){
        try {
            Long id = Long.parseLong(ID);

            for(Grupo g: listaGrupos){
                if(g.getId() == id){
                    return false;
                }
            }
            return true;
        } catch(NumberFormatException nfe){
            errorMsg.setText("El ID debe tener formato numérico");
            return false;
        }
    }
}
