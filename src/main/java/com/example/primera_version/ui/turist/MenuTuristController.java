package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MenuTuristController {

    @FXML
    private Button myButtonTuPerfil;

    @FXML
    private Button myButtonMeGusta;

    @FXML
    private Button myButtonNoMeGusta;

    @FXML
    private Button myButtonExperiencia;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        perfil.informacionUsuario(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        //funcion para visitar tu perfil

    }

    @FXML
    void bottonMeGusta(ActionEvent event) {
        //funcion que hace algo al tocar me gusta
        showAlert("Agregado", "El destino fue agregado a su lista de intereses");


    }

    @FXML
    void bottonNoMeGusta(ActionEvent event) {
        //funcion que hace algo al tocar no me gusta

    }

    @FXML
    void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Experiencia.class.getResourceAsStream("Experiencia.fxml"));
        Stage stage = new Stage();
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

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }




}
