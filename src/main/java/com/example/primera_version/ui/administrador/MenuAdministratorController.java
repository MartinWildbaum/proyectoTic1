package com.example.primera_version.ui.administrador;


import com.example.primera_version.Main;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.persistence.ExperienceRepository;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.StyledEditorKit;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;


@Component
public class MenuAdministratorController implements Initializable {


    @Autowired
    private ExperienceRepository experienceRepository;


    @FXML
    private Button botonAgregarOperador;


    @FXML
    private TextField campoBusqueda;

    @FXML
    private TableView<Experiencia> experienciasExpuestas;

    @FXML
    private TableColumn<Experiencia, String> tituloExpriencia;

    @FXML
    private TableColumn<Experiencia, String> descripcionExperiencia;

    @FXML
    private TableColumn<Experiencia, String> idExperiencia;

    @FXML
    private TableColumn<Experiencia, String> ubicacionExperiencia;

//    @FXML
//    private TableColumn<Experiencia, Collection<Interes>> interesesExperiencia;

    @FXML
    private TableColumn<Experiencia, String> aforoExperiencia;

//    @FXML
//    private TableColumn<Experiencia, OperadorTuristico> operadorExperiencia; // Refiere al nombre del operador turistico

    @FXML
    private TableColumn<Experiencia, String> estadoExperiencia;


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


    ObservableList<Experiencia> lista;

    @FXML
    void busquedaDinamica(KeyEvent event){
        List<Experiencia> query = (List<Experiencia>) experienceRepository.findAllByTituloExperienciaContaining(campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        experienciasExpuestas.setItems(lista);
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));

        descripcionExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            String descripcion_experiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getDescripcion();
            return new ReadOnlyStringWrapper(descripcion_experiencia);
        });

        idExperiencia.setCellValueFactory(cellData -> {
            Long idExp = cellData.getValue().getIdExperiencia();
            return new ReadOnlyStringWrapper(String.valueOf(idExp));
        });

        ubicacionExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            String ubicacionExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getUbicacion();
            return new ReadOnlyStringWrapper(ubicacionExperiencia);
        });

        aforoExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            Integer aforoExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getCantidad();
            return new ReadOnlyStringWrapper(String.valueOf(aforoExperiencia));
        });

        estadoExperiencia.setCellValueFactory(cellData -> {
            Long idExperiencia = cellData.getValue().getIdExperiencia();
            Boolean estadoExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getEstadoExperiencia();
            String estado = "Bloqueado";
            if(estadoExperiencia == null || estadoExperiencia){
                estado = "Autorizado";
            }
            return new ReadOnlyStringWrapper(estado);
        });
    }




    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());
        List<Experiencia> query = (List<Experiencia>) experienceRepository.findAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        experienciasExpuestas.setItems(lista);
        idExperiencia.setCellValueFactory((new PropertyValueFactory<>("idExperiencia")));
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ubicacionExperiencia.setCellValueFactory((new PropertyValueFactory<>("ubicacion")));
        aforoExperiencia.setCellValueFactory((new PropertyValueFactory<>("cantidad")));
        estadoExperiencia.setCellValueFactory((new PropertyValueFactory<>("estadoExperiencia")));

    }



    @FXML
    void agregarOTAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(TOController.class.getResourceAsStream("AddOperadorTuristico.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
