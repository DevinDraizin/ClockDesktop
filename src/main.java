import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Clock Desktop");
        window.setMaximized(true);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/UI/DriverLayout.fxml")));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();

    }
}
