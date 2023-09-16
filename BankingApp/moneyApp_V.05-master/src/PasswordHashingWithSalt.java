import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;
/*
* This class is used to a. generate salt when a new customer registers and b. to hash the salt to a password to be stored
 */
public class PasswordHashingWithSalt {
  public static String generateSalt() throws NoSuchAlgorithmException {
    SecureRandom random = SecureRandom.getInstanceStrong();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return Base64.getEncoder().encodeToString(salt);
  }
  public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
    int iterations = 10000;
    char[] chars = password.toCharArray();
    byte[] saltBytes = Base64.getDecoder().decode(salt);

    PBEKeySpec spec = new PBEKeySpec(chars, saltBytes, iterations, 64 * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = skf.generateSecret(spec).getEncoded();

    return Base64.getEncoder().encodeToString(hash);
  }
}
