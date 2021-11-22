package com.example.primera_version.ui.turist;
import com.example.primera_version.Main;
import com.example.primera_version.business.PaisMgr;
import com.example.primera_version.business.TuristMgr;
import com.example.primera_version.business.entities.Interes;
import com.example.primera_version.business.exceptions.InvalidUserInformation;
import com.example.primera_version.business.exceptions.PasswordNoCoinciden;
import com.example.primera_version.business.exceptions.UserAlreadyExists;
import com.example.primera_version.ui.Principal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;


@Component
public class AddTuristController implements Initializable {

    @Autowired
    Principal principal;

    @Autowired
    private TuristMgr turistMgr;

    @Autowired
    private PaisMgr paisMgr;

    @Autowired
    private SeleccionadorInicialInteresesController seleccionadorInicialInteresesController;

    @FXML
    private TextField txtMail;

    @FXML
    private DatePicker txtBirthdate;

    @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private ComboBox<String> myComboBoxPaises;

    @FXML
    private Button agregar;

    @FXML
    private Button cancelar;

    @FXML
    private Button seleccionarIntereses;

    @FXML
    void addTurist(ActionEvent event) {
        if (txtMail.getText() == null || txtMail.getText().equals("")   || txtBirthdate == null   || txtBirthdate.getValue().isAfter(LocalDate.now()) || txtPassword1.getText()==null || txtPassword1.getText().equals("") || txtPassword2.getText()==null || txtPassword2.getText().equals("") || myComboBoxPaises.getValue()==null) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                String pais = myComboBoxPaises.getValue();
                String mail = txtMail.getText();
                LocalDate birthdate = txtBirthdate.getValue();
                String password = txtPassword1.getText();
                String password2= txtPassword2.getText();

                Collection<Interes> interesesGenerales = seleccionadorInicialInteresesController.seleccionadorInteresesGenerales.getCheckModel().getCheckedItems();
                Collection<Interes> interesesParticulares = seleccionadorInicialInteresesController.seleccionadorInteresesParticulares.getCheckModel().getCheckedItems();


                try {

                    turistMgr.addTurist(mail, pais, birthdate, password,password2,interesesGenerales, interesesParticulares);
                    showAlert("Turista agregado", "Se agrego con exito el Turista!");
                    close(event);

                } catch (InvalidUserInformation invalidTuristInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (UserAlreadyExists turistAlreadyExists) {
                    showAlert(
                            "Mail ya registrado !",
                            "El mail indicado ya ha sido registrado en el sistema.");
                } catch (PasswordNoCoinciden passwordNoCoinciden) {
                    showAlert(
                            "Error en la contraseña !",
                            "Las contraseñas no coinciden.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }


    @FXML
    void close(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        AnchorPane root = fxmlLoader.load(Principal.class.getResourceAsStream("Principal.fxml"));
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (String nombrePais : paisMgr.getPaises()){
            myComboBoxPaises.getItems().add(nombrePais);
        }
        DropShadow shadow = new DropShadow();
        animarBoton(agregar, shadow);
        animarBoton(cancelar, shadow);
        animarBoton(seleccionarIntereses, shadow);

        }

    @FXML
    public void irASeleccionarIntereses(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        Parent root = fxmlLoader.load(SeleccionadorInicialInteresesController.class.getResourceAsStream("SeleccionadorInicialIntereses.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }


    private void animarBoton(Button boton, DropShadow dropShadow){

        boton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(dropShadow);
            }
        });
        boton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boton.setEffect(null);
            }
        });
    }

}
