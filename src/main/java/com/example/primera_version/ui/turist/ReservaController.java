package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.ReservaMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Turist;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.ReservaNoDisponible;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class ReservaController implements Initializable {


    @FXML
    private ChoiceBox<String> txtTipoDeDocumento;

    @FXML
    private TextField txtNumeroDeDocumento;

    @FXML
    private TextField txtCantidadDePersonas;

    @FXML
    private DatePicker fechaReserva;

    @Autowired
    private Principal principal;

    @Autowired
    private ExperienciaTemplate experienciaTemplate;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private TuristMgr turistMgr;


    @FXML
    public void volverAlMenu(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void addReserva(ActionEvent event) {
        long cantPersonas = 0l;
        try{
            cantPersonas = Long.parseLong(txtCantidadDePersonas.getText());
        }catch(Exception e){
            showAlert(
                    "Error en los datos!",
                    "La cantidad de personas debe ser un numero positivo.");
        }

        if(cantPersonas <= 0 || txtTipoDeDocumento.getValue()==null || txtNumeroDeDocumento.getText()==null || txtNumeroDeDocumento.getText().equals("") || txtCantidadDePersonas.getText()==null || txtCantidadDePersonas.getText().equals("") || fechaReserva == null || fechaReserva.getValue().isBefore(LocalDate.now())){
            showAlert(
                    "Error en los datos!",
                    "Por favor ingrese los datos correctamente.");
        }else{

            String tipoDeDoc = txtTipoDeDocumento.getValue();
            String numeroDeDoc= txtNumeroDeDocumento.getText();;
            //Long cantidadPersonas= Long.valueOf((txtCantidadDePersonas.getText()));
            LocalDate fecha = fechaReserva.getValue();

            //Llamo a la funcion para ver si puedo hacer la reserva

            try {
                reservaMgr.agregarReserva(principal.username.getText(),experienciaTemplate.templateTitulo.getText(), tipoDeDoc, numeroDeDoc, cantPersonas, fecha);
                showAlert("Reserva agregada", "Se agrego con exito la Reserva!");
                close(event);



            } catch (InvalidUserInformation invalidUserInformation) {
                showAlert(
                        "Informacion invalida !",
                        "Se encontro un error en los datos ingresados.");

            } catch (ReservaNoDisponible reservaNoDisponible) {
                showAlert(
                        " Reserva No Disponible !",
                        "Intente más tarde.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] tiposDeDocumentos={"Pasaporte","Cédula"};
        txtTipoDeDocumento.getItems().addAll(tiposDeDocumentos);
        Turist turist = turistMgr.encontrarTurista(principal.username.getText());
        if(turist.getValorDocumento() != null){ // Ya realizo alguna reserva
            txtTipoDeDocumento.getSelectionModel().select(turist.getTipoDocumento());
            txtNumeroDeDocumento.setText(turist.getValorDocumento());
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(MenuTuristController.class.getResourceAsStream("MenuTurist.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
