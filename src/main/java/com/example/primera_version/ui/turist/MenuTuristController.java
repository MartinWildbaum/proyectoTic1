package com.example.primera_version.ui.turist;

import com.example.primera_version.Main;
import com.example.primera_version.business.ExperienceMgr;
import com.example.primera_version.business.MenuMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Experiencia;
import com.example.primera_version.ui.Principal;
import com.example.primera_version.ui.administrador.MenuAdministradorController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.NotSerializableException;
import java.net.URL;
import java.util.*;

@Component
public class MenuTuristController implements Initializable{

    @Autowired
    MenuMgr menuMgr;

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private Perfil perfil;

    @Autowired
    private Principal principal;

    @Autowired
    private MostrarExperienciasDinamicoController mostrarExperienciasDinamicoController;

    @FXML
    private GridPane experienciaGrid;

    @FXML
    void visitarTuPerfil(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Perfil.class.getResourceAsStream("Perfil.fxml"));
        //perfil.setInformacionUsuario(principal.username.getText());
        principal.setearAnchorPane(root);
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
        // Voy a darle las experiencias recomendadas ya en orden de relavancia desde el manager de forma que simplemente las exponga en orden
        ArrayList<Experiencia> experienciasRecomendadas = menuMgr.asociadorExperiencias(turistMgr.encontrarTurista(principal.username.getText()));
        int columns = 0;
        int row = 1;
        try{
            for(int i=0;i< experienciasRecomendadas.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                AnchorPane anchorPane = fxmlLoader.load(MostrarExperienciasDinamicoController.class.getResourceAsStream("MostrarExperienciasDinamico.fxml"));
                MostrarExperienciasDinamicoController  mostrarExperienciasDinamicoController = fxmlLoader.getController();
                mostrarExperienciasDinamicoController.setData(experienciasRecomendadas.get(i));
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
}
