package com.example.primera_version.ui.tur_op_user;


import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.TurOpMgr;
import com.example.primera_version.business.TurOpUsersMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.exceptions.ExperienceAlreadyExists;
import com.example.primera_version.business.exceptions.InvalidExperienceInformation;
import com.example.primera_version.persistence.InterestRepository;
import com.example.primera_version.persistence.OpTurUsersRepository;
import com.example.primera_version.ui.Principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

@Component
public class AgregarExperienciaController implements Initializable {

    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private TurOpUsersMgr turOpUsersMgr;

    @Autowired
    private Principal principal;

    @Autowired
    private TurOpMgr turOpMgr;

    @Autowired
    private ExperienceMgr experienceMgr;

    @FXML
    private Button botonImagen;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtUbicacion;

    @FXML
    private TextField txtEnlacesRelacionados;

    @FXML
    private TextField txtAforoDisponible;

    @FXML
    private CheckComboBox<Interes> interesesRelacionados;

    private FileChooser fileChooser;

    private ArrayList<byte[]> imagenes = new ArrayList<>(5);

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
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
        //principal.setearAnchorPane(root);
    }

    @FXML
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("MenuTOUsers.fxml"));
        principal.setearAnchorPane(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        for(Interes interes : interesMgr.obtenerTodosIntereses()) {
            interesesRelacionados.getItems().add(interes);
        }

    }

    @FXML
    void agregarExperiencia(ActionEvent actionEvent){

        try{

            String titulo = txtTitulo.getText();
            String descripcion = txtDescripcion.getText();
            String ubicacion = txtUbicacion.getText();
            String aforoDisponible = txtAforoDisponible.getText();
            String enlacesRelacionados = txtEnlacesRelacionados.getText();
            ArrayList<byte[]> fotos = imagenes;
            Collection<Interes> interes = interesesRelacionados.getItems();

            if (imagenes == null) {

                showAlert("Todos los datos son obligatorios",
                        "Debes ingresar una imagen de la experiencia");
            }

            else {
                experienceMgr.addExperience(titulo, descripcion, enlacesRelacionados, ubicacion, fotos, interes, aforoDisponible, turOpUsersMgr.encontrarUnUsuariosOperadorTuristico(principal.username.getText()).getOperadorTuristico());
                // Cuando la agregue voy a tener que pasar el operador para el que trabaj el que la agrego
                showAlert("Experiencia registrada", "Se agrego exitosamente la experiencia!");
                volverAlMenu(actionEvent);


            }

        } catch(InvalidExperienceInformation e){
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        }catch (ExperienceAlreadyExists e){
            showAlert(
                    "Información invalida!",
                    "Ya hay una experiencia con este nombre");
        }catch (Exception e) {
            e.printStackTrace();
            showAlert(
                    "Algo salio mal!",
                    "Por favor, revise los datos ingresados"
            );
        }
    }

    @FXML
    public void addImagen(ActionEvent actionEvent) {
        Scene sceneActual =((Node)actionEvent.getSource()).getScene();
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
/*
        VBox vBox= new VBox();
        Scene scene = new Scene(vBox,960,600);
        stage.setScene(scene);
        stage.show();
        while(selectedFile == null);
        stage.setScene(sceneActual);
        stage.show();
*/
        while (selectedFile == null);
        Path url = selectedFile.toPath();
        //nombreImagen.setText(url.getFileName().toString());
        try {
            imagenes.add(Files.readAllBytes(url));
        }catch (IOException e){
        }
    }


}

