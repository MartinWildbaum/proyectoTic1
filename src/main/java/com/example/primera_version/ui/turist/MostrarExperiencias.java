package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.InteresMgr;
import com.example.primera_version.business.MenuMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.business.entities.InteresGeneral;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;

@Component
public class MostrarExperiencias implements Initializable  {

    @Autowired
    MenuMgr menuMgr;

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private ExperienceMgr experienceMgr;

    @Autowired
    private MenuTuristController menuTuristController;

    @Autowired
    private MostrarExperienciasDinamicoController mostrarExperienciasDinamicoController;

    @Autowired
    private Principal principal;

    @FXML
    private ImageView cartelMon;

    @FXML
    private GridPane experienciaGrid;

    @FXML
    private Button volverMenu;


    @FXML
    private ScrollPane scrollExperiencias;

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
    void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        //principal.setearAnchorPane(root);
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollExperiencias.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollExperiencias.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        efectoFotos(cartelMon);

        Queue<Experiencia> experienciasRecomendadas;
        if (menuTuristController.getApretado().getText().equals("Mis Experiencias")){
            // Voy a darle las experiencias recomendadas ya en orden de relavancia desde el manager de forma que simplemente las exponga en orden
            experienciasRecomendadas = menuMgr.asociadorExperiencias(turistMgr.encontrarTurista(principal.getUsername().getText()));

        }else{

            experienciasRecomendadas = new PriorityQueue<>(10);
            experienciasRecomendadas.addAll(experienceMgr.encontrarTodasPorInteresGeneral(menuTuristController.getApretado().getText()));
        }

        int numExpAMostrar = experienciasRecomendadas.size();
        int columns = 0;
        int row = 1;
        try{
            for(int i = 0; i < numExpAMostrar;i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(MostrarExperienciasDinamicoController.class.getResourceAsStream("MostrarExperienciasDinamico.fxml"));
                MostrarExperienciasDinamicoController  mostrarExperienciasDinamicoController = fxmlLoader.getController();
                mostrarExperienciasDinamicoController.setData(experienciasRecomendadas.remove());
                if(columns == 1){
                    columns = 0;
                    ++row;
                }
                experienciaGrid.add(anchorPane,columns++,row);


            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void efectoFotos(ImageView imagenxp){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        imagenxp.addEventFilter(MouseEvent.MOUSE_ENTERED, e->{
            imagenxp.setEffect(colorAdjust);
        });
        imagenxp.addEventFilter(MouseEvent.MOUSE_EXITED, e->{
            imagenxp.setEffect(null);
        });
    }
}
