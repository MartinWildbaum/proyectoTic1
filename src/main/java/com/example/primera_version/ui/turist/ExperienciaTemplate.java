package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienciaTemplate {


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
    private ExperienceMgr experienceMgr;

    @Autowired
    private Principal principal;

//    @Autowired
//    private MenuTuristController menuTuristController;

    public void setTemplete(Long id){

        Experiencia experiencia_mostrada = experienceMgr.encontrarExperienciaPorId(id);
        templateTitulo.setText(experiencia_mostrada.getTituloExperiencia());
        templateUbicacion.setText("Ubicacion: " + experiencia_mostrada.getUbicacion());
        templateDescrpicion.setText("Descripcion: " + experiencia_mostrada.getDescripcion());
        templateVideos.setText("Videos: " + experiencia_mostrada.getLinkVideos());

        // Pogramar aparicion de imagenes
        if (experiencia_mostrada.getImagenes().size() > 0){
            templateImage.setImage(experiencia_mostrada.getImagenes().iterator().next().getImagenAsJavaFxImage((int) templateImage.getFitHeight(),(int) templateImage.getFitWidth()));
        }


    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        principal.setearAnchorPane(root);
    }

}
