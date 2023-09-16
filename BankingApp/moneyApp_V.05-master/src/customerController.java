import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
public class customerController {
  public Label customerLabel;
  public Customer loggedInCustomer;
  @FXML
  public TextField balance;
  @FXML
  public TextField username;
  @FXML
  public Button logOutButton;
  @FXML
  public Button transferButton;
  @FXML
  void logOutButton() throws IOException {
    Session.getInstance().cleanUserSession();

    Parent secondRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogin.fxml")));
    Scene secondScene = new Scene(secondRoot);
    Stage stage = (Stage) logOutButton.getScene().getWindow();
    stage.setScene(secondScene);
    stage.setResizable(false);
    stage.show();
  }
  /*
  * Used a modular window so customer doesn't lose site of customer panel
   */
  public void transferButtonGo() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("customerTransferPane.fxml"));
    Parent transferRoot = loader.load();

    customerTransferController transferController = loader.getController();
    transferController.setCustomer(loggedInCustomer);

    Scene transferScene = new Scene(transferRoot);
    Stage transferStage = new Stage();
    transferStage.setTitle("Make a transfer to a friend");
    transferStage.setScene(transferScene);
    transferStage.initModality(Modality.APPLICATION_MODAL);
    transferStage.setResizable(false);
    transferStage.showAndWait();
    updateUI();
  }
  public void setLoggedInCustomer(Customer customer) {
    loggedInCustomer = customer;
    updateUI();
  }
  public void setCustomer(Customer customer) {
    loggedInCustomer = customer;
  }
  protected void updateUI() {
    balance.setText(String.valueOf(loggedInCustomer.getBalance()));
    username.setText(loggedInCustomer.getAccountNumber());
    customerLabel.setText("Welcome!, " + loggedInCustomer.getUsername());
  }
}