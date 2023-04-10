import java.util.*;
import java.lang.*;

public class AppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        ArrayList<Product> productArrayList = new ArrayList<Product>();

        System.out.println("Enter First Name, Last Name, Email & Phone Number");
        personArrayList.add(new Person(sc.next(), sc.next(), sc.next(), sc.next()));
       
        while (true) {
            displayMainMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                System.exit(0);
                break;
                case 1:
                displayCustomerMenu();
                    break;
                case 2:
                System.out.println("Enter your first, last name, email, phone number & seller ID");
                Seller sellerObj = new Seller(sc.next(),sc.next(),sc.next(),sc.next(),sc.next());
                   
                   
                  loop2: while(true){
                    displaySellerMenu();
                    int choice2 = sc.nextInt();
                   switch(choice2){
                    case 0:
                    break loop2;
                   case 1: 
                    System.out.println("Enter Product ID, name, description, price, category & quantity");
                    productArrayList.add(new Product(sc.next(),sc.next(),
                    sc.nextLine(),sc.nextDouble(),sc.next(),sc.nextInt(),sellerObj));
                   break;
                   case 2:
                   System.out.println("Enter Product Name");
                   for(int a = 0; a<productArrayList.size();a++)
            
                   break;
                   default:
                   System.out.println("Invalid Choice. Please try again.");
                   }
                   }
                    break;
                  default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }





    public static void displayMainMenu() {
        System.out.println("\nMAIN MENU" +
        "\n 0. Exit" +
        "\n 1. Customer Login" +
        "\n 2. Seller Login" +
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
        "\n 0. Exit" +
        "\n 2. Add Product" +
        "\n 3. Remove Product" +
        "\n 4. View Sales" +
        "\n 5. Logout" + 
        "\nEnter your choice: ");
    }

}    

