package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MostrarExperienciasDinamicoController {

    @FXML
    private ImageView imagenExperiencia;

    @FXML
    private Button buttonExperiencia;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private ExperienciaTemplate experienciaTemplate;

    private Experiencia experienciaASetear;

    public void setData(Experiencia experiencia){

        if (experiencia.getImagenes().size() > 0){
            imagenExperiencia.setImage(experiencia.getFotoPortadaAsJavaFxImage(200,200));
            imagenExperiencia.setStyle("-fx-alignment: CENTER;");// Me centra el texto


//            imagenExperiencia.setX(75);
        }
        efectoFotos(imagenExperiencia);
        buttonExperiencia.setText(experiencia.getTituloExperiencia());
        DropShadow shadow = new DropShadow();
        animarBoton(buttonExperiencia, shadow);

    }

    @FXML
    public void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        experienciaASetear = experienceMgr.encontrarExperienciaPorTitulo(((Button) event.getSource()).getText());
        //experienciaTemplate.setTemplete(experienceMgr.encontrarExperienciaPorTitulo(((Button) event.getSource()).getText()).getIdExperiencia());
        AnchorPane root = fxmlLoader.load(ExperienciaTemplate.class.getResourceAsStream("Template.fxml"));
        Node source = (Node) event.getSource();;
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
//        mostrarFotosDinamicoTemplate.setDataExperienciasFotos(experienceMgr.encontrarExperienciaPorTitulo(((Button) event.getSource()).getText()).getIdExperiencia());
        //principal.setearAnchorPane(root);

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

    private void animarBoton(Button boton, DropShadow dropShadow){

        boton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(dropShadow);
            }
        });
        boton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(null);
            }
        });
    }

    public Experiencia getExperienciaASetear() {
        return experienciaASetear;
    }

    public void setExperienciaASetear(Experiencia experienciaASetear) {
        this.experienciaASetear = experienciaASetear;
    }
}
