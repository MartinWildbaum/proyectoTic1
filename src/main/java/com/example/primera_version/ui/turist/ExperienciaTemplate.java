package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ExperienciaTemplate implements Initializable {


    @FXML
    public Text templateTitulo;

    @FXML
    private Text templateUbicacion;

    @FXML
    private Text templateDescrpicion;

    @FXML
    private Text templateVideos;

    @FXML
    private GridPane templateFotos;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private MostrarExperienciasDinamicoController mostrarExperienciasDinamicoController;




    public void setTemplete(Long id){
        Experiencia experiencia_mostrada = experienceMgr.encontrarExperienciaPorId(id);
        templateTitulo.setText(experiencia_mostrada.getTituloExperiencia());
        templateUbicacion.setText("Ubicacion: " + experiencia_mostrada.getUbicacion());
        templateDescrpicion.setText("Descripcion: " + experiencia_mostrada.getDescripcion());
        templateVideos.setText("Videos: " + experiencia_mostrada.getLinkVideos());

    }

    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
        //principal.setearAnchorPane(root);
    }


    @FXML
    void cerrarSesion(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void realizarReserva(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(ReservaController.class.getResourceAsStream("ReservaTurist.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Experiencia experiencia_mostrada = mostrarExperienciasDinamicoController.experienciaASetear;
        setTemplete(experiencia_mostrada.getIdExperiencia());
        //Experiencia experiencia_mostrada = experienceMgr.encontrarExperienciaPorTitulo(templateTitulo.getText());
        //no me esta encontrando la experiencia
        int cantidadDeFotos = experiencia_mostrada.getImagenes().size();
        int columns = 0;
        int row = 1;
        try{
            for(int i = 0; i < cantidadDeFotos;i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(ImagenesTemplateController.class.getResourceAsStream("ImagenesTemplate.fxml"));
                ImagenesTemplateController  imagenesTemplateController = fxmlLoader.getController();
                imagenesTemplateController.setDataImagenesTemplate(experiencia_mostrada);
                if(columns == 1){
                    columns = 0;
                    ++row;
                }
                templateFotos.add(anchorPane,columns++,row);


            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
