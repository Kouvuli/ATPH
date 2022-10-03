package Controllers;

import DAO.AccountDAO;
import Entities.Account;
import Utils.DialogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OTPController implements Initializable {
    @FXML
    private PasswordField passwordTxt;

    private String pass;

    private DialogUtil dialogUtil;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialogUtil=new DialogUtil();
    }


    @FXML
    void confirmHandler(ActionEvent event) throws IOException {

        if(!passwordTxt.getText().equals(pass)){
            dialogUtil.showAlert("Error","Incorrect password!", Alert.AlertType.ERROR);
            Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
//            window.close();
//            System.exit(0);
            return ;
        }
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(OTPController.class.getResource("/layouts/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public void setValue(String pass){
        this.pass=pass;
    }
}
