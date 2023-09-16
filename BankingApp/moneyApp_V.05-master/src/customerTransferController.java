import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class customerTransferController extends customerController {
  @FXML
  public TextField customerUserNameField;
  @FXML
  public TextField customerAccountNumberField;
  @FXML
  public TextField transferAmountField;
  @FXML
  public Button sendButton;
  @FXML
  public Label balanceCheck;
  public void sendButtonGo() {
    String userName = customerUserNameField.getText();
    String accountNumber = customerAccountNumberField.getText();

    try {
      String inputValue = transferAmountField.getText();
      if (!inputValue.isEmpty()) {
        double transferAmount = Double.parseDouble(transferAmountField.getText());
        if (transferAmount <= loggedInCustomer.getBalance()) {
          double newBalance = loggedInCustomer.getBalance() - transferAmount;
          loggedInCustomer.setBalance(newBalance);
          Session.transferCustomerBalance(userName, accountNumber, transferAmount, loggedInCustomer.getUsername(), loggedInCustomer.getAccountNumber());
          balanceCheck.setText("Successful transfer");
          balanceCheck.setTextFill(Color.MAGENTA);
          balanceCheck.setFont(Font.font("Arial", 16));
        } else {
          balanceCheck.setText("Insufficient Funds");
          balanceCheck.setTextFill(Color.RED);
          balanceCheck.setFont(Font.font("Arial", 16));
        }
      } else {
        showAlert("Input is empty","Please enter a number");
      }
    } catch (NumberFormatException e) {
      showAlert("Incorrect input type", "Please enter a number with number keys");
    }
  }
  private void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }
}

