package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.TuristRepository;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class Template {


    @FXML
    private Image templateImage;

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
        templateTitulo.setText(experienceRepository.findOneByIdExperiencia(id).getTituloExperiencia());
        templateUbicacion.setText("Ubicacion: " + experienceRepository.findOneByIdExperiencia(id).getUbicacion());
        templateDescrpicion.setText("Descripcion: " + experienceRepository.findOneByIdExperiencia(id).getDescripcion());
        templateVideos.setText("Videos: " + experienceRepository.findOneByIdExperiencia(id).getLinkVideos());

    }









}
