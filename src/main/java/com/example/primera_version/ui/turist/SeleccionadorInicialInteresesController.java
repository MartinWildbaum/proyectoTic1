package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.business.entities.InteresParticular;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    @FXML
    public CheckComboBox<Interes> seleccionadorInteresesGenerales;

    @FXML
    public CheckComboBox<Interes> seleccionadorInteresesParticulares;

    @FXML
    public ScrollPane scrollInteresesGenerales;

    @FXML
    public ScrollPane scrollInteresesParticulares;

    @FXML
    public Text interesesGeneralesSeleccionados;

    @FXML
    public Text interesesParticularesSeleccionados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollInteresesGenerales.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollInteresesGenerales.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollInteresesParticulares.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollInteresesParticulares.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        interesesGeneralesSeleccionados.setText(null);
        interesesParticularesSeleccionados.setText(null);

        // Lo cargo con todos lo intereses generales
        for (InteresGeneral interesGeneral : interesMgr.getIntereseGenerales()){
            seleccionadorInteresesGenerales.getItems().add(interesGeneral);
        }

    }


    @FXML
    public void ajustarIntereses(MouseEvent mouseEvent){

        try{
            //Saco los intereses particulares que no tienen su interesgeneral seleccionado
            for(Interes interes: seleccionadorInteresesParticulares.getItems()){
                InteresParticular intpar = (InteresParticular) interes;
                if(!seleccionadorInteresesGenerales.getCheckModel().getCheckedItems().contains(intpar.getInteresGeneral())){
                    seleccionadorInteresesParticulares.getItems().remove(interes);
                }
            }

            //Cargo los intereses particulares de acuerdo a los generales que seleccione
            for(Interes interesGeneral: seleccionadorInteresesGenerales.getCheckModel().getCheckedItems()){
                InteresGeneral intgen = (InteresGeneral) interesGeneral;
                for (InteresParticular interesParticular: intgen.getInteresesParticularesAsociados()){
                    if(!seleccionadorInteresesParticulares.getItems().contains(interesParticular)){
                        seleccionadorInteresesParticulares.getItems().add(interesParticular);
                    }
                }
            }

            String textoGeneral = "";
            for (Interes txt : seleccionadorInteresesGenerales.getCheckModel().getCheckedItems()) {
                textoGeneral = textoGeneral + "\n" + txt.getNombre();
            }
            interesesGeneralesSeleccionados.setText(textoGeneral);

            String textoParticular = "";
            for (Interes txt : seleccionadorInteresesParticulares.getCheckModel().getCheckedItems()) {
                textoParticular = textoParticular + "\n" + txt.getNombre();
            }
            interesesParticularesSeleccionados.setText(textoParticular);
        }catch (Exception ignored){

        }


    }


    @FXML
    public void volverAddTurist(ActionEvent actionEvent) throws IOException {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
