package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.business.entities.InteresParticular;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

@Component
public class SeleccionadorInicialInteresesController implements Initializable {



    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private TuristMgr turistMgr;

    @FXML
    public CheckComboBox<InteresGeneral> seleccionadorInteresesGenerales;

    @FXML
    public CheckComboBox<InteresParticular> seleccionadorInteresesParticulares;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lo cargo con todos lo intereses
        for (InteresGeneral interesGeneral : interesMgr.getIntereseGenerales()){
            seleccionadorInteresesGenerales.getItems().add(interesGeneral);
        }
        for (InteresParticular interesParticular : interesMgr.getInteresParticular()){
            seleccionadorInteresesParticulares.getItems().add(interesParticular);
        }

    }

    @FXML
    public void volverAddTurist(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(AddTuristController.class.getResourceAsStream("AddTurist.fxml"));
        principal.setearAnchorPane(root);


    }
}
