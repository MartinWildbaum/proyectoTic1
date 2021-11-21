package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.TuristMgr;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
    private TableColumn<Reserva, Long> numeroAcompanantes;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private Principal principal;

    private ObservableList<Reserva> listaReserva;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        misReservas.setEditable(true);
/*
        Set<Reserva> query = turistMgr.encontrarTurista(principal.username.getText()).getReservas();
        listaReserva = FXCollections.observableArrayList();
        for (Reserva reserva: query) {
            listaReserva.add(reserva);
        }*/
        //listaReserva.addAll(query);
        misReservas.setItems(listaReserva);

        numeroReserva.setStyle("-fx-alignment: CENTER;");
        tituloExperiencia.setStyle("-fx-alignment: CENTER;");
        fechayhoraReserva.setStyle("-fx-alignment: CENTER;");
        numeroAcompanantes.setStyle("-fx-alignment: CENTER;");


        numeroReserva.setCellValueFactory((new PropertyValueFactory<>("numeroReserva")));
        fechayhoraReserva.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        numeroAcompanantes.setCellValueFactory((new PropertyValueFactory<>("numeroPersonas")));
        tituloExperiencia.setCellValueFactory((new PropertyValueFactory<>("experiencia"))); // Ver como hacer para que me muestre el titulo y no el id de la experiencia

    }



    @FXML
    void busquedaDinamica(KeyEvent event) {
        List<Reserva> query = (List<Reserva>) reservaMgr.encontrarNumeroReserva(Long.valueOf((campoBusqueda.getText())));
        listaReserva = FXCollections.observableArrayList();
        listaReserva.removeAll();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);
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



