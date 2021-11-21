package com.example.primera_version.ui.administrador;


import com.example.primera_version.Main;
import com.example.primera_version.business.PaisMgr;
import com.example.primera_version.business.TurOpUsersMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.exceptions.InvalidTOInformation;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.PasswordNoCoinciden;
import com.example.primera_version.business.exceptions.UserAlreadyExists;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.turist.SeleccionadorInicialInteresesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class AgregarFuncionarioOperadorController {

    @Autowired
    private  Principal principal;

    @Autowired
    private TurOpUsersMgr turOpUsersMgr;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private TextField txtRaxonSocial;



    @FXML
    void addUserTO(ActionEvent event) {
        if (txtMail.getText().isBlank() || txtPassword1.getText().isBlank() || txtPassword2.getText().isBlank() || txtRaxonSocial.getText().isBlank()) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                String mail = txtMail.getText();
                String password = txtPassword1.getText();
                String password2 = txtPassword2.getText();
                String nombreOperador = txtRaxonSocial.getText();


                try {

                    turOpUsersMgr.addUsuarioOpTuristico(mail, password, password2, nombreOperador);
                    showAlert("Funcionario agregado", "Se agrego con exito el funcionario del operador turistico!");
                    close(event);

                } catch (InvalidUserInformation invalidTuristInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (UserAlreadyExists turistAlreadyExists) {
                    showAlert(
                            "Mail ya registrado !",
                            "El mail indicado ya ha sido registrado en el sistema.");
                } catch (PasswordNoCoinciden passwordNoCoinciden) {
                    showAlert(
                            "Error en la contraseña !",
                            "Las contraseñas no coinciden.");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidTOInformation invalidTOInformation) {
                    showAlert(
                            "Error en el nombre del operador turistico !",
                            "El nombre del operador turitico ingresado no se encuentra en nuestro sistema.");
                }
            }catch (Exception ignored){
                // Proponemos esto por control de que el operador turistico valide el ingreso de su fincionario
            }
        }
    }




    @FXML
    void close(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuAdministradorController.class.getResourceAsStream("MenuAdministrador.fxml"));
        Node source = (Node) event.getSource();
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
}
