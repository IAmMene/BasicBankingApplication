public class Admin extends User {
  public static boolean isAdminUser(String username, String password) {
    String storedUsername = "Admin";
    String storedPassword = "Admin";
    return storedUsername.equals(username) && storedPassword.equals(password);
  }
}
