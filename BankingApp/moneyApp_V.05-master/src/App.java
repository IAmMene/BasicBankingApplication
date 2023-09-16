import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;
/*
* Authors: Julian Menegon and Joshua Encinas Valador
* Simple banking app
 */
public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogin.fxml")));
    Scene scene = new Scene(root);

    Image icon = new Image("logo3.png");
    stage.getIcons().add(icon);
    stage.setTitle("Simple Banking with Simple - \"The only kind of banking you'll ever need\"");
    stage.setFullScreen(false);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }
}