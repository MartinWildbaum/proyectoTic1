package com.example.primera_version.ui.turist;


import com.example.primera_version.Main;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.ui.Principal;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MenuTuristController implements Initializable {



    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @Autowired
    private Template template;

    @FXML
    private Button irExperiencia;

    @FXML
    private Image  myImage;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private TableView<Experiencia> experienciasOfrecidas;

    @FXML
    private TableColumn<Experiencia, String> tituloExpriencia;

    @FXML
    private TableColumn<Experiencia, String> descripcionExperiencia;


    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        perfil.informacionUsuario(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        //funcion para visitar tu perfil
    }



    @FXML
    void irExperiencia(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        closeVentana(event);
        Parent root = fxmlLoader.load(Template.class.getResourceAsStream("Template.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        template.setTemplete((experienceRepository.findOneByTituloExperiencia(irExperiencia.getText()).getIdExperiencia()));
        stage.show();

    }

    ObservableList<Experiencia> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());
        List<Experiencia> query = (List<Experiencia>) experienceRepository.findAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        experienciasOfrecidas.setItems(lista);
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

    }


    @FXML
    void busquedaDinamica(KeyEvent event){
        List<Experiencia> query = (List<Experiencia>) experienceRepository.findAllByTituloExperienciaContaining(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        experienciasOfrecidas.setItems(lista);
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            String descripcion_experiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getDescripcion();
            return new ReadOnlyStringWrapper(descripcion_experiencia);
        });
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void closeVentana(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }



}
