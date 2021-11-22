package com.example.primera_version.ui.administrador;

import com.example.primera_version.Main;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class MenuAdministradorController implements Initializable {

    @Autowired
    private Principal principal;

    @FXML
    private Button administrarOperadores;

    @FXML
    private Button administrarExperiencias;

    @FXML
    private Button habilitarExperiencias;

    @FXML
    private Button ingresarFuncionario;

    @FXML
    private Button cerrarSession;


    @FXML
    void administrarExperiencias(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(ExperienciasAdministratorController.class.getResourceAsStream("ExperienciasAdministrador.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//        AnchorPane root = fxmlLoader.load(ExperienciasAdministratorController.class.getResourceAsStream("ExperienciasAdministrador.fxml"));
//        principal.setearAnchorPane(root);

    }

    @FXML
    void administrarOperadores(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(OperadoresAdminisradorController.class.getResourceAsStream("OperadoresAdministrador.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//        AnchorPane root = fxmlLoader.load(OperadoresAdminisradorController.class.getResourceAsStream("OperadoresAdministrador.fxml"));
//        principal.setearAnchorPane(root);
    }

    @FXML
    void experienciasASerValidadas(ActionEvent actionEvent)throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(OperadoresAdminisradorController.class.getResourceAsStream("HabilitadorNuevasExperiencias.fxml"));
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void IngresarFuncionario(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(AgregarFuncionarioOperadorController.class.getResourceAsStream("IngresarFuncionario.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DropShadow shadow = new DropShadow();
        animarBoton(administrarOperadores, shadow);
        animarBoton(administrarExperiencias, shadow);
        animarBoton(habilitarExperiencias, shadow);
        animarBoton(ingresarFuncionario, shadow);
        animarBoton(cerrarSession, shadow);
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





