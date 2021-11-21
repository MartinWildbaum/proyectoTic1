package com.example.primera_version.ui.turist;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Imagen;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;




@Component
public class ImagenesTemplateController {

    @FXML
    private ImageView imagenExperiencias;

    public void setDataImagenesTemplate(Imagen imagen){
        imagenExperiencias.setImage(imagen.getImagenAsJavaFxImage(200,200));
        //imagenExperiencias.setImage(experiencia.getImagenes().iterator().next().getImagenAsJavaFxImage(200,200));
        imagenExperiencias.setStyle("-fx-alignment: CENTER;");
        efectoFotos(imagenExperiencias);
    }


    public void efectoFotos(ImageView imagenxp){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        imagenxp.addEventFilter(MouseEvent.MOUSE_ENTERED, e->{
            imagenxp.setEffect(colorAdjust);
        });
        imagenxp.addEventFilter(MouseEvent.MOUSE_EXITED, e->{
            imagenxp.setEffect(null);
        });
    }
}
