import java.util.ArrayList;
/*
* This class handles the session logic when a user logs in to keep track of credentials and help update the UI
 */
public class Session {
  private static Session instance;
  private final Customer customer;
  private Session(Customer customer) {
    this.customer = customer;
  }
  // Read and find customer from data and return a customer object that can be passed back to session
  private static Customer customerSession(String username) {
    ArrayList<Customer> customers = FileHandle.readCustomers(); //
    for (Customer value : customers) {
      if (value.getUsername().equals(username)) {
        return value;
      }
    }
    return null;
  }
  // make a session with the customer object
  public static Session getInstance(String username) {
    if (instance == null) {
      instance = new Session(customerSession(username));
    }
    return instance;
  }
  // return the customer object of this session
  public Customer getCustomer() {
    return this.customer;
  }

  // logout button resets instance with clean user session
  public void cleanUserSession() {
    instance = null;
  }

  // return the Session called instance
  public static Session getInstance() {
    return instance;
  }
  public static void updateCustomerBalance(String username, String accountNumber, double newBalance) {
    // Read and write to change balance after using Admin panel to change one single customer balance
    ArrayList<Customer> customers = FileHandle.readCustomers(); // Read list of customers

    for (Customer customer : customers) {
      if (customer.getUsername().equals(username) && customer.getAccountNumber().equals(accountNumber)) {
        customer.setBalance(newBalance);
        break;
        }
      }
    FileHandle.writeCustomersToFile(customers); // Write updated customer list with new customers balance
  }

  // Method for customers to transfer balance
  public static void transferCustomerBalance(String username, String accountNumber, double transferBalance, String user, String userAcc) {
    ArrayList<Customer> customers = FileHandle.readCustomers(); // Read list of customers

    // Find both customers based on user account number and username and change their balances
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username) && customer.getAccountNumber().equals(accountNumber)) {
        double newBalance = customer.getBalance() + transferBalance;
        customer.setBalance(newBalance);
      }
      else if (customer.getUsername().equals(user) && customer.getAccountNumber().equals(userAcc)){
        double bal = customer.getBalance() - transferBalance;
        customer.setBalance(bal);
      }
    }
    FileHandle.writeCustomersToFile(customers); // Write list of customer data to user_data.txt after updated balances
  }
}
