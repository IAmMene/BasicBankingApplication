import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
/*
* This class handles the authentication of new customers and the customer login
 */
public class Authenticator {
public static boolean isValidUser(String username, String password) {
  if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
    return false;
  }
  Customer customer = getCustomerByUsername(username);
  if (customer != null) {
    String hashedPassword = customer.getPassword();
    String salt = customer.getSalt();
    try {
      String hashedInputPassword = PasswordHashingWithSalt.hashPassword(password, salt);
      return hashedInputPassword.equals(hashedPassword);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }
  return false;
}
  public static Customer getCustomerByUsername(String username) {
    ArrayList<Customer> customers = FileHandle.readCustomers();
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username)){
        return customer;
      }
    }
    return null;
  }

  public static boolean isValidNewUser(String username, String password) {
    return username != null && password != null && !username.isEmpty() && !password.isEmpty();
  }

}