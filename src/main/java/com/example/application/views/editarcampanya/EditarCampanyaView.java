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

    public EditarCampanyaView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3("Editar Campañas");
        H4 error = new H4("Selecciona todos los campos obligatorios");
        FormLayout formLayout2Col = new FormLayout();
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
        formLayout2Col.setWidth("100%");
        datePickerFin.setWidth("min-content");

        regionComboBox.setWidth("min-content");
        setComboBoxRegion(regionComboBox);
        bloqueComboBox.setWidth("min-content");
        setComboBoxBloque(bloqueComboBox);

        textFieldObjetivos.setWidth("100%");
        textFieldDemografia.setWidth("100%");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(siguienteButton);
        BotonesCreator.configurarBoton(cancelarButton, "home");

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textFieldNombre);
        formLayout2Col.add(textFieldID);
        formLayout2Col.add(datePickerInicio);
        formLayout2Col.add(datePickerFin);
        formLayout2Col.add(regionComboBox);
        formLayout2Col.add(bloqueComboBox);
        layoutColumn2.add(textFieldObjetivos);
        layoutColumn2.add(textFieldDemografia);
        layoutColumn2.add(layoutRow);
        layoutRow.add(siguienteButton);
        layoutRow.add(cancelarButton); 

        if(campEdit != null){
            textFieldNombre.setValue(campEdit.getNombre());
            textFieldID.setValue(campEdit.getId().toString());
            datePickerInicio.setValue(campEdit.getInicio());
            datePickerFin.setValue(campEdit.getFin());
            regionComboBox.setValue(campEdit.getRegion());
            bloqueComboBox.setValue(campEdit.getBloque());
            if(!campEdit.getObjetivos().isBlank()){
                textFieldObjetivos.setValue(campEdit.getObjetivos());
            }
            if(!campEdit.getDemografia().isBlank()){
                textFieldDemografia.setValue(campEdit.getDemografia());
            }
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
