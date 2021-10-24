package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.persistence.ExperienceRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class MostrarExperienciasDinamicoController {

    @FXML
    private ImageView imagenExperiencia;

    @FXML
    private Button buttonExperiencia;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private ExperienciaTemplate experienciaTemplate;

    public void setData(Experiencia experiencia){
        imagenExperiencia.setImage(experiencia.getImagenAsJavaFxImage(200, 200));
        buttonExperiencia.setText(experiencia.getTituloExperiencia());
    }

    @FXML
    void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(ExperienciaTemplate.class.getResourceAsStream("Template.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        experienciaTemplate.setTemplete(this.experienceMgr.encontrarExperienciaPorTitulo(buttonExperiencia.getText()).getIdExperiencia());
        stage.show();
    }

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
