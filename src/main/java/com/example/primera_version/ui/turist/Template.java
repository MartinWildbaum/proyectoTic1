package com.example.primera_version.ui.turist;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.persistence.ExperienceRepository;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class Template {


    @FXML
    private ImageView templateImage;

    @FXML
    private Text templateTitulo;

    @FXML
    private Text templateUbicacion;

    @FXML
    private Text templateDescrpicion;

    @FXML
    private Text templateVideos;

    @Autowired
    private ExperienceRepository experienceRepository;

    public void setTemplete(Long id){

        // ACA VAMOS A TENER QUE BUSCAR TODO EN LA BASE DE DATOS Y IR SETEANDO
        Experiencia experiencia_mostrada = experienceRepository.findOneByIdExperiencia(id);
        templateTitulo.setText(experiencia_mostrada.getTituloExperiencia());
        templateUbicacion.setText("Ubicacion: " + experiencia_mostrada.getUbicacion());
        templateDescrpicion.setText("Descripcion: " + experiencia_mostrada.getDescripcion());
        templateVideos.setText("Videos: " + experiencia_mostrada.getLinkVideos());
        templateImage.setImage(experienceRepository.findOneByIdExperiencia(id).getImagenAsJavaFxImage((int) templateImage.getFitHeight(),(int) templateImage.getFitWidth()));



    }



}
