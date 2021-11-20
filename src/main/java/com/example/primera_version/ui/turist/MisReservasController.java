package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.ui.Principal;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;

@Component
public class MisReservasController implements Initializable {


    @FXML
    private TextField campoBusqueda;

    @FXML
    private TableView<Reserva> misReservas;

    @FXML
    private TableColumn<Reserva,Long > numeroReserva;

    @FXML
    private TableColumn<Reserva, LocalDate> fechayhoraReserva;

    @FXML
    private TableColumn<Reserva,String> tituloExperiencia;

    @FXML
    private TableColumn<Reserva, Long> numeroAcompañantes;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private Principal principal;

    private ObservableList<Reserva> listaReserva;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        misReservas.setEditable(true);
/*
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

*/
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
    }


    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void volverAlPerfil(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("Perfil.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
}



