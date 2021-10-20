package com.example.primera_version.ui;

import com.example.primera_version.Main;
import com.example.primera_version.business.AdminMgr;
import com.example.primera_version.business.TurOpUsersMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.ui.administrador.ExperienciasAdministratorController;
import com.example.primera_version.ui.tur_op_user.MenuOperatorsUsersController;
import com.example.primera_version.ui.turist.MenuTuristController;
import com.example.primera_version.ui.turist.Perfil;
import com.example.primera_version.ui.turist.TuristController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class Principal {

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private AdminMgr adminMgr;

    @Autowired
    private TurOpUsersMgr turOpUsersMgr;

    @Autowired
    private TuristController turistController;

    @Autowired
    private Perfil perfil;

    @FXML
    public TextField username;

    @FXML
    private PasswordField password;

    //Esta de aca la usamos para el botton de resgitrarse, lo que hace es me lleva a la pestaña
    // donde pongo todos los datos de mi turista
    @FXML
    void agregarTuristaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(TuristController.class.getResourceAsStream("AddTurist.fxml"));
        turistController.addPaises(); //preciso llamarla aca porque tengo que precargar los datos en el choice box
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //La idea de este es que se fija si el usuario  y la contraseña machean, si lo hacen,
    //asumiendo que solo dejamos ingresar a turistas por ahora
    // me deberia dejar ir al menu de turistas
    @FXML
    void irMenuPrincipal(ActionEvent event) throws Exception {

            // ACA VOY A HACER LA BUSQUEDA EN LA BASE DE DATOS. El orden no es en vano.
            if (turistMgr.ingresar(username.getText(), password.getText())) {// Entra al if solo si me deja ingresar
                closeVentana(event);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                Parent root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
                Stage stageMenuTurist = new Stage();
                stageMenuTurist.setScene(new Scene(root));
                stageMenuTurist.show();
            } else if (adminMgr.ingresar(username.getText(), password.getText())) {
                closeVentana(event);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                Parent root = fxmlLoader.load(ExperienciasAdministratorController.class.getResourceAsStream("ExperienciasAdministrador.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root));
                stage1.show();
            } else if (turOpUsersMgr.ingresar(username.getText(), password.getText())) {
                closeVentana(event);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                Parent root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("MenuTO.fxml"));
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root));
                stage1.show();
            } else {
                showAlert(
                        "Datos incorrectos !",
                        "Ingrese su usuraio y su contraseña porfavor.");
                username.setText(null);
                password.setText(null);
            }
        }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
