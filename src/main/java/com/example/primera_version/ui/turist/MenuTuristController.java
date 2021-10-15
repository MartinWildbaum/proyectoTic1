package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.TuristRepository;
import com.example.primera_version.ui.Principal;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MenuTuristController implements Initializable{


    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private TuristRepository turistRepository;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @Autowired
    private Template template;

    @FXML
    private Button irExperiencia;

    @FXML
    private Image  myImage;

    @FXML
    private Button experienciaButton1;

    @FXML
    private Button experienciaButton2;

    @FXML
    private Button experienciaButton3;

    @FXML
    private Button experienciaButton4;

    @FXML
    private Button experienciaButton5;

    @FXML
    private Button experienciaButton6;

    @FXML
    private Button experienciaButton7;

    @FXML
    private Button experienciaButton8;

    @FXML
    private ImageView experienciaImage1 ;

    @FXML
    private ImageView experienciaImage2;

    @FXML
    private ImageView experienciaImage3;

    @FXML
    private ImageView experienciaImage4;

    @FXML
    private ImageView experienciaImage5;

    @FXML
    private ImageView experienciaImage6;

    @FXML
    private ImageView experienciaImage7;

    @FXML
    private ImageView experienciaImage8;

    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        perfil.setInformacionUsuario(principal.username.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        //funcion para visitar tu perfil
    }

    @FXML
    void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Template.class.getResourceAsStream("Template.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        template.setTemplete((experienceRepository.findOneByTituloExperiencia(experienciaButton1.getText()).getIdExperiencia()));
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Turist turist = turistRepository.findOneByMail(principal.username.getText());
        /*
        Iterator<Interes> iter = turist.getIntereses().iterator();

        String nombreExperiencia1 = iter.next().getNombre();

        experienciaButton1.setText(nombreExperiencia1);
        experienciaImage1.setImage(experienceRepository.findOneByTituloExperiencia(nombreExperiencia1).getImagenAsJavaFxImage(200,200));

        String nombreExperiencia2 = iter.next().getNombre();

        experienciaButton2.setText(nombreExperiencia2);
        experienciaImage2.setImage(experienceRepository.findOneByTituloExperiencia(nombreExperiencia2).getImagenAsJavaFxImage(200,200));


        String nombreExperiencia3 = iter.next().getNombre();

        experienciaButton3.setText(nombreExperiencia3);
        experienciaImage3.setImage(experienceRepository.findOneByTituloExperiencia(nombreExperiencia3).getImagenAsJavaFxImage(200,200));


        String nombreExperiencia4 = iter.next().getNombre();

        experienciaButton4.setText(nombreExperiencia4);
        experienciaImage4.setImage(experienceRepository.findOneByTituloExperiencia(nombreExperiencia4).getImagenAsJavaFxImage(200,200));

*/


        experienciaButton1.setText("LosDedos");
        //experienciaImage1.setImage(experienceRepository.findOneByTituloExperiencia(experienciaButton1.getText()).getImagenAsJavaFxImage((int)experienciaImage1.getFitHeight(),(int)experienciaImage1.getFitWidth()));
        experienciaImage1.setImage(experienceRepository.findOneByTituloExperiencia(experienciaButton1.getText()).getImagenAsJavaFxImage(200,200));



    }
}
