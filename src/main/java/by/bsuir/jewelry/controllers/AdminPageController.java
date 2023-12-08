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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminPageController {
    private final GemService gemService = new GemService();

    private GemModel editGem;
    private List<Gem> gems;

    private ObservableList<GemModel> gemData;

    @FXML
    private TextField clarityField;


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
    private Button refreshBtn;

    @FXML
    private Button filterBtn;
    @FXML
    private TableView<GemModel> gemTable;

    @FXML
    private TableColumn<GemModel, Long> idColumn;

    @FXML
    private TableColumn<GemModel, Double> priceColumn;

    @FXML
    private TableColumn<GemModel, Double> weightColumn;

    @FXML
    private TableColumn<GemModel, Double> clarityColumn;


    @FXML
    private TextField maxFilterField;

    @FXML
    private TextField minFilterField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField weightField;

    @FXML
    void exitBtn(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/jewelry/views/login-view.fxml"));
        Stage primaryStage = (Stage) exitBtn.getScene().getWindow();
        primaryStage.setScene(new Scene(loader.load(), 700, 500));
        primaryStage.show();
    }

    @FXML
    void refreshBtn(MouseEvent event) throws IOException {
        gemTable.getItems().clear();
        List<GemModel> gemModels = gems.stream()
                .map(GemModel::new)
                .collect(Collectors.toList());
        gemData.addAll(gemModels);
        gemTable.setItems(gemData);
    }

    @FXML
    void filterBtn(MouseEvent event) {
        String minFilterStr = minFilterField.getText();
        String maxFilterStr = maxFilterField.getText();
        Double minFilter, maxFilter;
        if(minFilterStr.isEmpty()){
            minFilter = 0.0;
            minFilterField.setText("0.0");
        }else minFilter = Double.parseDouble(minFilterStr);
        if(maxFilterStr.isEmpty()){
            maxFilter = 100.0;
            maxFilterField.setText("100.0");
        }else maxFilter = Double.parseDouble(maxFilterStr);
        ObservableList<GemModel> gemFilterData = FXCollections.observableArrayList();
        for(Gem gem : gems){
            boolean re = gem.getClarity()<=maxFilter;
            boolean r = gem.getClarity()>=minFilter;
            boolean res = gem.getClarity()>=minFilter&&gem.getClarity()<=maxFilter;
            if(gem.getClarity()>=minFilter && gem.getClarity()<=maxFilter){
                GemModel filterGemModel = new GemModel(gem);
                gemFilterData.add(filterGemModel);
            }
        }
        gemTable.getItems().clear();
        gemTable.setItems(gemFilterData);

    }

    @FXML
    void addBtn(MouseEvent event) {
        editText.setText("Добавление");
        weightField.setText("");
        priceField.setText("");
        clarityField.setText("");
        addBtn.setDisable(true);
        editGem = null;
    }

    @FXML
    void saveBtn(MouseEvent event) {
        String weightStr = weightField.getText();
        String priceStr = priceField.getText();
        String clarityStr = clarityField.getText();
        if(weightStr.isEmpty()||priceStr.isEmpty()||clarityStr.isEmpty()){
            dataDangerTxt.setOpacity(1);
        }else{
            if (editText.getText().equals("Добавление")){
                Gem gem = new Gem(Double.parseDouble(weightStr), Double.parseDouble(priceStr), Double.parseDouble(clarityStr));
                gemService.addGem(gem);
                gems.add(gem);
                GemModel newGemModel = new GemModel(gem);
                gemData.add(newGemModel);
                gemTable.setItems(gemData);
                dataDangerTxt.setOpacity(0);
                weightField.setText("");
                priceField.setText("");
                clarityField.setText("");
            }
            else {
                    Long id = editGem.getId();
                    Gem gem = new Gem(id, Double.parseDouble(weightStr), Double.parseDouble(priceStr), Double.parseDouble(clarityStr));
                    gemService.updateGem(gem);
                    GemModel updateGemModel = new GemModel(gem);
                    int indexToUpdate = gemData.indexOf(editGem);
                    if (indexToUpdate != -1) {
                        gemData.set(indexToUpdate, updateGemModel);
                        gems.set(indexToUpdate,gem);
                    }
                addBtn.setDisable(true);
                editText.setText("Добавление");
                editGem = null;

            }
        }
    }

    @FXML
    void initialize() {
        gems = gemService.getAllGems();
        List<GemModel> gemModels = gems.stream()
                .map(GemModel::new)
                .collect(Collectors.toList());

        gemData = FXCollections.observableArrayList();
        gemData.addAll(gemModels);

        // Установите данные в TableView
        gemTable.setItems(gemData);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        clarityColumn.setCellValueFactory(cellData -> cellData.getValue().clarityProperty().asObject());

        gemTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Получаем выбранную строку таблицы
                GemModel selectedItem = gemTable.getSelectionModel().getSelectedItem();

                // Проверяем, является ли выбранная строка непустой
                if (selectedItem != null) {
                    editBtn.setDisable(false);
                    deleteBtn.setDisable(false);
                }
            }
        });
    }

    @FXML
    void editBtn(MouseEvent event) {

        editGem = gemTable.getSelectionModel().getSelectedItem();
        if (editGem != null) {
            // Используйте данные из selectedGem
            double weight = editGem.getWeight();
            double price = editGem.getPrice();
            double clarity = editGem.getClarity();
            weightField.setText(Double.toString(weight));
            priceField.setText(Double.toString(price));
            clarityField.setText(Double.toString(clarity));
            editText.setText("Редактирование");
            addBtn.setDisable(false);
            editBtn.setDisable(true);
            dataDangerTxt.setOpacity(0);
        }
    }

    @FXML
    void deleteBtn(MouseEvent event) {
        GemModel selectedGemModel = gemTable.getSelectionModel().getSelectedItem();
        if (selectedGemModel != null) {
            // Используйте данные из selectedGem
            Long id = selectedGemModel.getId();
            double weight = selectedGemModel.getWeight();
            double price = selectedGemModel.getPrice();
            double clarity = selectedGemModel.getClarity();
            gemService.deleteGem(id.intValue());
            gemData.remove(selectedGemModel);
            Gem selectedGem = new Gem(id, weight, price, clarity);
            gems.remove(selectedGem);

        }
    }

}
