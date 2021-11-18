package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


@Component
public class Perfil implements Initializable {


    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private ModificadorInteresesController modificadorInteresesController;

    @FXML
    private Button misIntereses;

    @FXML
    private ScrollPane scrollIntereses;

    @FXML
    private Button misReservas;

    @FXML
    private Label usuariolabel;

    @FXML
    private Label paislabel;

    @FXML
    private Label fechalabel;

    @FXML
    public Text intereseslabel;

    private String cambiarLocalDateAString(LocalDate fecha){
        return fecha.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
    }

    public void setInformacionUsuario(){

        Turist turistAMostrar = turistMgr.encontrarTurista(principal.username.getText());
        System.out.println(turistAMostrar.getListaIntereses());
        usuariolabel.setText(turistAMostrar.getMail());
        usuariolabel.setStyle("-fx-alignment: CENTER;");// Me centra el texto
        paislabel.setText(turistAMostrar.getPais().getNombre());
        paislabel.setStyle("-fx-alignment: CENTER;");
        fechalabel.setText(cambiarLocalDateAString(turistAMostrar.getBirthdate()));
        fechalabel.setStyle("-fx-alignment: CENTER;");
        intereseslabel.setText(modificadorInteresesController.interesesGeneralesSeleccionados.getText() + modificadorInteresesController.interesesParticularesSeleccionados.getText());
        intereseslabel.setStyle("-fx-alignment: CENTER;");
    }

    @FXML
    void modificarIntereses(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(ModificadorInteresesController.class.getResourceAsStream("ModificarIntereses.fxml"));
        principal.setearAnchorPane(root);
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
        //principal.setearAnchorPane(root);
    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        principal.setearAnchorPane(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        scrollIntereses.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollIntereses.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Turist turistAMostrar = turistMgr.encontrarTurista(principal.username.getText());


        usuariolabel.setText(turistAMostrar.getMail());
        usuariolabel.setStyle("-fx-alignment: CENTER;");// Me centra el texto
        paislabel.setText(turistAMostrar.getPais().getNombre());
        paislabel.setStyle("-fx-alignment: CENTER;");
        fechalabel.setText(cambiarLocalDateAString(turistAMostrar.getBirthdate()));
        fechalabel.setStyle("-fx-alignment: CENTER;");
        intereseslabel.setText(turistAMostrar.getListaIntereses());
        intereseslabel.setStyle("-fx-alignment: CENTER;");
    }

}
