package com.example.primera_version.ui.tur_op_user;
import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.ui.Principal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

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
    private TableColumn<Reserva,LocalDateTime> fechayhoraReserva;

    @FXML
    private TableColumn<Reserva,Long> idExperienciaReserva;

    @FXML
    private TableColumn<Reserva, String> mailTuristReserva;

    @FXML
    private TableColumn<Reserva, Long> cantidadReserva;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private Principal principal;

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
        principal.setearAnchorPane(root);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        misReservas.setEditable(true);
        List<Reserva> query = reservaMgr.encontrarTodas();
        listaReserva = FXCollections.observableArrayList();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);

        numeroReserva.setStyle("-fx-alignment: CENTER;");
        estadoReserva.setStyle("-fx-alignment: CENTER;");
        fechayhoraReserva.setStyle("-fx-alignment: CENTER;");
        idExperienciaReserva.setStyle("-fx-alignment: CENTER;");
        mailTuristReserva.setStyle("-fx-alignment: CENTER;");
        cantidadReserva.setStyle("-fx-alignment: CENTER;");

        numeroReserva.setCellValueFactory((new PropertyValueFactory<>("Número de Reserva")));
        estadoReserva.setCellValueFactory(new PropertyValueFactory<>("Estado de reserva"));
        fechayhoraReserva.setCellValueFactory(new PropertyValueFactory<>("Fecha y Hora"));
        idExperienciaReserva.setCellValueFactory((new PropertyValueFactory<>("Id Experiencia")));
        mailTuristReserva.setCellValueFactory((new PropertyValueFactory<>("Mail Turista")));
        cantidadReserva.setCellValueFactory(new PropertyValueFactory<>("Cantidad Reserva"));

    }

    @FXML
    void busquedaDinamica(KeyEvent event) {
        List<Reserva> query = (List<Reserva>) reservaMgr.encontrarTodasContenidoTitulo(Long.valueOf((campoBusqueda.getText())));
        listaReserva = FXCollections.observableArrayList();
        listaReserva.removeAll();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);
    }
}
