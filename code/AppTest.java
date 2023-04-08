import java.util.*;
import java.lang.*;

public class AppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        
        System.out.println("Enter First Name, Last Name, Email & Phone Number");
        personArrayList.add(new Person(sc.next(), sc.next(), sc.next(), sc.next()));
       
        while (true) {
            displayMainMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                   displayCustomerMenu();
                   
                    break;
                case 2:
                   displaySellerMenu();
                    break;
                case 3:
                System.exit(0);
                    break;
                  default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }








    public static void displayMainMenu() {
        System.out.println("\nMAIN MENU" +
        "\n 1. Customer Login" +
        "\n 2. Seller Login" +
        "\n 3. Exit" +
        "\nEnter your choice: ");
    }

     public static void displayCustomerMenu() {
       System.out.println("\nCUSTOMER MENU" +
        "\n 1. View Products" + 
        "\n 2. View Transactions" +
        "\n 3. Buy Product" +
        "\n 4. View Balance" +
        "\n 5. Logout" +
        "\nEnter your choice: ");
    }

    public static void displaySellerMenu() {
     System.out.println("\nSELLER MENU" +
        "\n 1. View Products" +
        "\n 2. Add Product" +
        "\n 3. Remove Product" +
        "\n 4. View Sales" +
        "\n 5. Logout" + 
        "\nEnter your choice: ");
    }
}


