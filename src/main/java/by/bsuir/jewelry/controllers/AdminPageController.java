package by.bsuir.jewelry.controllers;

import by.bsuir.jewelry.models.Gem;
import by.bsuir.jewelry.tableView.GemModel;
import by.bsuir.jewelry.services.GemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPageController {
    private final GemService gemService = new GemService();
    private List<Gem> gems;
    private ObservableList<GemModel> gemData;
    @FXML
    private Button ascendingBtn;

    @FXML
    private TableColumn<Gem, Double> clarityColumn;

    @FXML
    private TextField clarityField;

    @FXML
    private Button decendingBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Text editText;

    @FXML
    private Text dataDangerTxt;

    @FXML
    private Button exitBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button filterBtn;
    @FXML
    private TableView<GemModel> gemTable;

    @FXML
    private TableColumn<Gem, Long> idColumn;

    @FXML
    private TableColumn<Gem, Double> priceColumn;

    @FXML
    private TableColumn<Gem, Double> weightColumn;


    @FXML
    private TextField maxFilterField;

    @FXML
    private TextField minFilterField;

    @FXML
    private TextField priceField;

    @FXML
    private ChoiceBox<?> sortChoice;


    @FXML
    private TextField weightField;

    @FXML
    void ascendingBtn(MouseEvent event) {

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
    void addBtn(MouseEvent event) {

    }

    @FXML
    void saveBtn(MouseEvent event) {
        String weightStr = weightField.getText();
        String priceStr = priceField.getText();
        String clarityStr = clarityField.getText();
        if(weightStr.isEmpty()||priceStr.isEmpty()||clarityStr.isEmpty()){
            dataDangerTxt.setOpacity(1);
        }else{
            Gem gem = new Gem(Double.parseDouble(weightStr), Double.parseDouble(priceStr), Double.parseDouble(clarityStr));
            gemService.addGem(gem);
            gems.add(gem);
            GemModel newGemModel = new GemModel(gem);
            gemData.add(newGemModel);
            gemTable.setItems(gemData);
            dataDangerTxt.setOpacity(0);
        }
    }

    @FXML
    void initialize() {
        gems = gemService.getAllGems();
        List<GemModel> gemModels = gems.stream()
                .map(GemModel::new)
                .collect(Collectors.toList());

        gemData = FXCollections.observableArrayList(gemModels);

        // Установите данные в TableView
        gemTable.setItems(gemData);
//        UnaryOperator<TextFormatter.Change> filterNum = change -> {
//            String newText = change.getControlNewText();
//            if (Pattern.matches("\\d*", newText)) {
//                return change;
//            } else {
//                return null;
//            }
//        };
//
//        UnaryOperator<TextFormatter.Change> filterClarity = change -> {
//            String newText = change.getControlNewText();
//            if (newText.matches("\\d*") && newText.length() <= 3) {
//                int value = newText.isEmpty() ? 0 : Integer.parseInt(newText);
//                if (value >= 0 && value <= 100) {
//                    return change;
//                }
//            }
//            return null;
//        };
//
//        Float defaultValue = 0.0f;
//
//        TextFormatter<Float> textFormatterClarity = new TextFormatter<>(new FloatStringConverter(), defaultValue, filterClarity);
//        TextFormatter<Float> textFormatterNum = new TextFormatter<>(new FloatStringConverter(), defaultValue, filterNum);
//
//        clarityField.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), defaultValue, filterClarity));
//        weightField.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), defaultValue, filterNum));
//        priceField.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), defaultValue, filterNum));
//        minFilterField.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), defaultValue, filterNum));
//        maxFilterField.setTextFormatter(new TextFormatter<>(new FloatStringConverter(), defaultValue, filterNum));

    }

    @FXML
    void editBtn(MouseEvent event) {

    }

    @FXML
    void deleteBtn(MouseEvent event) {

    }
}
