package com.example.primera_version.ui.administrador;

import com.example.primera_version.Main;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.TurOpRepository;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.turist.MenuTuristController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class OperadoresAdminisradorController implements Initializable{


    @Autowired
    private TurOpRepository turOpRepository;



    @FXML
    private TextField campoBusqueda;

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
    private TableColumn<OperadorTuristico, Boolean> estadoOperador;


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


    ObservableList<OperadorTuristico> lista;

    @FXML
    void busquedaDinamica(KeyEvent event){
        List<OperadorTuristico> query = (List<OperadorTuristico>) turOpRepository.findAllByNameTOContaining(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        operadoresExpuestos.setItems(lista);
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
//        });
    }




    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());
        List<OperadorTuristico> query = (List<OperadorTuristico>) turOpRepository.findAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        operadoresExpuestos.setItems(lista);
        idOperadorTuristico.setCellValueFactory((new PropertyValueFactory<>("idOpTur")));
        nombreOperador.setCellValueFactory(new PropertyValueFactory<>("nameTO"));
        razonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        nombreContacto.setCellValueFactory((new PropertyValueFactory<>("contact_name")));
        apellidoContacto.setCellValueFactory((new PropertyValueFactory<>("contact_surname")));
        edadContacto.setCellValueFactory((new PropertyValueFactory<>("contact_age")));
        telefonoContacto.setCellValueFactory((new PropertyValueFactory<>("contact_phone")));
        //estadoOperador.setCellValueFactory((new PropertyValueFactory<>("estado")));
        estadoOperador.setCellValueFactory(new Callback<CellDataFeatures<OperadorTuristico, Boolean>, ObservableValue<Boolean>>() {
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

        estadoOperador.setCellFactory(new Callback<TableColumn<OperadorTuristico, Boolean>, TableCell<OperadorTuristico, Boolean>>() {
            @Override
            public TableCell<OperadorTuristico, Boolean> call(TableColumn<OperadorTuristico, Boolean> param) {
                CheckBoxTableCell<OperadorTuristico,Boolean> cell = new CheckBoxTableCell<OperadorTuristico, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

    }



    @FXML
    void agregarOTAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(TOController.class.getResourceAsStream("AddOperadorTuristico.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(MenuAdministradorController.class.getResourceAsStream("MenuAdministrador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
