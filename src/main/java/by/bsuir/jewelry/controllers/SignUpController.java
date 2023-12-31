package by.bsuir.jewelry.controllers;

import by.bsuir.jewelry.auth.Authorisation;
import by.bsuir.jewelry.exceptions.RegisterException;
import by.bsuir.jewelry.services.UserService;
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
import java.sql.SQLException;

public class SignUpController {
    private final UserService userService = new UserService();

    @FXML
    private Text fieldDangerTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private Text loginDangerTxt;

    @FXML
    private TextField loginField;

    @FXML
    private Text passwordDangerTxt;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button regBtn;

    @FXML
    private PasswordField repeatPasswordField;

    public SignUpController() {
    }

    @FXML
    void loginBtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/login-view.fxml"));
        Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(loader.load(), 700, 500));
        primaryStage.show();
    }
    @FXML
    void regBtn(MouseEvent event) {
        String loginStr = loginField.getText();
        String passwordStr = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();

        boolean isUserExc = false;
        boolean isFieldsEmpty = loginStr.isEmpty()||passwordStr.isEmpty()||repeatPassword.isEmpty();
        boolean isPasswordsMatch = passwordStr.equals(repeatPassword);
        try {
            if (isFieldsEmpty) {
                passwordDangerTxt.setOpacity(0);
                fieldDangerTxt.setOpacity(1);
                loginDangerTxt.setOpacity(0);
            } else if (!isPasswordsMatch) {
                throw new RegisterException("Пароли не совпадают");
            } else {
                    String role = userService.getRegistrationRole();
                    isUserExc = Authorisation.registerUser(loginStr, passwordStr, role);
                    Long id = userService.getUserByLogin(loginStr).getId();
                    if (!isUserExc) {
                        passwordDangerTxt.setOpacity(0);
                        fieldDangerTxt.setOpacity(0);
                        loginDangerTxt.setOpacity(1);
                    } else {
                        passwordDangerTxt.setOpacity(0);
                        fieldDangerTxt.setOpacity(0);
                        loginDangerTxt.setOpacity(0);
                        FXMLLoader loader;
                        if (role.equals("user")) {
                            loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/userPage-view.fxml"));
                            Stage primaryStage = (Stage) regBtn.getScene().getWindow();
                            UserPageController controller = new UserPageController();
                            controller.setId(id);

                            loader.setController(controller);

                            Scene scene = new Scene(loader.load(), 700, 500);
                            primaryStage.setScene(scene);
                            primaryStage.show();
                        } else {
                            loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/adminPage-view.fxml"));
                            Stage primaryStage = (Stage) regBtn.getScene().getWindow();
                            primaryStage.setScene(new Scene(loader.load(), 700, 500));
                            primaryStage.show();
                        }


                    }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RegisterException e){
            System.out.println(e.getMessage());
            passwordDangerTxt.setOpacity(0);
            fieldDangerTxt.setOpacity(0);
            loginDangerTxt.setOpacity(1);
        }

        loginField.setText("");
        passwordField.setText("");
        repeatPasswordField.setText("");
    }

}
