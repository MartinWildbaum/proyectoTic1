package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.entities.Experiencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class MostrarFotosDinamicoTemplate implements Initializable {

    @FXML
    private ImageView imagenExperiencias;

    @Autowired
    private ExperienceMgr experienceMgr;

//    public void setDataExperienciasFotos(Long id){
//        Experiencia experiencia_mostrada = experienceMgr.encontrarExperienciaPorId(id);
//        imagenExperiencias.setImage(experiencia_mostrada.getImagenes().iterator().next().getImagenAsJavaFxImage(200,200));
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
