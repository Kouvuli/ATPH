package Controllers;

import DAO.AccountDAO;
import Entities.Account;
import Utils.CryptoUtil;
import Utils.DialogUtil;
import Utils.FileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {

    @FXML
    private PasswordField passwordTxt;

    private String path;
    private String name;
    CryptoUtil cryptoUtil;
    FileUtil fileUtil;
    DialogUtil dialogUtil;
    @FXML
    void cancelHandler(ActionEvent event) {
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void confirmHandler(ActionEvent event) throws IOException {

        AccountDAO dao=new AccountDAO();
        Account account=dao.getAccountByFilePathAndFileName(path,name);
        if(!BCrypt.checkpw(passwordTxt.getText(),account.getPassword())){
          dialogUtil.showAlert("Error","Incorrect password!", Alert.AlertType.ERROR);
          Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
          window.close();
          return ;
        }

        File file = new File(path + "/" + name);
        String content=fileUtil.readFile(file);
        String decryptContent=cryptoUtil.decryptData(passwordTxt.getText(),account.getCreateAt(),content);
        file.setWritable(true);
        dao.delData(account);
        fileUtil.writeFile(decryptContent,file);
        if(!file.canWrite()){
            dialogUtil.showAlert("Warning","Cannot unlock file", Alert.AlertType.WARNING);
        }else{
            dialogUtil.showAlert("Info","Unlock file succesfully",Alert.AlertType.INFORMATION);
        }
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cryptoUtil=new CryptoUtil();
        fileUtil = new FileUtil();
        dialogUtil=new DialogUtil();
    }

    public void setValue(String path,String name){
        this.path=path;
        this.name=name;
    }


}
