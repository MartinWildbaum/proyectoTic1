package com.example.primera_version.ui;

import com.example.primera_version.Main;
import com.example.primera_version.business.AdminMgr;
import com.example.primera_version.business.TurOpUsersMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.ui.administrador.MenuAdministradorController;
import com.example.primera_version.ui.tur_op_user.MenuOperatorsUsersController;
import com.example.primera_version.ui.turist.MenuTuristController;
import com.example.primera_version.ui.turist.AddTuristController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


@Component
public class Principal {

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private AdminMgr adminMgr;

    @Autowired
    private TurOpUsersMgr turOpUsersMgr;

    @Autowired
    private AddTuristController addTuristController;

    @FXML
    public TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelTitulo;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private Label logoLabel;

    @FXML
    private Button registrarseButton;

    public void setearAnchorPane(AnchorPane pane){
        rootPane.getChildren().setAll(pane);
    }

    //Esta de aca la usamos para el botton de resgitrarse, lo que hace es me lleva a la pestaña
    // donde pongo todos los datos de mi turista
    @FXML
    void agregarTuristaAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(AddTuristController.class.getResourceAsStream("AddTurist.fxml"));
        setearAnchorPane(pane);
    }

    //La idea de este es que se fija si el usuario  y la contraseña machean, si lo hacen,
    //asumiendo que solo dejamos ingresar a turistas por ahora
    // me deberia dejar ir al menu de turistas
    @FXML
    void irMenuPrincipal(ActionEvent event) throws Exception {
            // ACA VOY A HACER LA BUSQUEDA EN LA BASE DE DATOS. El orden no es en vano.
            if (turistMgr.ingresar(username.getText(), password.getText())) {// Entra al if solo si me deja ingresar
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root));
                stage.show();
            } else if (adminMgr.ingresar(username.getText(), password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane root = fxmlLoader.load(MenuAdministradorController.class.getResourceAsStream("MenuAdministrador.fxml"));
                setearAnchorPane(root);
            } else if (turOpUsersMgr.ingresar(username.getText(), password.getText())) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("MenuTOUsers.fxml"));
                setearAnchorPane(root);
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

}
