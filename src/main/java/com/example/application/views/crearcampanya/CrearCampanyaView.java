package com.example.application.views.crearcampanya;

import com.example.application.data.Bloque;
import com.example.application.data.Campanya;
import com.example.application.data.Region;
import com.example.application.views.Utilidades;
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

@PageTitle("Crear Campanya")
@Route("crear-campanya")
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
public class CrearCampanyaView extends Composite<VerticalLayout> {

    @SuppressWarnings("unchecked")
    private final List<Campanya> listaCamps = (List<Campanya>) VaadinSession.getCurrent().getAttribute("listaCamps");
    private final Campanya campMedioCreada = (Campanya) VaadinSession.getCurrent().getAttribute("campMedioCreada");

    public CrearCampanyaView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3("Crear Campañas");
        H4 error = new H4("Selecciona todos los campos obligatorios");
        FormLayout camposObligatoriosLayout = new FormLayout();
        TextField textFieldNombre = new TextField("Nombre*");
        TextField textFieldID = new TextField("ID (Debe ser único)*");
        DatePicker datePickerInicio = new DatePicker("Inicio*");
        DatePicker datePickerFin = new DatePicker("Fin*");
        ComboBox<Region> regionComboBox = new ComboBox<>("Region*");
        ComboBox<Bloque> bloqueComboBox = new ComboBox<>("Bloque*");
        TextField textFieldObjetivos = new TextField("Objetivos");
        TextField textFieldDemografia = new TextField("Demografia");
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.CENTER);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h3);
        h3.setWidth("100%");
        camposObligatoriosLayout.setWidth("100%");
        datePickerFin.setWidth("min-content");

        regionComboBox.setWidth("min-content");
        regionComboBox.setItems(Utilidades.crearListaRegiones());
        regionComboBox.setItemLabelGenerator(item -> item.getNombre());
        bloqueComboBox.setWidth("min-content");
        bloqueComboBox.setItems(Utilidades.crearListaBloques());
        bloqueComboBox.setItemLabelGenerator(item -> item.getNombre());

        textFieldObjetivos.setWidth("100%");
        textFieldDemografia.setWidth("100%");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Utilidades.configurarBoton(siguienteButton);
        Utilidades.configurarBoton(cancelarButton, "");

        if(campMedioCreada != null){
            textFieldNombre.setValue(campMedioCreada.getNombre());
            textFieldID.setValue(campMedioCreada.getId().toString());
            datePickerInicio.setValue(campMedioCreada.getInicio());
            datePickerFin.setValue(campMedioCreada.getFin());
            regionComboBox.setValue(campMedioCreada.getRegion());
            bloqueComboBox.setValue(campMedioCreada.getBloque());
            if(!campMedioCreada.getObjetivos().isBlank()){
                textFieldObjetivos.setValue(campMedioCreada.getObjetivos());
            }
            if(!campMedioCreada.getDemografia().isBlank()){
                textFieldDemografia.setValue(campMedioCreada.getDemografia());
            }
        }

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(camposObligatoriosLayout);
        camposObligatoriosLayout.add(textFieldNombre);
        camposObligatoriosLayout.add(textFieldID);
        camposObligatoriosLayout.add(datePickerInicio);
        camposObligatoriosLayout.add(datePickerFin);
        camposObligatoriosLayout.add(regionComboBox);
        camposObligatoriosLayout.add(bloqueComboBox);
        layoutColumn2.add(textFieldObjetivos);
        layoutColumn2.add(textFieldDemografia);
        layoutColumn2.add(layoutRow);
        layoutRow.add(siguienteButton);
        layoutRow.add(cancelarButton);
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
                Campanya camp = new Campanya(Long.parseLong(ID), nombre, objetivos, demografia, inicio, fin, region, bloque);
                listaCamps.add(camp);
                VaadinSession.getCurrent().setAttribute("campMedioCreada", camp);
                VaadinSession.getCurrent().setAttribute("bloqueSelec", bloque);
                VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
                getUI().ifPresent(ui -> ui.navigate("seleccionar-usuarios"));
            } else {
                getContent().add(error);
            }        
        });
    }

    //Comprueba que todos los campos introducidos son correctos y no están vacíos
    private boolean comprobarCamposCompletos(String ID, String nombre, LocalDate inicio, LocalDate fin, Bloque b, Region r){
        if(nombre.isBlank()){
            return false;
        }
        if(inicio == null || fin == null){
            return false;
        }
        //Comprobar que la fecha de inicio es anterior a la de fin
        if(inicio.isAfter(fin)){
            return false;
        }
        if(b == null){
            return false;
        }
        if(r == null){
            return false;
        }
        
        //Comprobar ID: tiene formato numérico y es único
        try {
            
            Long id = Long.parseLong(ID);

            for(Campanya c: listaCamps){
                if(c.getId() == id){
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
}
