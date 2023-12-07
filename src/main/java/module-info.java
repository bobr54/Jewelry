module by.bsuir.jewelry {
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    requires jakarta.persistence;
    requires java.sql;
    requires java.naming;

    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    requires org.controlsfx.controls;
    opens by.bsuir.jewelry to javafx.fxml, lombok, org.hibernate.orm.core;
    opens by.bsuir.jewelry.models to org.hibernate.orm.core;
    exports by.bsuir.jewelry;
    exports by.bsuir.jewelry.controllers;
    opens by.bsuir.jewelry.controllers to javafx.fxml;
}