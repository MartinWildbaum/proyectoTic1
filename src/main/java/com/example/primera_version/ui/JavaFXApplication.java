package com.example.primera_version.ui;

import com.example.primera_version.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Esta de aca me controla la ventana del inicio

public class JavaFXApplication extends Application {

    private Parent root;

    @Override
    public void init() throws Exception {
        // Para que es esto?
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show(); // Me muestra la primera pantalla

    }

    @Override
    public void stop() {
        Main.getContext().close();
    }

}
