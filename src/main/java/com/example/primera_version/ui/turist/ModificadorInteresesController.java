package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.business.entities.InteresParticular;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;


@Component
public class ModificadorInteresesController implements Initializable {

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @FXML
    private Button guardarCambios;

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

        Turist turist = turistMgr.encontrarTurista(principal.username.getText());

        // Cargo los intereses generales con todos los intereses generales que tengo.
        for (Interes intereseGeneralAgregar: interesMgr.getIntereseGenerales()) {
            seleccionadorInteresesGenerales.getItems().add(intereseGeneralAgregar);
        }

        // Selecciono los intereses con los que ya habia seleccionado

        Collection<Interes> interesesAgregar = turist.getIntereses();
        Collection<InteresGeneral> interGenerales = new ArrayList<>(10); // Intereses generales del turista
        Collection<InteresParticular> interParticulares = new ArrayList<>(10);// Intereses particulares del turista

        for (Interes inter: interesesAgregar) {
            if (inter instanceof InteresGeneral){
                interGenerales.add((InteresGeneral) inter);
            }else{// Seguro es un interes particular
                interParticulares.add((InteresParticular) inter);
            }
        }
        for (InteresGeneral intgen: interGenerales) {
            seleccionadorInteresesGenerales.getCheckModel().check(intgen);
            Collection<InteresParticular> intspars = intgen.getInteresesParticularesAsociados();
            for (InteresParticular intpar: intspars) { // Me agrego los intereses articulares correspondientes
                seleccionadorInteresesParticulares.getItems().add(intpar);
                /*if(interParticulares.contains(intpar)){ // Si lo tenia marcado me lo chequea
                    seleccionadorInteresesParticulares.getCheckModel().check(intpar);
                }*/
            }
        }
        for (InteresParticular interesParticular:interParticulares) {
            seleccionadorInteresesParticulares.getCheckModel().check(interesParticular);
        }
        /*
        for (Interes interes: interesesAgregar) {
            if(interes instanceof InteresGeneral){
                seleccionadorInteresesGenerales.getCheckModel().check((InteresGeneral) interes);
            }else{
                seleccionadorInteresesParticulares.getCheckModel().check((InteresParticular) interes);
            }
        }*/
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
    void cerrarSesion(ActionEvent event) throws Exception{
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
    void volver(ActionEvent event) throws Exception{

        Collection<Interes> interesesNuevos = new ArrayList<>(10);
        interesesNuevos.addAll(seleccionadorInteresesGenerales.getCheckModel().getCheckedItems());
        interesesNuevos.addAll(seleccionadorInteresesParticulares.getCheckModel().getCheckedItems());
        Turist turist = turistMgr.encontrarTurista(principal.username.getText());
        turist.setIntereses(interesesNuevos);
        turistMgr.actualizarTurista(turist);
        perfil.setInformacionUsuario();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }
/*
    @FXML
    void GuardarCambiosyVolver(ActionEvent event) throws Exception{
        // Realizo los cambios

        Collection<Interes> interesesNuevos = new ArrayList(10);
        interesesNuevos.addAll(seleccionadorInteresesGenerales.getCheckModel().getCheckedItems());
        interesesNuevos.addAll(seleccionadorInteresesParticulares.getCheckModel().getCheckedItems());
        Turist turist = turistMgr.encontrarTurista(principal.username.getText());
        turist.setIntereses(interesesNuevos);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        principal.setearAnchorPane(root);
    }
*/
}
