package com.example.primera_version.ui.turist;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.ui.Principal;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




@Component
public class ImagenesTemplateController {

    @FXML
    private ImageView imagenExperiencias;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private ExperienciaTemplate experienciaTemplate;

    public void setDataImagenesTemplate(Experiencia experiencia){
//        experiencia.getImagenes().iterator().next()
        imagenExperiencias.setImage(experiencia.getImagenes().iterator().next().getImagenAsJavaFxImage(200,200));


    }

}
