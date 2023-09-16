import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Customer extends User {
  private double balance;
  private String username;
  private final String password;
  private final String accountNumber;
  private final String salt;
  public Customer(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    this.username = username;
    this.balance = 0.0;
    this.accountNumber = AccountNumberGenerator.generateAccountNumber();
    this.salt = PasswordHashingWithSalt.generateSalt();
    this.password = PasswordHashingWithSalt.hashPassword(password,  salt);
  }
  public Customer(String username, String password, double balance, String accountNumber, String salt) {
    this.username = username;
    this.password = password;
    this.balance = balance;
    this.accountNumber = accountNumber;
    this.salt = salt;
  }
  public String getUsername() {
    return username;
  }
  public String getPassword() {
    return password;
  }
  public double getBalance() {
    return balance;
  }
  public String getAccountNumber() { return accountNumber; }
  public String getSalt() { return salt; }
  public void setBalance(double balance) {
    this.balance = balance;
  }
  public void setUsername(String username) { this.username = username; }
}
