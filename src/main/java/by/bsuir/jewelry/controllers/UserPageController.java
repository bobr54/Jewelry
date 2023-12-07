package by.bsuir.jewelry.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import by.bsuir.jewelry.models.Gem;
import by.bsuir.jewelry.models.Necklace;
import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.services.GemService;
import by.bsuir.jewelry.services.NecklaceService;
import by.bsuir.jewelry.services.UserService;
import by.bsuir.jewelry.tableView.GemModel;
import by.bsuir.jewelry.tableView.GemUserModel;
import by.bsuir.jewelry.tableView.NecklaceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private final UserService userService = new UserService();
    private GemUserModel editGem;
    private final GemService gemService = new GemService();
    private final NecklaceService necklaceService = new NecklaceService();
    private List<Gem> gems;
    private ObservableList<GemUserModel> gemData;
    private ObservableList<NecklaceModel> necklaceData;

    private Necklace currNecklace;
    private List<Necklace> necklaceList;
    private Long userId;


    private List<Gem> gemNecklace;
    @FXML
    private ResourceBundle resources;

    @FXML
    private Text emptyField;

    @FXML
    private URL location;

    @FXML
    private Button ascendingBtn;

    @FXML
    private Button createBtn;

    @FXML
    private Button decendingBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button filterBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<GemUserModel> gemTable;


    @FXML
    private TextField maxFilterField;

    @FXML
    private TextField minFilterField;

    @FXML
    private TableView<NecklaceModel> myJewelryTable;

    @FXML
    private Text noJewelryText;


    @FXML
    private ChoiceBox<?> sortChoice;

    @FXML
    private Text totalCostTxt;

    @FXML
    private TextField weightField;
    @FXML
    private TableColumn<GemUserModel, Long> idColumn;

    @FXML
    private TableColumn<GemUserModel, Double> priceColumn;

    @FXML
    private TableColumn<GemUserModel, Double> weightColumn;

    @FXML
    private TableColumn<GemUserModel, Double> clarityColumn;

    @FXML
    private TableColumn<GemUserModel, Integer> quentityColumn;

    @FXML
    private TableColumn<NecklaceModel, Long> idNecklaceColumn;

    public void setId(Long id) {
        this.userId = id;
    }
    @FXML
    void ascendingBtn(MouseEvent event) {

    }

    @FXML
    void createBtn(MouseEvent event) {
        String weightStr = weightField.getText();
        if(weightStr.isEmpty())
            emptyField.setOpacity(1);
        else{
            emptyField.setOpacity(0);
            Map<Gem, Integer> map = getSubsetSum(Double.parseDouble(weightStr));
            if(map.isEmpty()){
                noJewelryText.setOpacity(1);
            }
            else {
                noJewelryText.setOpacity(0);
                User user = userService.getUserById(userId);
                currNecklace = new Necklace(user, map);
                necklaceService.addNecklace(currNecklace);
                necklaceList.add(currNecklace);
                NecklaceModel newNecklaceModel = new NecklaceModel(currNecklace);
                necklaceData.add(newNecklaceModel);
                myJewelryTable.setItems(necklaceData);
                Map<Gem, Integer> gemQuantities = currNecklace.getGemQuantities();
                gemNecklace = new ArrayList<>(gemQuantities.keySet());
                Double price = 0.0;
                List<GemUserModel> gemUserModels = new ArrayList<>();
                for(Gem gem : gemNecklace){
                    price += gem.getPrice();
                    Integer quantity = gemQuantities.get(gem);
                    GemUserModel model = new GemUserModel(gem, quantity);
                    gemUserModels.add(model);
                }

                gemData = FXCollections.observableArrayList();
                gemData.addAll(gemUserModels);
                gemTable.setItems(gemData);
                totalCostTxt.setText(Double.toString(price) + "руб");
            }
        }
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
        ObservableList<GemUserModel> gemFilterData = FXCollections.observableArrayList();
        Map<Gem, Integer> gemQuantities = currNecklace.getGemQuantities();
        for(Gem gem : gems){
            if(gem.getClarity()>=minFilter && gem.getClarity()<=maxFilter){
                Integer quantity = gemQuantities.get(gem);
                GemUserModel filterGemUserModel = new GemUserModel(gem, quantity);
                gemFilterData.add(filterGemUserModel);
            }
        }
        gemTable.getItems().clear();
        if(gemFilterData.size()>0)
            gemTable.setItems(gemFilterData);

    }
    @FXML
    void initialize() {
        gems = gemService.getAllGems();

        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        clarityColumn.setCellValueFactory(cellData -> cellData.getValue().clarityProperty().asObject());
        necklaceList = necklaceService.getNecklaceByUser(userId);
        List<NecklaceModel> necklaceModels = necklaceList.stream()
                .map(NecklaceModel::new)
                .collect(Collectors.toList());

        necklaceData = FXCollections.observableArrayList();
        necklaceData.addAll(necklaceModels);
        myJewelryTable.setItems(necklaceData);

        myJewelryTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                NecklaceModel selectedItem = myJewelryTable.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {

                    Long id = selectedItem.getId();
                    for (Necklace necklace : necklaceList) {
                        deleteBtn.setDisable(false);
                        if (necklace.getId().equals(id)) {
                            currNecklace = necklace;
                            Map<Gem, Integer> gemQuantities = currNecklace.getGemQuantities();
                            gemNecklace = new ArrayList<>(gemQuantities.keySet());
                            Double price = 0.0;
                            List<GemUserModel> gemUserModels = new ArrayList<>();
                            for(Gem gem : gemNecklace){
                                price += gem.getPrice();
                                Integer quantity = gemQuantities.get(gem);
                                GemUserModel model = new GemUserModel(gem, quantity);
                                gemUserModels.add(model);
                            }

                            gemData = FXCollections.observableArrayList();
                            gemData.addAll(gemUserModels);
                            gemTable.setItems(gemData);
                            totalCostTxt.setText(Double.toString(price) + "руб");
                            break;
                        }
                    }
                }
            }
        });
        myJewelryTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Получаем выбранную строку таблицы
                NecklaceModel selectedItem = myJewelryTable.getSelectionModel().getSelectedItem();

                // Проверяем, является ли выбранная строка непустой
                if (selectedItem != null) {
                    decendingBtn.setDisable(false);
                }
            }
        });
    }

    public Map<Gem, Integer> getSubsetSum(double sum) {
        int n = gems.size();
        boolean[][] dp = new boolean[n + 1][(int) (sum + 1)];

        // Инициализация первого столбца
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Заполнение массива dp
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (gems.get(i - 1).getWeight() <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][(int) (j - gems.get(i - 1).getWeight())];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Построение Map с числами и их количеством
        Map<Gem, Integer> subsetMap = new HashMap<>();
        int i = n;
        double j = sum;
        while (i > 0 && j > 0) {
            if (dp[i][(int) j] && !dp[i - 1][(int) j]) {
                Gem gem = gems.get(i - 1);
                subsetMap.put(gem, subsetMap.getOrDefault(gem, 0) + 1);
                j -= gems.get(i - 1).getWeight();
            }
            i--;
        }

        return subsetMap;
    }

    @FXML
    public void refreshBtn(MouseEvent mouseEvent) {
        if(currNecklace != null) {
            gemTable.getItems().clear();
            Map<Gem, Integer> gemQuantities = currNecklace.getGemQuantities();
            gemNecklace = new ArrayList<>(gemQuantities.keySet());
            Double price = 0.0;
            List<GemUserModel> gemUserModels = new ArrayList<>();
            for (Gem gem : gemNecklace) {
                price += gem.getPrice();
                Integer quantity = gemQuantities.get(gem);
                GemUserModel model = new GemUserModel(gem, quantity);
                gemUserModels.add(model);
            }
            gemData.addAll(gemUserModels);
            gemTable.setItems(gemData);
        }
    }

    public void deleteBtn(MouseEvent mouseEvent) {
        NecklaceModel selectedItem = myJewelryTable.getSelectionModel().getSelectedItem();

//        if (selectedItem != null) {
//            decendingBtn.setDisable(false);
//            Long id = selectedItem.getId();
//            necklaceService.deleteNecklace(id.intValue());
//            necklaceData.remove(selectedItem);
//
//            Gem selectedGem = new Gem(id, weight, price, clarity);
//            gems.remove(selectedGem);
//        }
    }
}
