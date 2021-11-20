package com.example.primera_version.ui.tur_op_user;
import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.ui.Principal;
import com.sun.scenario.effect.impl.sw.java.JSWInvertMaskPeer;
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

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Component
public class Reservas_Operador implements Initializable {

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TableView<Reserva> misReservas;

    @FXML
    private TableColumn<Reserva,Long > numeroReserva;

    @FXML
    private TableColumn<Reserva,Boolean> estadoReserva;

    @FXML
    private TableColumn<Reserva, LocalDate> fechayhoraReserva;

    @FXML
    private TableColumn<Reserva,Long> idExperienciaReserva;

    @FXML
    private TableColumn<Reserva, String> mailTuristReserva;

    @FXML
    private TableColumn<Reserva, Long> numeroPersonas;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private ExperienciasOperadorController experienciasOperadorController;

    private ObservableList<Reserva> listaReserva;

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
    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("MenuTOUsers.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void volverAMisExperiencias(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("ExperienciasDeMiOperador.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        misReservas.setEditable(true);

        Collection<Reserva> query = experienciasOperadorController.misExperiencias.getSelectionModel().getSelectedItem().getReservas();
        listaReserva = FXCollections.observableArrayList();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);

        numeroReserva.setStyle("-fx-alignment: CENTER;");
        estadoReserva.setStyle("-fx-alignment: CENTER;");
        fechayhoraReserva.setStyle("-fx-alignment: CENTER;");
        idExperienciaReserva.setStyle("-fx-alignment: CENTER;");
        mailTuristReserva.setStyle("-fx-alignment: CENTER;");
        numeroPersonas.setStyle("-fx-alignment: CENTER;");

        numeroReserva.setCellValueFactory((new PropertyValueFactory<>("numeroReserva")));
        fechayhoraReserva.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        idExperienciaReserva.setCellValueFactory((new PropertyValueFactory<>("experiencia")));
        mailTuristReserva.setCellValueFactory((new PropertyValueFactory<>("turista")));
        numeroPersonas.setCellValueFactory(new PropertyValueFactory<>("numeroPersonas"));
        //estadoReserva.setCellValueFactory(new PropertyValueFactory<>("estado"));

        estadoReserva.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Reserva, Boolean> param) {
                Reserva reserva = param.getValue();
                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(false);
                if (!(reserva == null)) {
                    booleanProperty.setValue(reserva.getEstado());
                }


                //estadoExperiencia.setOnEditCommit();

                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        reserva.setEstado(newValue);
                        reservaMgr.actualizarReserva(reserva);
                    }
                });

                return booleanProperty;
            }
        });
        estadoReserva.setCellFactory(new Callback<TableColumn<Reserva, Boolean>, TableCell<Reserva, Boolean>>() {
            @Override
            public TableCell<Reserva, Boolean> call(TableColumn<Reserva, Boolean> param) {
                CheckBoxTableCell<Reserva,Boolean> cell = new CheckBoxTableCell<Reserva, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

    }

    @FXML
    void busquedaDinamica(KeyEvent event) {
        List<Reserva> query = (List<Reserva>) reservaMgr.encontrarNumeroReserva(Long.valueOf((campoBusqueda.getText())));
        listaReserva = FXCollections.observableArrayList();
        listaReserva.removeAll();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);
    }
}
