package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.ui.Principal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MisReservasController implements Initializable {


    @FXML
    private TextField campoBusqueda;

    @FXML
    private Button puntuarExperiencia;

    @FXML
    private Button realizarDenuncia;

    @FXML
    private Button realizarCritica;

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


        Collection<Reserva> query = turistMgr.encontrarTurista(principal.getUsername().getText()).getReservas();
        listaReserva = FXCollections.observableArrayList();
        for (Reserva reserva: query) {
            if(reserva.getExperiencia().getEstaDisponible() && reserva.getExperiencia().getOperadorTuristico().getEstado()){ // La agrego solo si tanto la expriencia como el operador turistico estan habilitados
                listaReserva.add(reserva);
            }
        }
        //listaReserva.addAll(query);
        misReservas.setItems(listaReserva);


        numeroReserva.setStyle("-fx-alignment: CENTER;");
        tituloExperiencia.setStyle("-fx-alignment: CENTER;");
        fechayhoraReserva.setStyle("-fx-alignment: CENTER;");
        numeroAcompanantes.setStyle("-fx-alignment: CENTER;");


        numeroReserva.setCellValueFactory((new PropertyValueFactory<>("numeroReserva")));
        fechayhoraReserva.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        numeroAcompanantes.setCellValueFactory((new PropertyValueFactory<>("numeroPersonas")));
        //tituloExperiencia.setCellValueFactory((new PropertyValueFactory<>("experiencia"))); // Ver como hacer para que me muestre el titulo y no el id de la experiencia
        tituloExperiencia.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getExperiencia().getTituloExperiencia()));

        fechayhoraReserva.setSortType(TableColumn.SortType.DESCENDING);
        misReservas.getSortOrder().add(fechayhoraReserva);
        misReservas.sort();

        //numeroAcompanantes.setStyle("-fx-background-color: transparent");
        misReservas.setStyle("-fx-background-color: derive;");
        misReservas.setStyle("-fx-stroke: transparent;");
        misReservas.setStyle("-fx-alignment: CENTER ; ");
        misReservas.setStyle("-fx-text-alignment: center;");

        DropShadow shadow = new DropShadow();
        animarBoton(puntuarExperiencia, shadow);
        animarBoton(realizarCritica, shadow);
        animarBoton(realizarDenuncia, shadow);

    }



    @FXML
    void busquedaDinamica(KeyEvent event) {


        List<Reserva> query = (List<Reserva>) reservaMgr.encontrarPorTituloExpYTurista(turistMgr.encontrarTurista(principal.getUsername().getText()), campoBusqueda.getText());
        listaReserva = FXCollections.observableArrayList();
        listaReserva.removeAll();
        listaReserva.addAll(query);
        misReservas.setItems(listaReserva);
        if(!campoBusqueda.getText().isBlank()){
            misReservas.sort();
        }



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



    @FXML
    void realizarDenuncia(ActionEvent actionEvent)throws Exception{

        if(misReservas.getSelectionModel().getSelectedItem().getFecha().isAfter(LocalDate.now())){
            showAlert(
                    "No puede realizar la denuncia !",
                    "Solo es posible realizar denuncias de experiencias luego de asistir a estas.");
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(DenunciaController.class.getResourceAsStream("RealizarDenuncia.fxml"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void realizarCritica(ActionEvent actionEvent) throws Exception{

        if(misReservas.getSelectionModel().getSelectedItem().getFecha().isAfter(LocalDate.now()) && !misReservas.getSelectionModel().getSelectedItem().getFecha().isEqual(LocalDate.now())){
            showAlert(
                    "No puede realizar la critica !",
                    "Solo es posible realizar criticas de experiencias luego de asistir a estas.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(CriticaController.class.getResourceAsStream("RealizarCritica.fxml"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void puntuarExperiencia(ActionEvent actionEvent) throws Exception{

        if(misReservas.getSelectionModel().getSelectedItem().getFecha().isAfter(LocalDate.now())){
            showAlert(
                    "No puede puntuar la experiencia !",
                    "Solo es posible puntuar experiencias luego de asistir a estas.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(PuntuarExperienciaController.class.getResourceAsStream("PuntuarExperiencia.fxml"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

        }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

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

    public TableColumn<Reserva, String> getTituloExperiencia() {
        return tituloExperiencia;
    }

    public TableView<Reserva> getMisReservas() {
        return misReservas;
    }
}



