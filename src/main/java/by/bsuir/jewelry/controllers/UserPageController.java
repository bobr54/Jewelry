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
    private Button createBtn;


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
    void createBtn(MouseEvent event) {
        String weightStr = weightField.getText();
        if(weightStr.isEmpty())
            emptyField.setOpacity(1);
        else{
            emptyField.setOpacity(0);
            Map<Gem, Integer> map = checkGem(Double.parseDouble(weightStr));
            if(map == null){
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
                    Integer quantity = gemQuantities.get(gem);
                    price += gem.getPrice() * quantity;
                    GemUserModel model = new GemUserModel(gem, quantity);
                    gemUserModels.add(model);
                }

                gemData = FXCollections.observableArrayList();
                gemData.addAll(gemUserModels);
                gemTable.setItems(gemData);
                quentityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
                totalCostTxt.setText(Double.toString(price) + "руб");
            }
        }
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
        gemNecklace = new ArrayList<>(gemQuantities.keySet());
        Double price = 0.0;
        for(Gem gem : gemNecklace){
            if(gem.getClarity()>=minFilter && gem.getClarity()<=maxFilter){
                Integer quantity = gemQuantities.get(gem);
                GemUserModel filterGemUserModel = new GemUserModel(gem, quantity);
                price += gem.getPrice() * quantity;
                gemFilterData.add(filterGemUserModel);
            }
        }
        totalCostTxt.setText(Double.toString(price) + "руб");
        gemTable.getItems().clear();
        if(gemFilterData.size()>0)
            gemTable.setItems(gemFilterData);

    }

    @FXML
    void necklaceTable(MouseEvent event) {
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
                        Integer quantity = gemQuantities.get(gem);
                        GemUserModel model = new GemUserModel(gem, quantity);
                        price += gem.getPrice() * quantity;
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
        idNecklaceColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        myJewelryTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                NecklaceModel selectedItem = myJewelryTable.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    deleteBtn.setDisable(false);
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
                                Integer quantity = gemQuantities.get(gem);
                                GemUserModel model = new GemUserModel(gem, quantity);
                                price += gem.getPrice() * quantity;

                                gemUserModels.add(model);
                            }

                            gemData = FXCollections.observableArrayList();
                            gemData.addAll(gemUserModels);
                            gemTable.setItems(gemData);
                            quentityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

                            totalCostTxt.setText(Double.toString(price) + "руб");
                            break;
                        }
                    }
                }
            }
        });
    }

    private Map<Gem, Integer> checkGem( double res) {
        Map<Gem, Integer> result = new HashMap<>();
        if (isSumPossible(res, 0, result)) {
            Iterator<Map.Entry<Gem, Integer>> iterator = result.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Gem, Integer> entry = iterator.next();
                if (entry.getValue() == 0) {
                    iterator.remove();
                }
            }
            return result;
        } else {
            return null;
        }
    }

    private boolean isSumPossible(double target, int index, Map<Gem, Integer> result) {
        if (target == 0) {
            return true;
        }

        if (index >= gems.size()) {
            return false;
        }

        double current = gems.get(index).getWeight();

        for (int count = 0; count * current <= target; count++) {
            double remaining = target - count * current;
            result.put(gems.get(index), count);

            if (isSumPossible(remaining, index + 1, result)) {
                return true;
            }

            result.remove(current);
        }

        return false;
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
                Integer quantity = gemQuantities.get(gem);
                GemUserModel model = new GemUserModel(gem, quantity);
                price += gem.getPrice() * quantity;

                gemUserModels.add(model);
            }
            gemData.addAll(gemUserModels);
            gemTable.setItems(gemData);
            totalCostTxt.setText(Double.toString(price) + "руб");
        }
    }

    public void deleteBtn(MouseEvent mouseEvent) {
        NecklaceModel selectedItem = myJewelryTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            deleteBtn.setDisable(false);
            Long id = selectedItem.getId();
            deleteNecklace(id);
//            necklaceService.deleteNecklace(id.intValue());
            necklaceData.remove(selectedItem);
        }
    }

    private void deleteNecklace(Long id){
        Necklace deleteNecklace = null;
        for (Necklace necklace : necklaceList) {
            if (necklace.getId().equals(id)) {
                deleteNecklace = necklace;
                break;
            }
        }
        necklaceService.deleteNecklace(deleteNecklace);
    }
}
