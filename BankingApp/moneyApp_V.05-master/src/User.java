/*
* Superclass for admin and customer users alike
 */
public class User {
  protected String firstname;
  protected String lastname;
  protected String username;
  protected String password;
  public String toString() { return lastname + " " + firstname + " " + username+ " " + password; }
  protected User() {
  }
}
