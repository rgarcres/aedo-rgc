package com.example.application.views.seleccionargrupo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.example.application.data.Campanya;
import com.example.application.data.Grupo;
import com.example.application.views.utilidades.BotonesCreator;
import com.example.application.views.utilidades.Utilidades;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Seleccionar Grupo")
@Route("seleccionar-grupo")
@Menu(order=3, icon = LineAwesomeIconUrl.ADJUST_SOLID)
public class SeleccionarGrupoView extends Composite<VerticalLayout>{

    @SuppressWarnings("unchecked")
    private final List<Campanya> listaCamps = (List<Campanya>) VaadinSession.getCurrent().getAttribute("listaCamps");

    @SuppressWarnings("unchecked")
    private final List<Grupo> listaGrupos = (List<Grupo>) VaadinSession.getCurrent().getAttribute("listaGrupos");
    private final List<Grupo> gruposFiltrados = new ArrayList<>();
    private final Campanya campMedioCreada = (Campanya) VaadinSession.getCurrent().getAttribute("campMedioCreada");
    private final Campanya campEdit = (Campanya) VaadinSession.getCurrent().getAttribute("campEdit");
    private final Campanya campMedioEditada = (Campanya) VaadinSession.getCurrent().getAttribute("campMedioEditada");

    private String nombreFiltro;
    private Long idFiltro;

