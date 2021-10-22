package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.persistence.TuristRepository;
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

    @Autowired
    private TuristRepository turistRepository;

    @FXML
    private Label usuariolabel;

    @FXML
    private Label paislabel;

    @FXML
    private Label fechalabel;

    @FXML
    private Label intereseslabel;

    @FXML
    private Button cerrarSesion;



    private String cambiarLocalDateAString(LocalDate fecha){
        return fecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }

    public void setInformacionUsuario(String mail){

        Turist turistAMostrar = turistRepository.findOneByMail(mail);

        usuariolabel.setText(turistAMostrar.getMail());
        usuariolabel.setStyle("-fx-alignment: CENTER;");// Me centra el texto
        paislabel.setText(turistAMostrar.getPais().getNombre());
        paislabel.setStyle("-fx-alignment: CENTER;");
        fechalabel.setText(cambiarLocalDateAString(turistAMostrar.getBirthdate()));
        fechalabel.setStyle("-fx-alignment: CENTER;");
        intereseslabel.setText(turistAMostrar.getIntereses().toString());
        intereseslabel.setStyle("-fx-alignment: CENTER;");
    }

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
