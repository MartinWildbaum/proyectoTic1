package com.example.primera_version.ui.administrador;


import com.example.primera_version.Main;
import com.example.primera_version.business.AdminMgr;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.ui.Principal;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class ExperienciasAdministratorController implements Initializable {

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private AdminMgr adminMgr;

    @Autowired
    private Principal principal;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TableView<Experiencia> experienciasExpuestas;

    @FXML
    private TableColumn<Experiencia, String> tituloExpriencia;

    @FXML
    private TableColumn<Experiencia, String> descripcionExperiencia;

    @FXML
    private TableColumn<Experiencia, String> idExperiencia;

    @FXML
    private TableColumn<Experiencia, String> ubicacionExperiencia;

    @FXML
    private TableColumn<Experiencia, Collection<Interes>> interesesExperiencia;

    @FXML
    private TableColumn<Experiencia, String> aforoExperiencia;

    @FXML
    private TableColumn<Experiencia, OperadorTuristico> operadorExperiencia; // Refiere al nombre del operador turistico

    @FXML
    private TableColumn<Experiencia, Boolean> estadoExperiencia;


    ObservableList<Experiencia> lista;

    @FXML
    void busquedaDinamica(KeyEvent event){
        List<Experiencia> query = (List<Experiencia>) experienceMgr.encontrarTodasContenidoTitulo(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        experienciasExpuestas.setItems(lista);
        experienciasExpuestas.sort();
        /*
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));

        descripcionExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            String descripcion_experiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getDescripcion();
            return new ReadOnlyStringWrapper(descripcion_experiencia);
        });

        idExperiencia.setCellValueFactory(cellData -> {
            Long idExp = cellData.getValue().getIdExperiencia();
            return new ReadOnlyStringWrapper(String.valueOf(idExp));
        });

        ubicacionExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            String ubicacionExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getUbicacion();
            return new ReadOnlyStringWrapper(ubicacionExperiencia);
        });

        aforoExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            Integer aforoExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getCantidad();
            return new ReadOnlyStringWrapper(String.valueOf(aforoExperiencia));
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
*/

    }




    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());
        experienciasExpuestas.setEditable(true);
        List<Experiencia> query = experienceMgr.encontrarTodas();
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        experienciasExpuestas.setItems(lista);

        idExperiencia.setStyle("-fx-alignment: CENTER;");
        tituloExpriencia.setStyle("-fx-alignment: CENTER;");
        descripcionExperiencia.setStyle("-fx-alignment: CENTER;");
        ubicacionExperiencia.setStyle("-fx-alignment: CENTER;");
        aforoExperiencia.setStyle("-fx-alignment: CENTER;");
        operadorExperiencia.setStyle("-fx-alignment: CENTER;");
        interesesExperiencia.setStyle("-fx-alignment: CENTER;");

        idExperiencia.setCellValueFactory((new PropertyValueFactory<>("idExperiencia")));
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ubicacionExperiencia.setCellValueFactory((new PropertyValueFactory<>("ubicacion")));
        aforoExperiencia.setCellValueFactory((new PropertyValueFactory<>("cantidad")));
        operadorExperiencia.setCellValueFactory(new PropertyValueFactory<>("operadorTuristico"));
        interesesExperiencia.setCellValueFactory(new PropertyValueFactory<>("intereses"));
        //estadoExperiencia.setCellValueFactory((new PropertyValueFactory<>("estadoExperiencia")));
        estadoExperiencia.setCellValueFactory(new Callback<CellDataFeatures<Experiencia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Experiencia, Boolean> param) {
                Experiencia experiencia = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(experiencia.getEstaDisponible());

                //estadoExperiencia.setOnEditCommit();

                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        experiencia.setEstaDisponible(newValue);
                        experienceMgr.actualizarExperiencia(experiencia);
                    }
                });

                return booleanProperty;
            }
        });
        estadoExperiencia.setCellFactory(new Callback<TableColumn<Experiencia, Boolean>, TableCell<Experiencia, Boolean>>() {
            @Override
            public TableCell<Experiencia, Boolean> call(TableColumn<Experiencia, Boolean> param) {
                CheckBoxTableCell<Experiencia,Boolean> cell = new CheckBoxTableCell<Experiencia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        idExperiencia.setSortType(SortType.DESCENDING);
        experienciasExpuestas.getSortOrder().add(idExperiencia);
        experienciasExpuestas.sort();
    }

/*
    @FXML
    void validarExperiencia(ActionEvent actionEvent)throws Exception{
        Experiencia experiencia = experienciasExpuestas.getSelectionModel().getSelectedItem();
        experiencia.setEstaDisponible(true);
        // Quiero guardar en la tabla quien fue el ultimo administrador que modifico mi expereincia asi hay un responsable
        experiencia.setAdministrador(adminMgr.encontrarAdministradorPorMail(principal.username.getText()));
        experienceMgr.actualizarExperiencia(experiencia);
        actualizarEstados();
    }

    @FXML
    void invalidarExperiencia(ActionEvent actionEvent)throws Exception{
        Experiencia experiencia = experienciasExpuestas.getSelectionModel().getSelectedItem();
        experiencia.setEstaDisponible(false);
        // Quiero guardar en la tabla quien fue el ultimo administrador que modifico mi expereincia asi hay un responsable
        experiencia.setAdministrador(adminMgr.encontrarAdministradorPorMail(principal.username.getText()));
        experienceMgr.actualizarExperiencia(experiencia);
        actualizarEstados();
    }
*/
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

    @FXML
    void close(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
/*
    void actualizarEstados(){
        estadoExperiencia.setCellValueFactory(new Callback<CellDataFeatures<Experiencia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Experiencia, Boolean> param) {
                Experiencia experiencia = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(experiencia.getEstaDisponible());

                //estadoExperiencia.setOnEditCommit();

                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        experiencia.setEstaDisponible(newValue);
                    }
                });

                return booleanProperty;
            }
        });
        estadoExperiencia.setCellFactory(new Callback<TableColumn<Experiencia, Boolean>, TableCell<Experiencia, Boolean>>() {
            @Override
            public TableCell<Experiencia, Boolean> call(TableColumn<Experiencia, Boolean> param) {
                CheckBoxTableCell<Experiencia,Boolean> cell = new CheckBoxTableCell<Experiencia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }
*/
}
