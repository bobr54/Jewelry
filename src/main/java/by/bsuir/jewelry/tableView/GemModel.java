package by.bsuir.jewelry.tableView;

import by.bsuir.jewelry.models.Gem;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class GemModel {
    private final LongProperty id;
    private final DoubleProperty price;
    private final DoubleProperty weight;
    private final DoubleProperty clarity;

    public GemModel(Gem gem) {
        this.id = new SimpleLongProperty(gem.getId());
        this.price = new SimpleDoubleProperty(gem.getPrice());
        this.weight = new SimpleDoubleProperty(gem.getWeight());
        this.clarity = new SimpleDoubleProperty(gem.getClarity());
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
}
