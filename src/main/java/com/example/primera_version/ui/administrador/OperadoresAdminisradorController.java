package com.example.primera_version.ui.administrador;

import com.example.primera_version.Main;
import com.example.primera_version.business.TurOpMgr;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.ui.Principal;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class OperadoresAdminisradorController implements Initializable{


    @Autowired
    private TurOpMgr turOpMgr;

    @Autowired
    private Principal principal;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private Button volverMenu;

    @FXML
    private Button cerrarSesion;

    @FXML
    private Button agregarOperador;

    @FXML
    private TableView<OperadorTuristico> operadoresExpuestos;

    @FXML
    private TableColumn<OperadorTuristico, String> nombreOperador;

    @FXML
    private TableColumn<OperadorTuristico, String> razonSocial;

    @FXML
    private TableColumn<OperadorTuristico, String> idOperadorTuristico;

    @FXML
    private TableColumn<OperadorTuristico, String> nombreContacto;

    @FXML
    private TableColumn<OperadorTuristico, String> apellidoContacto;

    @FXML
    private TableColumn<OperadorTuristico, String> edadContacto;

    @FXML
    private TableColumn<OperadorTuristico, String> telefonoContacto;

    @FXML
    private TableColumn<OperadorTuristico, Boolean> estaDisponible;

    ObservableList<OperadorTuristico> lista;



    @FXML
    void busquedaDinamica(KeyEvent event){
        List<OperadorTuristico> query = (List<OperadorTuristico>) turOpMgr.encontrarTodosPorContenidoNombre(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        operadoresExpuestos.setItems(lista);
        operadoresExpuestos.sort();
        /*
        nombreOperador.setCellValueFactory(new PropertyValueFactory<>("nameTO"));
        razonSocial.setCellValueFactory(cellData -> {
            Long idOperador = cellData.getValue().getIdOpTur();
            String razSoc = turOpRepository.findOneByIdOpTur(idOperador).getRazonSocial();
            return new ReadOnlyStringWrapper(razSoc);
        });

        idOperadorTuristico.setCellValueFactory(cellData -> {
            Long idOp = cellData.getValue().getIdOpTur();
            return new ReadOnlyStringWrapper(String.valueOf(idOp));
        });

        nombreContacto.setCellValueFactory(cellData -> {
            Long idOperador = cellData.getValue().getIdOpTur();
            String nombContacto = turOpRepository.findOneByIdOpTur(idOperador).getCantact_name();
            return new ReadOnlyStringWrapper(nombContacto);
        });
        apellidoContacto.setCellValueFactory(cellData -> {
            Long idOperador = cellData.getValue().getIdOpTur();
            String apellContacto = turOpRepository.findOneByIdOpTur(idOperador).getCantact_surname();
            return new ReadOnlyStringWrapper(apellContacto);
        });
        edadContacto.setCellValueFactory(cellData -> {
            Long idOperador = cellData.getValue().getIdOpTur();
            Integer edadCon = turOpRepository.findOneByIdOpTur(idOperador).getContact_age();
            return new ReadOnlyStringWrapper(String.valueOf(edadCon));
        });
        telefonoContacto.setCellValueFactory(cellData -> {
            Long idOperador = cellData.getValue().getIdOpTur();
            String telCont = turOpRepository.findOneByIdOpTur(idOperador).getContact_phone();
            return new ReadOnlyStringWrapper(telCont);
        });

//        estadoExperiencia.setCellValueFactory(cellData -> {
//            Long idExperiencia = cellData.getValue().getIdExperiencia();
//            Boolean estadoExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getEstadoExperiencia();
//            String estado = "Bloqueado";
//            if(estadoExperiencia == null || estadoExperiencia){
//                estado = "Autorizado";
//            }
//            return new ReadOnlyStringWrapper(estado);
//        });*/
    }




    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());

        operadoresExpuestos.setEditable(true);
        List<OperadorTuristico> query = (List<OperadorTuristico>) turOpMgr.encontrarTodos();
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        operadoresExpuestos.setItems(lista);



        idOperadorTuristico.setStyle("-fx-alignment: CENTER;");
        nombreOperador.setStyle("-fx-alignment: CENTER;");
        razonSocial.setStyle("-fx-alignment: CENTER;");
        nombreContacto.setStyle("-fx-alignment: CENTER;");
        apellidoContacto.setStyle("-fx-alignment: CENTER;");
        edadContacto.setStyle("-fx-alignment: CENTER;");
        telefonoContacto.setStyle("-fx-alignment: CENTER;");

        idOperadorTuristico.setCellValueFactory((new PropertyValueFactory<>("idOpTur")));
        nombreOperador.setCellValueFactory(new PropertyValueFactory<>("nameTO"));
        razonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        nombreContacto.setCellValueFactory((new PropertyValueFactory<>("contact_name")));
        apellidoContacto.setCellValueFactory((new PropertyValueFactory<>("contact_surname")));
        edadContacto.setCellValueFactory((new PropertyValueFactory<>("contact_age")));
        telefonoContacto.setCellValueFactory((new PropertyValueFactory<>("contact_phone")));
        //estaDisponible.setCellValueFactory((new PropertyValueFactory<>("estado")));
        estaDisponible.setCellValueFactory(new Callback<CellDataFeatures<OperadorTuristico, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<OperadorTuristico, Boolean> param) {
                OperadorTuristico operadorTuristico = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(operadorTuristico.getEstado());

                //estadoOperador.setOnEditCommit();


                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        operadorTuristico.setEstado(newValue);
                        turOpMgr.actualizarOperadorTuristico(operadorTuristico);
                    }
                });

                return booleanProperty;
            }
        });

        estaDisponible.setCellFactory(new Callback<TableColumn<OperadorTuristico, Boolean>, TableCell<OperadorTuristico, Boolean>>() {
            @Override
            public TableCell<OperadorTuristico, Boolean> call(TableColumn<OperadorTuristico, Boolean> param) {
                CheckBoxTableCell<OperadorTuristico,Boolean> cell = new CheckBoxTableCell<OperadorTuristico, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        idOperadorTuristico.setSortType(SortType.DESCENDING);
        operadoresExpuestos.getSortOrder().add(idOperadorTuristico);
        operadoresExpuestos.sort();

        DropShadow shadow = new DropShadow();
        animarBoton(cerrarSesion, shadow);
        animarBoton(volverMenu, shadow);
        animarBoton(agregarOperador, shadow);

    }


/*
    @FXML
    void validarExperiencia(ActionEvent actionEvent)throws Exception{
        OperadorTuristico operadorTuristico = operadoresExpuestos.getSelectionModel().getSelectedItem();
        operadorTuristico.setEstado(true);
        turOpMgr.actualizarOperadorTuristico(operadorTuristico);
        actualizarEstados();

    }

    @FXML
    void invalidarExperiencia(ActionEvent actionEvent)throws Exception{
        OperadorTuristico operadorTuristico = operadoresExpuestos.getSelectionModel().getSelectedItem();
        operadorTuristico.setEstado(false);
        turOpMgr.actualizarOperadorTuristico(operadorTuristico);
        actualizarEstados();
    }
*/


    @FXML
    void agregarOTAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(TOController.class.getResourceAsStream("AddOperadorTuristico.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//        AnchorPane root = fxmlLoader.load(TOController.class.getResourceAsStream("AddOperadorTuristico.fxml"));
//        principal.setearAnchorPane(root);
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
        //principal.setearAnchorPane(root);

    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuAdministradorController.class.getResourceAsStream("MenuAdministrador.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }
/*
    void actualizarEstados(){
        estaDisponible.setCellValueFactory(new Callback<CellDataFeatures<OperadorTuristico, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<OperadorTuristico, Boolean> param) {
                OperadorTuristico operadorTuristico = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(operadorTuristico.getEstado());

                //estadoOperador.setOnEditCommit();


                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        operadorTuristico.setEstado(newValue);
                    }
                });

                return booleanProperty;
            }
        });

        estaDisponible.setCellFactory(new Callback<TableColumn<OperadorTuristico, Boolean>, TableCell<OperadorTuristico, Boolean>>() {
            @Override
            public TableCell<OperadorTuristico, Boolean> call(TableColumn<OperadorTuristico, Boolean> param) {
                CheckBoxTableCell<OperadorTuristico,Boolean> cell = new CheckBoxTableCell<OperadorTuristico, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

    }
*/

    private void animarBoton(Button boton, DropShadow dropShadow){

        boton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(dropShadow);
            }
        });
        boton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(null);
            }
        });
    }
}
