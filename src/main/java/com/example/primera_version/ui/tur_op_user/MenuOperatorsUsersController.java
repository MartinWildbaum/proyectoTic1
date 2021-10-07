package com.example.primera_version.ui.tur_op_user;



import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;




@Component
public class MenuOperatorsUsersController {


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
