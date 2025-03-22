package com.example.application.views.crearcampanya;

import com.example.application.data.Bloque;
import com.example.application.data.Region;
import com.example.application.views.Personalizacion;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Crear Campanya")
@Route("crear-campanya")
@Menu(order = 2, icon = LineAwesomeIconUrl.USER)
public class CrearCampanyaView extends Composite<VerticalLayout> {

    public CrearCampanyaView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        ComboBox<Region> regionComboBox = new ComboBox<>("Region");
        ComboBox<Bloque> bloqueComboBox = new ComboBox<>("Bloque");
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
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
        h3.setText("Crear Campañas");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, h3);
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        textField.setLabel("Nombre");
        textField2.setLabel("ID (Debe ser único)");
        datePicker.setLabel("Inicio");
        datePicker2.setLabel("Fin");
        datePicker2.setWidth("min-content");

        regionComboBox.setWidth("min-content");
        setComboBoxRegion(regionComboBox);
        bloqueComboBox.setWidth("min-content");
        setComboBoxBloque(bloqueComboBox);

        textField3.setLabel("Objetivos");
        textField3.setWidth("100%");
        textField4.setLabel("Demografía");
        textField4.setWidth("100%");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Personalizacion.configurarBoton(siguienteButton, "seleccionar-usuarios");
        Personalizacion.configurarBoton(cancelarButton, "");

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(datePicker);
        formLayout2Col.add(datePicker2);
        formLayout2Col.add(regionComboBox);
        formLayout2Col.add(bloqueComboBox);
        layoutColumn2.add(textField3);
        layoutColumn2.add(textField4);
        layoutColumn2.add(layoutRow);
        layoutRow.add(siguienteButton);
        layoutRow.add(cancelarButton);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxBloque(ComboBox<Bloque> comboBox){
        List<Bloque> bloques = new ArrayList<>();
        bloques.add(new Bloque("Bloque 1"));
        bloques.add(new Bloque("Bloque 2"));
        bloques.add(new Bloque("Bloque 3"));
        bloques.add(new Bloque("Bloque 4"));
        comboBox.setItems(bloques);
        comboBox.setItemLabelGenerator(item -> ((Bloque)item).getNombre());
    }

    private void setComboBoxRegion(ComboBox<Region> comboBox){
        List<Region> regiones = new ArrayList<>();
        regiones.add(new Region("Torremolinos", "Malaga"));
        regiones.add(new Region("Alora", "Malaga"));
        regiones.add(new Region("Nerja", "Malaga"));
        regiones.add(new Region("Velez Malaga", "Malaga"));
        comboBox.setItems(regiones);
        comboBox.setItemLabelGenerator(item -> ((Region)item).getNombre());
    }
}
