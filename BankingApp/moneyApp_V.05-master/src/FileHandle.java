import java.io.*;
import java.util.ArrayList;
/*
* Class used to deal with fileIO used throughout the program
 */
public class FileHandle {
  private static final String fileName = "user_data.txt";
  public static String appendUserToFile(Customer customer) {
    ArrayList<Customer> customers = readCustomers();
    for (Customer checkCustomer : customers) {
      if (customer.getUsername().equals(checkCustomer.getUsername())) {
        return "username taken";
      }
    }
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
      // Generating Salt and hashedPassword before new customer is added to user_data.txt
      writer.println(customer.getUsername() + "," + customer.getPassword() + "," + customer.getBalance() + "," + customer.getAccountNumber() + "," + customer.getSalt());
    } catch (Exception e) {
      System.out.println("Try again");
    }
    return null;
  }
  public static ArrayList<Customer> readCustomers() {
    ArrayList<Customer> customers = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if(line.trim().isEmpty()) {
          continue;
        }
        String[] data = line.split(",");
        String username = data[0].trim();
        String password = data[1].trim();
        double balance = Double.parseDouble(data[2]);
        String accountNumber = data[3].trim();
        String salt = data[4].trim();
        customers.add(new Customer(username, password, balance, accountNumber, salt));
      }
    } catch (IOException e) {
      System.out.println("Error with IO");
    }
    return customers;
  }
  public static void writeCustomersToFile(ArrayList<Customer> customers) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
      for (Customer customer : customers) {
        writer.println(customer.getUsername() + "," + customer.getPassword() + "," + customer.getBalance() + "," + customer.getAccountNumber() + "," + customer.getSalt());
      }
    } catch (Exception e) {
      System.out.println("Error writing to file.");
    }
  }
}

