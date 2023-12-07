package by.bsuir.jewelry.tableView;

import by.bsuir.jewelry.models.Necklace;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;

public class NecklaceModel {
    private final LongProperty id;

    public NecklaceModel(Necklace necklace) {
        this.id = new SimpleLongProperty(necklace.getId());
    }


    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

}
