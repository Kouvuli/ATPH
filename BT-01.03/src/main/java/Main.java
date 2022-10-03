import Constants.Constant;
import Controllers.OTPController;
import Utils.CryptoUtil;
import Utils.EmailUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Properties;
import java.util.Random;

public class Main extends Application {

    private CryptoUtil cryptoUtil=new CryptoUtil();
    @Override
    public void start(Stage stage) throws IOException {

//        final String fromEmail = Constant.MAIL_FROM; //requires valid gmail id
//        final String password = "0918031878Tam100"; // correct password for gmail id
//        final String toEmail = Constant.MAIL_TO; // can be any email id
//
//        System.out.println("TLSEmail Start");
//        Properties props = new Properties();
//        props.put("mail.smtp.host", Constant.SMPT_HOST_SERVER); //SMTP Host
//        props.put("mail.smtp.port", "587"); //TLS Port
//        props.put("mail.smtp.ssl.trust", Constant.SMPT_HOST_SERVER);
//        props.put("mail.smtp.auth", "true"); //enable authentication
//        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        //create Authenticator object to pass in Session.getInstance argument
//        Authenticator auth = new Authenticator() {
//            //override the getPasswordAuthentication method
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        };
//        String token=cryptoUtil.genRandomAlphaNumeric(10);
//        Session session = Session.getInstance(props, auth);
//        EmailUtil.sendEmail(session, toEmail,Constant.MAIL_SUBJECT, Constant.MAIL_BODY+token);

        char b= (char) ('a'+13);

        String k=new StringBuilder("a").append(b).toString();
        String token=cryptoUtil.genRandomAlphaNumeric(10);
        Twilio.init(Constant.ACCOUNT_SID,Constant.AUTH_TOKEN);
        Message.creator(new PhoneNumber(Constant.PHONE_TO),
                new PhoneNumber(Constant.PHONE_FROM),
                "Here is your OTP: "+token).create();
        System.out.println(token);
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/layouts/otp-dialog.fxml"));
        OTPController controller=new OTPController();
        controller.setValue(token);
        loader.setController(controller);

        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene editScene = new Scene(root);
        window.setTitle("OTP");
        window.setScene(editScene);
        window.showAndWait();

    }

    public static void main(String[] args) {
        launch();
    }
}