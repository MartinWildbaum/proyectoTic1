package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.persistence.TuristRepository;
import com.example.primera_version.persistence.UserRepository;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class Perfil {

    @FXML
    private Label usuariolabel;

    @FXML
    private Label paislabel;

    @FXML
    private Label fechalabel;

    @FXML
    private Label intereseslabel;

    @Autowired
    private Principal principal;

    @Autowired
    private MenuTuristController menuTuristController;

    @Autowired
    private TuristRepository turistRepository;

    private String cambiarLocalDateAString(LocalDate fecha){
        return fecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }


//    public void informacionTurista(String mail){
//        usuariolabel.setText(turistRepository.findOneByMail(mail).getMail());
//        paislabel.setText(turistRepository.findOneByMail(mail).getNacionalidad());
//        fechalabel.setText(cambiarLocalDateAString(turistRepository.findOneByMail(mail).getBirthdate()));
//        intereseslabel.setText(turistRepository.findOneByMail(mail).getInteresesAsociados().toString());
//
//    }



//    @FXML
//    void informacionUsuario(ActionEvent event) {
//        informacionTurista(mail);
//        usuariolabel.setText("hola");
//        paislabel.setText("hola2");
//        fechalabel.setText("hola3");
//        intereseslabel.setText("hola4");
//    }

    @FXML
    void cerrarSesion(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
