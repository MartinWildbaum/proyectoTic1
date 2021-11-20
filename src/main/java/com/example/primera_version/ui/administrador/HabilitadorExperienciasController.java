package com.example.primera_version.ui.administrador;

import com.example.primera_version.Main;
import com.example.primera_version.business.AdminMgr;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.tur_op_user.MenuOperatorsUsersController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class HabilitadorExperienciasController implements Initializable {


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
    private TableColumn<Experiencia, String> momentoRegistro;

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
    void busquedaDinamica(KeyEvent event) {
        List<Experiencia> query = (List<Experiencia>) experienceMgr.encontrarTodasContenidoTitulo(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        for (Experiencia experiencia:query) {
            if(!experiencia.getEstaDisponible()){
                lista.add(experiencia);
            }
        }
        experienciasExpuestas.setItems(lista);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        experienciasExpuestas.setEditable(true);

        List<Experiencia> query = experienceMgr.encontrarTodas();
        lista = FXCollections.observableArrayList();
        for (Experiencia experiencia: query) {
            if(!experiencia.getEstaDisponible()){
                lista.add(experiencia);
            }
        }
        experienciasExpuestas.setItems(lista);

        idExperiencia.setStyle("-fx-alignment: CENTER;");
        tituloExpriencia.setStyle("-fx-alignment: CENTER;");
        descripcionExperiencia.setStyle("-fx-alignment: CENTER;");
        ubicacionExperiencia.setStyle("-fx-alignment: CENTER;");
        aforoExperiencia.setStyle("-fx-alignment: CENTER;");
        operadorExperiencia.setStyle("-fx-alignment: CENTER;");
        interesesExperiencia.setStyle("-fx-alignment: CENTER;");
        momentoRegistro.setStyle("-fx-alignment: CENTER;");

        idExperiencia.setCellValueFactory((new PropertyValueFactory<>("idExperiencia")));
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ubicacionExperiencia.setCellValueFactory((new PropertyValueFactory<>("ubicacion")));
        aforoExperiencia.setCellValueFactory((new PropertyValueFactory<>("cantidad")));
        operadorExperiencia.setCellValueFactory(new PropertyValueFactory<>("operadorTuristico"));
        interesesExperiencia.setCellValueFactory(new PropertyValueFactory<>("intereses"));
        momentoRegistro.setCellValueFactory(new PropertyValueFactory<>("momentoRegistro"));
        //estadoExperiencia.setCellValueFactory((new PropertyValueFactory<>("estadoExperiencia")));
        estadoExperiencia.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Experiencia, Boolean>, ObservableValue<Boolean>>() {
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
    }
}
