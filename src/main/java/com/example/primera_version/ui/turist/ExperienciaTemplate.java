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
import javafx.scene.image.ImageView;
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

//    @Autowired
//    private MenuTuristController menuTuristController;

    public void setTemplete(Long id){

        Experiencia experiencia_mostrada = experienceMgr.encontrarExperienciaPorId(id);
        templateTitulo.setText(experiencia_mostrada.getTituloExperiencia());
        templateUbicacion.setText("Ubicacion: " + experiencia_mostrada.getUbicacion());
        templateDescrpicion.setText("Descripcion: " + experiencia_mostrada.getDescripcion());
        templateVideos.setText("Videos: " + experiencia_mostrada.getLinkVideos());
        templateImage.setImage(experiencia_mostrada.getImagenAsJavaFxImage((int) templateImage.getFitHeight(),(int) templateImage.getFitWidth()));

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
