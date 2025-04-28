package com.example.application.views.editarcampanya;

import com.example.application.data.Bloque;
import com.example.application.data.Campanya;
import com.example.application.data.Region;
import com.example.application.views.utilidades.BotonesCreator;
import com.example.application.views.utilidades.ListaCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.time.LocalDate;
import java.util.List;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Editar Campaña")
@Route("editar-campanya")
@Menu(order = 3, icon = LineAwesomeIconUrl.USER)
public class EditarCampanyaView extends Composite<VerticalLayout> {
    
    @SuppressWarnings("unchecked")
    private final List<Campanya> listaCamps = (List<Campanya>) VaadinSession.getCurrent().getAttribute("listaCamps");
    private Campanya campEdit = (Campanya) VaadinSession.getCurrent().getAttribute("campEdit");
    private Campanya campMedioEditada = (Campanya) VaadinSession.getCurrent().getAttribute("campMedioEditada");
    
    public EditarCampanyaView() {
    //--------------Inicialización--------------
        //--------------Layouts--------------
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout botonesFinalLayout = new HorizontalLayout();
        FormLayout camposLayout = new FormLayout();
        //--------------Encabezados--------------
        H3 h3 = new H3("Editar Campañas");
        H4 error = new H4("Selecciona todos los campos obligatorios");
        //--------------Campos a rellenar--------------
        TextField textFieldNombre = new TextField("Nombre*");
        TextField textFieldID = new TextField("ID (Debe ser único)*");
        DatePicker datePickerInicio = new DatePicker("Inicio*");
        DatePicker datePickerFin = new DatePicker("Fin*");
        ComboBox<Region> regionComboBox = new ComboBox<>("Region*");
        ComboBox<Bloque> bloqueComboBox = new ComboBox<>("Bloque*");
        TextField textFieldObjetivos = new TextField("Objetivos");
        TextField textFieldDemografia = new TextField("Demografia");
        //--------------Botones--------------
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");

        configurarLayout(mainLayout);
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, h3);
        h3.setWidth("100%");
        camposLayout.setWidth("100%");
        datePickerFin.setWidth("min-content");

        regionComboBox.setWidth("min-content");
        setComboBoxRegion(regionComboBox);
        bloqueComboBox.setWidth("min-content");
        setComboBoxBloque(bloqueComboBox);

        textFieldObjetivos.setWidth("100%");
        textFieldDemografia.setWidth("100%");
        botonesFinalLayout.addClassName(Gap.MEDIUM);
        botonesFinalLayout.setWidth("100%");
        botonesFinalLayout.getStyle().set("flex-grow", "1");
        botonesFinalLayout.setAlignItems(Alignment.CENTER);
        botonesFinalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(siguienteButton);
        BotonesCreator.configurarBoton(cancelarButton, "home");

        getContent().add(mainLayout);
        mainLayout.add(h3);
        mainLayout.add(camposLayout);
        camposLayout.add(textFieldNombre);
        camposLayout.add(textFieldID);
        camposLayout.add(datePickerInicio);
        camposLayout.add(datePickerFin);
        camposLayout.add(regionComboBox);
        camposLayout.add(bloqueComboBox);
        mainLayout.add(textFieldObjetivos);
        mainLayout.add(textFieldDemografia);
        mainLayout.add(botonesFinalLayout);
        botonesFinalLayout.add(siguienteButton);
        botonesFinalLayout.add(cancelarButton);

        if(campMedioEditada != null){
            textFieldNombre.setValue(campMedioEditada.getNombre());
            textFieldID.setValue(campMedioEditada.getId().toString());
            datePickerInicio.setValue(campMedioEditada.getInicio());
            datePickerFin.setValue(campMedioEditada.getFin());
            regionComboBox.setValue(campMedioEditada.getRegion());
            bloqueComboBox.setValue(campMedioEditada.getBloque());
            textFieldObjetivos.setValue(campMedioEditada.getObjetivos());
            textFieldDemografia.setValue(campMedioEditada.getDemografia());   
        } else if(campEdit != null){
            textFieldNombre.setValue(campEdit.getNombre());
            textFieldID.setValue(campEdit.getId().toString());
            datePickerInicio.setValue(campEdit.getInicio());
            datePickerFin.setValue(campEdit.getFin());
            regionComboBox.setValue(campEdit.getRegion());
            bloqueComboBox.setValue(campEdit.getBloque());
            textFieldObjetivos.setValue(campEdit.getObjetivos());
            textFieldDemografia.setValue(campEdit.getDemografia());
        } 

        siguienteButton.addClickListener(e -> {
            String nombre = textFieldNombre.getValue();
            String ID = textFieldID.getValue();
            LocalDate inicio = datePickerInicio.getValue();
            LocalDate fin = datePickerFin.getValue();
            Bloque bloque = bloqueComboBox.getValue();
            Region region = regionComboBox.getValue();
            String objetivos = textFieldObjetivos.getValue();
            String demografia = textFieldDemografia.getValue();

            if(comprobarCamposCompletos(ID, nombre, inicio, fin, bloque, region)) {
                listaCamps.remove(campEdit);
                Campanya camp = new Campanya(Long.parseLong(ID), nombre, objetivos, demografia, inicio, fin, region, bloque);
                listaCamps.add(camp);
                VaadinSession.getCurrent().setAttribute("campMedioEditada", camp);
                VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
                getUI().ifPresent(ui -> ui.navigate("seleccionar-grupo"));
            } else {
                getContent().add(error);
            }        
        });
    }

    private void setComboBoxBloque(ComboBox<Bloque> comboBox){
        comboBox.setItems(ListaCreator.crearListaBloques());
        comboBox.setItemLabelGenerator(item -> ((Bloque)item).getNombre());
    }

    private void setComboBoxRegion(ComboBox<Region> comboBox){
        comboBox.setItems(ListaCreator.crearListaRegiones());
        comboBox.setItemLabelGenerator(item -> ((Region)item).getNombre());
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
    }

    private boolean comprobarCamposCompletos(String ID, String nombre, LocalDate inicio, LocalDate fin, Bloque b, Region r){
        if(nombre.isBlank()){
            return false;
        }
        if(inicio == null || fin == null){
            return false;
        }
        if(inicio.isAfter(fin)){
            return false;
        }
        if(b == null){
            return false;
        }
        if(r == null){
            return false;
        }
        //Comprobar ID: tiene formato numérico
        //Como en esta página se está editando, no hay que comprobar que
        //el ID esté ya en lista para devolver false
        try {
            Long.parseLong(ID);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
