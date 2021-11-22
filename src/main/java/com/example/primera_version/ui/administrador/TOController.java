package com.example.primera_version.ui.administrador;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.primera_version.Main;
import com.example.primera_version.business.TurOpMgr;
import com.example.primera_version.business.exceptions.InvalidTOInformation;
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
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class TOController implements Initializable {

//Esta de aca me controla la ventana del operador turistico, en donde me puedo registrar y eso

    @Autowired
    private TurOpMgr toMgr;

    @Autowired
    private Principal principal;

    @FXML
    private TextField txtRazonSocial;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtContactName;

    @FXML
    private TextField txtContactSurname;

    @FXML
    private TextField txtContactPhone;

    @FXML
    private TextField txtContactAge;

    @FXML
    private Button agregar;

    @FXML
    private Button cancelar;



    @FXML
    void addTO(ActionEvent event) {
        if (txtRazonSocial.getText() == null ||  txtName.getText() == null ||  txtContactName.getText() == null || txtContactSurname.getText() == null || txtContactPhone.getText() == null || txtContactAge.getText() == null || txtRazonSocial.getText().isBlank() || txtName.getText().isBlank() || txtContactName.getText().isBlank() || txtContactSurname.getText().isBlank() || txtContactAge.getText().isBlank() || txtContactPhone.getText().isBlank()) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                String razon_social = txtRazonSocial.getText();
                String name = txtName.getText();

                String contact_name = txtContactName.getText();
                String contact_surname = txtContactSurname.getText();
                String contact_phone = txtContactPhone.getText();
                int contact_age = Integer.parseInt(txtContactAge.getText());


                try {

                    toMgr.addTO(razon_social, name, contact_name, contact_surname, contact_phone, contact_age);

                    showAlert("Operador turistico agregado", "Se agrego con exito el Operador turistico!");

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                    AnchorPane root = fxmlLoader.load(OperadoresAdminisradorController.class.getResourceAsStream("MenuAdministrador.fxml"));
                    //principal.setearAnchorPane(root);
                    volverAdminOper(event);

                } catch (InvalidTOInformation invalidTOInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (com.example.primera_version.business.exceptions.TOAlreadyExists TOAlreadyExists ) {
                    showAlert(
                            "Mail ya registrado !",
                            "El mail indicado ya ha sido registrado en el sistema).");
                } catch (Exception e) {
                    e.printStackTrace();

                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "La edad del cliente no tiene el formato esperado (numerico).");

            }
        }

    }

    private void clean() { // Vacio todos los campos

        txtRazonSocial.setText(null);
        txtName.setText(null);
        txtContactName.setText(null);
        txtContactSurname.setText(null);
        txtContactPhone.setText(null);
        txtContactAge.setText(null);

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


    @FXML
    void volverAdminOper(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(OperadoresAdminisradorController.class.getResourceAsStream("OperadoresAdministrador.fxml"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DropShadow shadow = new DropShadow();
        animarBoton(agregar, shadow);
        animarBoton(cancelar, shadow);

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
}

