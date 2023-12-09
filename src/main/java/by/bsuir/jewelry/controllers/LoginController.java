package by.bsuir.jewelry.controllers;

import by.bsuir.jewelry.auth.AuthResult;
import by.bsuir.jewelry.auth.Authorisation;
import by.bsuir.jewelry.exceptions.NoContentFieldException;
import by.bsuir.jewelry.exceptions.UncorrectDataException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Text dataDangerTxt;

    @FXML
    private Text fieldDangerTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button regBtn;

    @FXML
    void regBtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/signUp-view.fxml"));
        Stage primaryStage = (Stage) regBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(loader.load(), 700, 500));
        primaryStage.show();
    }

    @FXML
    void loginBtn(MouseEvent event) throws IOException {
        try {
            String loginStr = loginField.getText();
            String passwordStr = passwordField.getText();
            boolean isFieldsEmpty = loginStr.isEmpty() || passwordStr.isEmpty();
            if (isFieldsEmpty) {
                throw new NoContentFieldException("Ошибка: Поля пустые " , fieldDangerTxt, dataDangerTxt);
            }
            AuthResult authResult = Authorisation.authenticateUser(loginStr, passwordStr);
            if (!authResult.isAuthenticated()) {
                    throw new UncorrectDataException("Неверный логин или пароль : " + loginStr + "  " + passwordStr);
                }
            try {
                FXMLLoader loader;
                if (authResult.getRole().equals("admin")) {
                    loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/adminPage-view.fxml"));
                    Stage primaryStage = (Stage) regBtn.getScene().getWindow();
                    primaryStage.setScene(new Scene(loader.load(), 700, 500));
                    primaryStage.show();
                } else {
                    loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/userPage-view.fxml"));

                    Stage primaryStage = (Stage) regBtn.getScene().getWindow();
                    UserPageController controller = new UserPageController();
                    controller.setId(authResult.getUserId());

                    loader.setController(controller);

                    Scene scene = new Scene(loader.load(), 700, 500);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        catch (UncorrectDataException e){
            System.out.println("Ошибка: " + e.getMessage());
            dataDangerTxt.setOpacity(1);
            fieldDangerTxt.setOpacity(0);
        }
        catch (NoContentFieldException e){

        }
        loginField.setText("");
        passwordField.setText("");
    }

}
