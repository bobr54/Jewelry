package by.bsuir.jewelry;

import by.bsuir.jewelry.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class JewelryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.close();
        FXMLLoader fxmlLoader = new FXMLLoader(JewelryApplication.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Jewelry App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}