package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.CriticaMgr;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class CriticaController implements Initializable {

    @FXML
    private Text titExpe;

    @Autowired
    private ReservaMgr reservaMgr;

    @FXML
    private TextField criticaExperiencia;

    @Autowired
    private MisReservasController misReservasController;

    @FXML
    private Button bottonConfirmar;

    @FXML
    private CriticaMgr criticaMgr;


    @FXML
    void volverAlPerfil(ActionEvent actionEvent) throws Exception {
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
    public void volverAlMenu(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }


    private void animarBoton(Button boton, DropShadow dropShadow) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow shadow = new DropShadow();
        criticaExperiencia.setStyle("-fx-background-color:  #87CEEB");
        titExpe.setText(misReservasController.getMisReservas().getSelectionModel().getSelectedItem().getExperiencia().getTituloExperiencia());
        animarBoton(bottonConfirmar, shadow);
    }

    @FXML
    void confirmarReserva(ActionEvent event) throws Exception {


        if (criticaExperiencia.getText() == null || criticaExperiencia.getText().isBlank()) {
            showAlert(
                    "Error en los datos!",
                    "Por favor ingrese los datos correctamente.");

        }

        String criticaTexto = criticaExperiencia.getText();
        Reserva reserva = reservaMgr.encontrarNumeroReserva(misReservasController.getMisReservas().getSelectionModel().getSelectedItem().getNumeroReserva());

        criticaMgr.addCritica(criticaTexto, reserva);
        showAlert("Critica agregada", "Se creo con exito!");
        close(event);







    }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Reserva.class.getResourceAsStream("ReservaTurist.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
