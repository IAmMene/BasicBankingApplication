import java.util.UUID;
/*
* This class is to be used to create a new unique Account number when a new customer is registered
 */
public class AccountNumberGenerator {
  public static String generateAccountNumber() {
    return UUID.randomUUID().toString();
  }
}
