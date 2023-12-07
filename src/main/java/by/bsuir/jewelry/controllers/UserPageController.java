package by.bsuir.jewelry.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ascendingBtn;

    @FXML
    private TableColumn<?, ?> clarityColumn;

    @FXML
    private Button createBtn;

    @FXML
    private Button decendingBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button filterBtn;

    @FXML
    private TableView<?> gemTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TextField maxFilterField;

    @FXML
    private TextField minFilterField;

    @FXML
    private TableView<?> myJewelryTable;

    @FXML
    private Text noJewelryText;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private ChoiceBox<?> sortChoice;

    @FXML
    private Text totalCostTxt;

    @FXML
    private TableColumn<?, ?> weightColumn;

    @FXML
    private TextField weightField;

    @FXML
    void ascendingBtn(MouseEvent event) {

    }

    @FXML
    void createBtn(MouseEvent event) {

    }

    @FXML
    void decendingBtn(MouseEvent event) {

    }

    @FXML
    void exitBtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/login-view.fxml"));
        Stage primaryStage = (Stage) exitBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(loader.load(), 700, 500));
        primaryStage.show();
    }

    @FXML
    void filterBtn(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert ascendingBtn != null : "fx:id=\"ascendingBtn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert clarityColumn != null : "fx:id=\"clarityColumn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert createBtn != null : "fx:id=\"createBtn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert decendingBtn != null : "fx:id=\"decendingBtn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert exitBtn != null : "fx:id=\"exitBtn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert filterBtn != null : "fx:id=\"filterBtn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert gemTable != null : "fx:id=\"gemTable\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert maxFilterField != null : "fx:id=\"maxFilterField\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert minFilterField != null : "fx:id=\"minFilterField\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert myJewelryTable != null : "fx:id=\"myJewelryTable\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert noJewelryText != null : "fx:id=\"noJewelryText\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert sortChoice != null : "fx:id=\"sortChoice\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert totalCostTxt != null : "fx:id=\"totalCostTxt\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert weightColumn != null : "fx:id=\"weightColumn\" was not injected: check your FXML file 'userPage-view.fxml'.";
        assert weightField != null : "fx:id=\"weightField\" was not injected: check your FXML file 'userPage-view.fxml'.";

    }

}
