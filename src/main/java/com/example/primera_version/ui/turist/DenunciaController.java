package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.CriticaMgr;
import com.example.primera_version.business.DenunciaMgr;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.entities.Reserva;
import com.example.primera_version.business.exceptions.InavlidClasificacionInformation;
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
import java.util.ResourceBundle;

@Component
public class DenunciaController implements Initializable {

    @FXML
    private Text tituloExperienciaDenuncia;

    @FXML
    private TextField motivoDeLaDenuncia;

    @FXML
    private TextField descrpicionDenuncia;

    @Autowired
    private DenunciaMgr denunciaMgr;

    @Autowired
    private ReservaMgr reservaMgr;

    @FXML
    private Button bottonConfirmar;

    @Autowired
    private MisReservasController misReservasController;

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
    public void volverAlMenu(ActionEvent event) throws Exception{
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

    @FXML
    void confirmarDenuncia(ActionEvent event){
        if (motivoDeLaDenuncia.getText().isBlank() || descrpicionDenuncia.getText().isBlank()) {
            showAlert(
                    "Error en los datos!",
                    "Por favor ingrese los datos correctamente.");

        }


        if (motivoDeLaDenuncia.getText() == null || motivoDeLaDenuncia.getText().isBlank() || descrpicionDenuncia.getText() == null || descrpicionDenuncia.getText().isBlank()) {
            showAlert(
                    "Error en los datos!",
                    "Por favor ingrese los datos correctamente.");
            motivoDeLaDenuncia.setText(null);
            descrpicionDenuncia.setText(null);

        }else{

            String motivo = motivoDeLaDenuncia.getText();
            String descrpicion = descrpicionDenuncia.getText();

            Reserva reserva = reservaMgr.encontrarNumeroReserva(misReservasController.getMisReservas().getSelectionModel().getSelectedItem().getNumeroReserva());


            try {
                denunciaMgr.addDenuncia(motivo, descrpicion, reserva);
                showAlert("Critica agregada", "Se creo con exito!");
                close(event);

            } catch (InavlidClasificacionInformation | IOException inavlidClasificacionInformation) {
                showAlert(
                        "Informacion invalida !",
                        "Se encontro un error en los datos ingresados o ya hay una critica para esta reserva.");

            }
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow shadow = new DropShadow();
        tituloExperienciaDenuncia.setText(misReservasController.getMisReservas().getSelectionModel().getSelectedItem().getExperiencia().getTituloExperiencia());
        animarBoton(bottonConfirmar, shadow);


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
        AnchorPane root = fxmlLoader.load(MisReservasController.class.getResourceAsStream("VerMisReservas.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
