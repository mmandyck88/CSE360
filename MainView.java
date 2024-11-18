
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    public MainView(Stage stage) {
        VBox layout = new VBox(20);
        Button button = new Button("Welcome to the Heart Health System");
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 600, 600);
        stage.setScene(scene);
    }
}
