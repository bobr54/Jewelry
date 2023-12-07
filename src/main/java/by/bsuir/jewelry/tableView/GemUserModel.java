package by.bsuir.jewelry.tableView;

import by.bsuir.jewelry.models.Gem;
import javafx.beans.property.*;

public class GemUserModel {
    private final LongProperty id;
    private final DoubleProperty price;
    private final DoubleProperty weight;
    private final DoubleProperty clarity;

    private final IntegerProperty quantity;

    public GemUserModel(Gem gem, int quantity) {
        this.id = new SimpleLongProperty(gem.getId());
        this.price = new SimpleDoubleProperty(gem.getPrice());
        this.weight = new SimpleDoubleProperty(gem.getWeight());
        this.clarity = new SimpleDoubleProperty(gem.getClarity());
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public double getClarity() {
        return clarity.get();
    }

    public DoubleProperty clarityProperty() {
        return clarity;
    }

    public void setClarity(double clarity) {
        this.clarity.set(clarity);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public int getQuantity() {
        return quantity.get();
    }

}

