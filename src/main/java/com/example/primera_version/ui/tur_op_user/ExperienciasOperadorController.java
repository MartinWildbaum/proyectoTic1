package com.example.primera_version.ui.tur_op_user;


import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.TurOpUsersMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.entities.OperadorTuristico;
import com.example.primera_version.business.entities.UsuarioOpTur;
import com.example.primera_version.persistence.ExperienceRepository;
import com.example.primera_version.persistence.OpTurUsersRepository;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.administrador.MenuAdministradorController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ExperienciasOperadorController implements  Initializable{

    @Autowired
    private Principal principal;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private TurOpUsersMgr turOpUsersMgr;

    @FXML
    private TextField campoBusqueda;

    @FXML
    public TableView<Experiencia> misExperiencias;

    @FXML
    private TableColumn<Experiencia, String> tituloExpriencia;

    @FXML
    private TableColumn<Experiencia, String> descripcionExperiencia;

    @FXML
    private TableColumn<Experiencia, String> idExperiencia;

    @FXML
    private TableColumn<Experiencia, String> ubicacionExperiencia;

    @FXML
    private TableColumn<Experiencia, Collection<Interes>> interesesExperiencia;

    @FXML
    private TableColumn<Experiencia, String> aforoExperiencia;

    @FXML
    private TableColumn<Experiencia, Boolean> estaDisponible;


    ObservableList<Experiencia> lista;

    @FXML
    void busquedaDinamica(KeyEvent event){
        OperadorTuristico opTuri = turOpUsersMgr.encontrarOperadorTuristicoParaElQueTrabaja(principal.username.getText());
        List<Experiencia> query = (List<Experiencia>) experienceMgr.encontrarTodasPorTituloYOperador(opTuri, campoBusqueda.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(query);
        misExperiencias.setItems(lista);
        /*
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

//        estadoExperiencia.setCellValueFactory(cellData -> {
//            Long idExperiencia = cellData.getValue().getIdExperiencia();
//            Boolean estadoExperiencia = experienceRepository.findOneByIdExperiencia(idExperiencia).getEstadoExperiencia();
//            String estado = "Bloqueado";
//            if(estadoExperiencia == null || estadoExperiencia){
//                estado = "Autorizado";
//            }
//            return new ReadOnlyStringWrapper(estado);
//        });

         */
    }




    @Override
    public void initialize(URL location, ResourceBundle resources){ // Lo que hace es levantar de una cuando se llama a la clase
        //username_label.setText(cliente.getUsername());
        //Agarro todas las experiencias del operador turistico para el que trabaja el operador que ingreso.
        List<Experiencia> query = (List<Experiencia>) experienceMgr.encontrarTodasPorOperadorTuristico((turOpUsersMgr.encontrarUnUsuariosOperadorTuristico(principal.username.getText())).getOperadorTuristico());
        lista = FXCollections.observableArrayList();
        lista.addAll(query);
        misExperiencias.setItems(lista);
        idExperiencia.setStyle("-fx-alignment: CENTER;");
        tituloExpriencia.setStyle("-fx-alignment: CENTER;");
        descripcionExperiencia.setStyle("-fx-alignment: CENTER;");
        ubicacionExperiencia.setStyle("-fx-alignment: CENTER;");
        aforoExperiencia.setStyle("-fx-alignment: CENTER;");
        interesesExperiencia.setStyle("-fx-alignment: CENTER;");

        idExperiencia.setCellValueFactory((new PropertyValueFactory<>("idExperiencia")));
        tituloExpriencia.setCellValueFactory(new PropertyValueFactory<>("tituloExperiencia"));
        descripcionExperiencia.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ubicacionExperiencia.setCellValueFactory((new PropertyValueFactory<>("ubicacion")));
        aforoExperiencia.setCellValueFactory((new PropertyValueFactory<>("cantidad")));
        interesesExperiencia.setCellValueFactory(new PropertyValueFactory<>("intereses"));

        //estadoExperiencia.setCellValueFactory((new PropertyValueFactory<>("estadoExperiencia")));
        estaDisponible.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Experiencia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Experiencia, Boolean> param) {
                Experiencia experiencia = param.getValue();

                SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(experiencia.getEstaDisponible());

                //estadoExperiencia.setOnEditCommit();

                booleanProperty.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        experiencia.setEstaDisponible(newValue);
                    }
                });

                return booleanProperty;
            }
        });
        estaDisponible.setCellFactory(new Callback<TableColumn<Experiencia, Boolean>, TableCell<Experiencia, Boolean>>() {
            @Override
            public TableCell<Experiencia, Boolean> call(TableColumn<Experiencia, Boolean> param) {
                CheckBoxTableCell<Experiencia,Boolean> cell = new CheckBoxTableCell<Experiencia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
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
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuOperatorsUsersController.class.getResourceAsStream("MenuTOUsers.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void verReservas(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Reservas_Operador.class.getResourceAsStream("ReservasTO.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }



}
