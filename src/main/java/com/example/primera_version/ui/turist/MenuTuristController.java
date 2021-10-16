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
import java.util.*;

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

        Collection<Interes> intereses = turist.getIntereses();
        Iterable<Experiencia> allExperiencias = experienceRepository.findAll();
        ArrayList<Experiencia> experienciasRecomendadas = new ArrayList<>(); // Experiencias que voy a mostrar en el menu principal

        for (Experiencia experiencia : allExperiencias) {

            for (Interes interes : intereses) {
                if(experiencia.getIntereses().contains(interes)){
                    experienciasRecomendadas.add(experiencia);
                    break; // Para que no me agregue los intereses mas de una vez
                }
            }
        }

        try {
            experienciaButton1.setText(experienciasRecomendadas.get(0).getTituloExperiencia());
            experienciaImage1.setImage(experienciasRecomendadas.get(0).getImagenAsJavaFxImage(200, 200));

            experienciaButton2.setText(experienciasRecomendadas.get(1).getTituloExperiencia());
            experienciaImage2.setImage(experienciasRecomendadas.get(1).getImagenAsJavaFxImage(200, 200));

            experienciaButton3.setText(experienciasRecomendadas.get(2).getTituloExperiencia());
            experienciaImage3.setImage(experienciasRecomendadas.get(2).getImagenAsJavaFxImage(200, 200));

            experienciaButton4.setText(experienciasRecomendadas.get(3).getTituloExperiencia());
            experienciaImage4.setImage(experienciasRecomendadas.get(3).getImagenAsJavaFxImage(200, 200));

            experienciaButton5.setText(experienciasRecomendadas.get(4).getTituloExperiencia());
            experienciaImage5.setImage(experienciasRecomendadas.get(4).getImagenAsJavaFxImage(200, 200));

            experienciaButton5.setText(experienciasRecomendadas.get(5).getTituloExperiencia());
            experienciaImage5.setImage(experienciasRecomendadas.get(5).getImagenAsJavaFxImage(200, 200));

            experienciaButton6.setText(experienciasRecomendadas.get(6).getTituloExperiencia());
            experienciaImage6.setImage(experienciasRecomendadas.get(6).getImagenAsJavaFxImage(200, 200));

            experienciaButton7.setText(experienciasRecomendadas.get(7).getTituloExperiencia());
            experienciaImage7.setImage(experienciasRecomendadas.get(7).getImagenAsJavaFxImage(200, 200));

            experienciaButton8.setText(experienciasRecomendadas.get(8).getTituloExperiencia());
            experienciaImage8.setImage(experienciasRecomendadas.get(8).getImagenAsJavaFxImage(200, 200));

        }catch (Exception e){e.printStackTrace();}



//        experienciaButton1.setText("LosDedos");
//        //experienciaImage1.setImage(experienceRepository.findOneByTituloExperiencia(experienciaButton1.getText()).getImagenAsJavaFxImage((int)experienciaImage1.getFitHeight(),(int)experienciaImage1.getFitWidth()));
//        experienciaImage1.setImage(experienceRepository.findOneByTituloExperiencia(experienciaButton1.getText()).getImagenAsJavaFxImage(200,200));



    }
}
