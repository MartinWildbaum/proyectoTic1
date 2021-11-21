package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.MenuMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuTuristController implements Initializable {

    @Autowired
    MenuMgr menuMgr;

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private Principal principal;


    @FXML
    private GridPane experienciaGrid;

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private Button boton5;

    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
        //principal.setearAnchorPane(root);
    }


    @FXML
    void cerrarSesion(ActionEvent event) throws Exception {
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
    void bottonMisExperiencias(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MostrarExperiencias.class.getResourceAsStream("MostrarExperiencias.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow shadow = new DropShadow();
        boton1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton1.setEffect(shadow);
            }
        });
        boton1.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton1.setEffect(null);
            }
        });
        boton2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton2.setEffect(shadow);
            }
        });
        boton2.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton2.setEffect(null);
            }
        });
        boton3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton3.setEffect(shadow);
            }
        });
        boton3.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton3.setEffect(null);
            }
        });
        boton4.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton4.setEffect(shadow);
            }
        });
        boton4.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton4.setEffect(null);
            }
        });
        boton5.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton5.setEffect(shadow);
            }
        });
        boton5.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton5.setEffect(null);
            }
        });
    }


//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // Voy a darle las experiencias recomendadas ya en orden de relavancia desde el manager de forma que simplemente las exponga en orden
//        Queue<Experiencia> experienciasRecomendadas = menuMgr.asociadorExperiencias(turistMgr.encontrarTurista(principal.username.getText()));
//        int numExpAMostrar = experienciasRecomendadas.size();
//        int columns = 0;
//        int row = 1;
//        try{
//            for(int i = 0; i < numExpAMostrar;i++){
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
//                AnchorPane anchorPane = fxmlLoader.load(MostrarExperienciasDinamicoController.class.getResourceAsStream("MostrarExperienciasDinamico.fxml"));
//                MostrarExperienciasDinamicoController  mostrarExperienciasDinamicoController = fxmlLoader.getController();
//                mostrarExperienciasDinamicoController.setData(experienciasRecomendadas.remove());
//                if(columns == 1){
//                    columns = 0;
//                    ++row;
//                }
//                experienciaGrid.add(anchorPane,columns++,row);
//
//
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
