package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.MenuMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.TuristRepository;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class MenuTuristController implements Initializable{

    @Autowired
    MenuMgr menuMgr;

    @Autowired
    private TuristRepository turistRepository;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @FXML
    private GridPane experienciaGrid;



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
    }


    @FXML
    void cerrarSesion(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Experiencia> experienciasRecomendadas = menuMgr.asociadorExperiencias(turistRepository.findOneByMail(principal.username.getText()));
        int columns=0;
        int row=1;
        try{
            for(int i=0;i< experienciasRecomendadas.size();i++){
                FXMLLoader fxmlLoader=new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("MostrarExperienciasDinamico.fxml"));
                AnchorPane anchorPane= fxmlLoader.load();
                MostrarExperienciasDinamicoController  mostrarExperienciasDinamicoController= fxmlLoader.getController();
                mostrarExperienciasDinamicoController.setData(experienciasRecomendadas.get(i));
                if(columns==2){
                    columns=0;
                    ++row;
                }
                experienciaGrid.add(anchorPane,columns++,row);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