    public SeleccionarGrupoView(){
        //----Crear componentes----
        //Layouts
        VerticalLayout mainLayout = new VerticalLayout();
        HorizontalLayout filtrosLayout = new HorizontalLayout();
        FormLayout botonesLayout = new FormLayout();
        //Cabeceras
        H3 titulo = new H3("Seleccionar Grupo");
        H4 errorMsg = new H4();
        errorMsg.getStyle().set("color", "#ff4e4e");
        //Campos de texto
        TextField nombreTextField = new TextField("Nombre");
        TextField idTextField = new TextField("ID");
        //Grid
        Grid<Grupo> gridGrupos = new Grid<>();
        //Botones
        Button atrasButton = new Button("<");
        Button siguienteButton = new Button("Siguiente");
        Button cancelarButton = new Button("Cancelar");
        Button buscarButton = new Button("Buscar");
        Button limpiarButton = new Button("Limpiar");

        //---------Configurar Layout---------
        configurarLayout(mainLayout);
        filtrosLayout.setWidth("100%");
        titulo.setWidth("100%");
        gridGrupos.setWidth("100%");
        botonesLayout.setWidth("100%");

        //---------Configurar Grid---------
        configurarGrid(gridGrupos);
        if(campMedioCreada != null && !campMedioCreada.getGrupos().isEmpty()){
            gruposFiltrados.addAll(campMedioCreada.getGrupos());
        } else if (campMedioEditada != null && !campMedioEditada.getGrupos().isEmpty()){
            gruposFiltrados.addAll(campMedioEditada.getGrupos());
        } else if (campEdit != null){
            gruposFiltrados.addAll(campEdit.getGrupos());
        } else {
            gruposFiltrados.addAll(listaGrupos);
        }
        gridGrupos.setItems(gruposFiltrados);

        //---------Configurar botones---------
        if(campEdit == null){
            BotonesCreator.configurarBoton(atrasButton, "crear-campanya");
        } else {
            BotonesCreator.configurarBoton(atrasButton, "editar-campanya");
        }

        siguienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(siguienteButton);

        BotonesCreator.configurarBoton(cancelarButton, "home");

        buscarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        BotonesCreator.configurarBoton(buscarButton);

        BotonesCreator.configurarBoton(limpiarButton);

        //---------Comportamiento de filtros---------
        //Nombre
        nombreTextField.addKeyPressListener(Key.ENTER, e ->{
            nombreFiltro = nombreTextField.getValue();
            if(Utilidades.esNumero(idTextField.getValue())){
                idFiltro = Long.parseLong(idTextField.getValue());
            } else {
                errorMsg.setText("El ID debe ser numérico");
            }
            aplicarFiltros(gridGrupos);
        });
        //ID
        idTextField.addKeyPressListener(Key.ENTER, e -> {
            nombreFiltro = nombreTextField.getValue();
            if(Utilidades.esNumero(idTextField.getValue())){
                idFiltro = Long.parseLong(idTextField.getValue());
            } else {
                errorMsg.setText("El ID debe ser numérico");
            }
            aplicarFiltros(gridGrupos);
        });

        //---------Comportamiento de botones---------
        //Atras
        atrasButton.addClickListener(e -> {
            listaCamps.remove(campMedioCreada);
        });
        //Buscar
        buscarButton.addClickListener(e -> {
            nombreFiltro = nombreTextField.getValue();
            if(Utilidades.esNumero(idTextField.getValue())){
                idFiltro = Long.parseLong(idTextField.getValue());
            } else {
                errorMsg.setText("El ID debe ser numérico");
            }
            aplicarFiltros(gridGrupos);
        });
        //Limpiar
        limpiarButton.addClickListener(e -> {
            gruposFiltrados.addAll(gruposFiltrados);
            nombreTextField.clear();
            idTextField.clear();
            nombreFiltro = "";
            idFiltro = null;
            aplicarFiltros(gridGrupos);
        });
        //Siguiente
        siguienteButton.addClickListener(e -> {
            Set<Grupo> seleccionados = gridGrupos.getSelectedItems();
            if(seleccionados.isEmpty()){
                errorMsg.setText("Selecciona un grupo");
            } else {
                listaCamps.getLast().setGrupos(new ArrayList<>(seleccionados));

                if(campEdit == null){
                    VaadinSession.getCurrent().setAttribute("campMedioCreada", listaCamps.getLast());
                } else {
                    VaadinSession.getCurrent().setAttribute("campMedioEditada", listaCamps.getLast());
                }
                getUI().ifPresent(ui -> ui.navigate("seleccionar-preguntas"));
            }
        });
        //Cancelar
        cancelarButton.addClickListener(e -> {
            listaCamps.removeLast();
            if(campEdit != null){
                listaCamps.add(campEdit);
            }
            VaadinSession.getCurrent().setAttribute("campEdit", null);
            VaadinSession.getCurrent().setAttribute("campMedioCreada", null);
            VaadinSession.getCurrent().setAttribute("campMedioEditada", null);
            VaadinSession.getCurrent().setAttribute("listaCamps", listaCamps);
        });

        //---------Añadir componentes a layaout---------
        mainLayout.add(filtrosLayout);
        filtrosLayout.add(atrasButton);
        filtrosLayout.add(nombreTextField);
        filtrosLayout.add(idTextField);
        filtrosLayout.add(buscarButton);
        filtrosLayout.add(limpiarButton);
        mainLayout.add(gridGrupos);
        mainLayout.add(botonesLayout);
        mainLayout.add(errorMsg);
        botonesLayout.add(siguienteButton);
        botonesLayout.add(cancelarButton);
        
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

    /*
     * Realiza el filtrado de los grupos que se van a mostrar
     * en el grid
     */
    private void aplicarFiltros(Grid<Grupo> grid){
        //Limpiar lista de grupos
        gruposFiltrados.clear();

        //Aplicar los filtros
        gruposFiltrados.addAll(listaGrupos.stream()
        .filter(grupo -> nombreFiltro.isBlank() || Utilidades.buscarCoincidencias(grupo.getNombre(), nombreFiltro))
        .filter(grupo -> idFiltro == null || idFiltro == grupo.getId())
        .collect(Collectors.toList()));

        //Agrega los grupos que se han seleccionado
        grid.setItems(gruposFiltrados);
    }

    /*
     * Estiliza en grid y añade las columnas
     */
    private void configurarGrid(Grid<Grupo> grid) {
        //Ajustar tamaño
        grid.setWidth("100%");      
        grid.getStyle().set("flex-grow", "0");

        //Permitir la selección multiple
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        //Establecer las columnas
        grid.addColumn(Grupo::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Grupo::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Grupo::getDescripcion).setHeader("Descripcion").setAutoWidth(true);
        grid.addColumn(g -> g.getUsuarios()).setHeader("Usuarios").setAutoWidth(true);
    }
}
