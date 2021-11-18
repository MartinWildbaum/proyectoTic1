package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MostrarExperienciasDinamicoController {

    @FXML
    private ImageView imagenExperiencia;

    @FXML
    public Button buttonExperiencia;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private ExperienciaTemplate experienciaTemplate;


    public void setData(Experiencia experiencia){

        if (experiencia.getImagenes().size() > 0){
            imagenExperiencia.setImage(experiencia.getImagenes().iterator().next().getImagenAsJavaFxImage(200,200));
//            imagenExperiencia.setStyle("-fx-alignment: CENTER;");// Me centra el texto

            imagenExperiencia.setX(75);
        }
        buttonExperiencia.setText(experiencia.getTituloExperiencia());

    }

    @FXML
    public void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(ExperienciaTemplate.class.getResourceAsStream("Template.fxml"));
        experienciaTemplate.setTemplete(experienceMgr.encontrarExperienciaPorTitulo(((Button) event.getSource()).getText()).getIdExperiencia());
//        mostrarFotosDinamicoTemplate.setDataExperienciasFotos(experienceMgr.encontrarExperienciaPorTitulo(((Button) event.getSource()).getText()).getIdExperiencia());
        principal.setearAnchorPane(root);

    }

}
