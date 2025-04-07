package com.example.application.views.crearcampanya;

import com.example.application.data.Bloque;
import com.example.application.data.Campanya;
import com.example.application.data.Grupo;
import com.example.application.data.Region;
import com.example.application.views.utilidades.BotonesCreator;
import com.example.application.views.utilidades.ListaCreator;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");

    public CrearCampanyaView() {
        //-----------Inicializar componentes-----------
        //Layouts
        VerticalLayout mainLayout = new VerticalLayout();
        FormLayout camposObligatoriosLayout = new FormLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        //Cabeceras
        H3 h3 = new H3("Crear Campañas");
        H4 errorMsg = new H4("Selecciona todos los campos obligatorios");
        //Campos a rellenar
        TextField textFieldNombre = new TextField("Nombre*");
        TextField textFieldID = new TextField("ID (Debe ser único)*");
        DatePicker datePickerInicio = new DatePicker("Inicio*");
        DatePicker datePickerFin = new DatePicker("Fin*");
        ComboBox<Region> regionComboBox = new ComboBox<>("Region*");
        ComboBox<Bloque> bloqueComboBox = new ComboBox<>("Bloque*");
        TextField textFieldObjetivos = new TextField("Objetivos");
        TextField textFieldDemografia = new TextField("Demografia");
        //Botones
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        mainLayout.setWidth("100%");
        mainLayout.setMaxWidth("800px");
        mainLayout.setHeight("min-content");
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);
        mainLayout.setAlignSelf(FlexComponent.Alignment.CENTER, h3);
        h3.setWidth("100%");
        camposObligatoriosLayout.setWidth("100%");
        datePickerFin.setWidth("min-content");

        /*
         * Establece la fecha mínima que se permite escoger
         * Inicio: la fecha de hoy
         * Fin: la fecha de inicio
         */
        datePickerInicio.setMin(LocalDate.now());
        datePickerInicio.addValueChangeListener(e -> {
            if(datePickerInicio.getValue() != null){
                datePickerFin.setMin(datePickerInicio.getValue());
            }
        });

        regionComboBox.setWidth("min-content");
        regionComboBox.setItems(ListaCreator.crearListaRegiones());
        regionComboBox.setItemLabelGenerator(item -> item.getNombre());
        bloqueComboBox.setWidth("min-content");
        bloqueComboBox.setItems(ListaCreator.crearListaBloques());
        bloqueComboBox.setItemLabelGenerator(item -> item.getNombre());

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

        //-----------Llenar campos-----------
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

        //-----------Añadir componentes a la vista-----------
        getContent().add(mainLayout);
        mainLayout.add(h3);
        mainLayout.add(camposObligatoriosLayout);
        camposObligatoriosLayout.add(textFieldNombre);
        camposObligatoriosLayout.add(textFieldID);
        camposObligatoriosLayout.add(datePickerInicio);
        camposObligatoriosLayout.add(datePickerFin);
        camposObligatoriosLayout.add(regionComboBox);
        camposObligatoriosLayout.add(bloqueComboBox);
        mainLayout.add(textFieldObjetivos);
        mainLayout.add(textFieldDemografia);
        mainLayout.add(layoutRow);
        layoutRow.add(siguienteButton);
        layoutRow.add(cancelarButton);

        //-----------Comportamiento de botones-----------
        siguienteButton.addClickListener(e -> {
            String nombre = textFieldNombre.getValue();
            String ID = textFieldID.getValue();
            LocalDate inicio = datePickerInicio.getValue();
            LocalDate fin = datePickerFin.getValue();
            Bloque bloque = bloqueComboBox.getValue();
            Region region = regionComboBox.getValue();
            String objetivos = textFieldObjetivos.getValue();
            String demografia = textFieldDemografia.getValue();

            if(!comprobarCamposCompletos(nombre, inicio, fin, bloque, region)) {
                getContent().add(errorMsg);
            } else if(!comprobarID(ID, errorMsg)){
                getContent().add(errorMsg);
            } else if(listaGrupos.isEmpty() || listaGrupos == null){
                notificarGruposVacios();
            } else {
                Campanya camp = new Campanya(Long.parseLong(ID), nombre, objetivos, demografia, inicio, fin, region, bloque);
                listaCamps.add(camp);
                VaadinSession.getCurrent().setAttribute("campMedioCreada", camp);
                VaadinSession.getCurrent().setAttribute("bloqueSelec", bloque);
                VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
                getUI().ifPresent(ui -> ui.navigate("seleccionar-grupo"));
            }        
        });
    }

    /*
     * Crea una notificación que avisa de que hace falta crear una grupo para poder
     * crear una campaña. 
     */
    private void notificarGruposVacios(){
        Notification errorGrupos = new Notification();
        Button cerrarButton = new Button("Cerrar");
        H3 tituloError = new H3("No hay ningún grupo creado");
        Div texto = new Div("Crea un grupo antes de crear una campaña");
        VerticalLayout layout = new VerticalLayout();

        layout.setAlignItems(Alignment.CENTER);
        tituloError.getStyle().set("font-weight", "bold").set("color", "black");
        BotonesCreator.configurarBoton(cerrarButton);

        cerrarButton.addClickListener(e -> {
            errorGrupos.close();
        });

        layout.add(tituloError, texto, cerrarButton);
        errorGrupos.addThemeVariants(NotificationVariant.LUMO_ERROR);
        errorGrupos.add(layout);
        errorGrupos.open();
    }

    //Comprueba que todos los campos introducidos son correctos y no están vacíos
    private boolean comprobarCamposCompletos(String nombre, LocalDate inicio, LocalDate fin, Bloque b, Region r){
        if(nombre.isBlank()){
            return false;
        }
        if(inicio == null || fin == null){
            return false;
        }
        if(b == null){
            return false;
        }
        if(r == null){
            return false;
        }

        return true;
    }

    //Comprobar ID: tiene formato numérico y es único
    private boolean comprobarID(String ID, H4 errorMsg){
        try {
    
            Long id = Long.parseLong(ID);

            for(Campanya c: listaCamps){
                if(c.getId() == id){
                    errorMsg.setText("El ID debe ser único");
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException nfe){
            errorMsg.setText("El ID debe tener formato numérico");
            return false;
        }
    }
}
