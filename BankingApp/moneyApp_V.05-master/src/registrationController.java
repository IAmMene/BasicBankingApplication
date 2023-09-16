import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
/*
* This class handles the registration scene and calls on Authenticator and  FileHandle.java
 */
public class registrationController {
  @FXML
  public TextField firstNameField;
  @FXML
  public TextField lastNameField;
  @FXML
  public Label successLabel;
  @FXML
  private Button logOutButton;
  @FXML
  private Button regButton;
  @FXML
  private PasswordField passWordField;
  @FXML
  private TextField userNameField;
  @FXML
  void logOutButton() throws IOException {
    Parent secondRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogin.fxml")));
    Scene secondScene = new Scene(secondRoot);
    Stage stage = (Stage) logOutButton.getScene().getWindow();
    stage.setScene(secondScene);
    stage.setResizable(false);
    stage.show();
  }
  @FXML
  void setRegButton() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    String username = userNameField.getText();
    String password = passWordField.getText();
    if (Authenticator.isValidNewUser(username, password)) {
      Customer newCustomer = new Customer(username, password);
      String duplicate = FileHandle.appendUserToFile(newCustomer);
      if (duplicate == null) {
        Parent secondRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogin.fxml")));
        Scene secondScene = new Scene(secondRoot);
        Stage stage = (Stage) regButton.getScene().getWindow();
        stage.setScene(secondScene);
        stage.setResizable(false);
        stage.show();
      } else {
        successLabel.setText("Username has been taken");
        successLabel.setTextFill(Color.RED);
        successLabel.setFont(Font.font("Arial", 16));
      }
    } else {
      successLabel.setText("Make sure all fields are filled");
      successLabel.setTextFill(Color.RED);
      successLabel.setFont(Font.font("Arial", 16));
    }
  }
}


