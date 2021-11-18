package com.example.primera_version.ui.tur_op_user;

import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.business.entities.InteresParticular;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.turist.AddTuristController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@Component
public class SeleccionadorInteresesExperiencia implements Initializable {

    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private Principal principal;

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
        for (InteresGeneral interesGeneral : interesMgr.getIntereseGenerales()) {
            seleccionadorInteresesGenerales.getItems().add(interesGeneral);
        }
    }

    @FXML
    public void ajustarInt(MouseEvent mouseEvent){
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

        //Cargo los intereses particulares de acuerdo a los generales que seleccione
        for(Interes interesGeneral: seleccionadorInteresesGenerales.getCheckModel().getCheckedItems()){
            InteresGeneral intgen = (InteresGeneral) interesGeneral;
            for (InteresParticular interesParticular: intgen.getInteresesParticularesAsociados()){
                if(!seleccionadorInteresesParticulares.getItems().contains(interesParticular)){
                    seleccionadorInteresesParticulares.getItems().add(interesParticular);
                }
            }
        }

    }


    @FXML
    public void volverAddExperience(ActionEvent actionEvent) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(AddTuristController.class.getResourceAsStream("AddTurist.fxml"));
        principal.setearAnchorPane(root);*/
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
}
