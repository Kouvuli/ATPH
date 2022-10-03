package Utils;

import javafx.scene.control.Alert;

public class DialogUtil {
    public void showAlert(String title,String content,Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
