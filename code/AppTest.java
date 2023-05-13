import java.util.*;
import java.lang.*;  
public class AppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        ArrayList<Product> productArrayList = new ArrayList<Product>();

int choice1 =  0;

        while(choice1 != 3){
            App.mainMenu();
            choice1 = sc.nextInt();
            switch(choice1){
                case 1:
                    System.out.println("Entering Seller Details...\nEnter first name");
                    String firstName = sc.next();
                    System.out.println("Enter last name");
                    String lastName = sc.next();
                    System.out.println("Enter email");
                    String email = sc.next();
                    System.out.println("Enter phone number");
                    String phoneNumber = sc.next();
                    System.out.println("Enter seller id");
                    String sellerId = sc.next();
                    Seller seller = new Seller(firstName,lastName,email,phoneNumber,sellerId);
                    personArrayList.add(seller);


                    int choice2 = 0;
                   while(choice2 != 3) {
                       App.sellerMenu();
                       choice2 = sc.nextInt();
                       switch (choice2) {
                           case 1:
                               System.out.println("Enter Product ID");
                               String id = sc.next();
                               System.out.println("Enter Product name");
                               String name = sc.next();
                               sc.nextLine();
                               System.out.println("Enter Product Description");
                               String description = sc.nextLine();
                               sc.nextLine();
                               System.out.println("Enter Price");
                               double price = sc.nextDouble();
                               System.out.println("Enter Category");
                               String category = sc.next();
                               System.out.println("Enter Quantity");
                               int quantity = sc.nextInt();
                               Product product = new Product(id, name, description, price,
                                       category, quantity, seller);
                               productArrayList.add(product);
                               break;
                           case 2:
                               for (Product x : productArrayList) {
                                   System.out.println(x);
                               }
                               break;
                           case 3:
                               System.out.println("Exiting Seller Menu...");
                               break;
                           default:
                               System.out.println("Invalid choice, enter valid choice!");
                               break;
                       }
                   }
                    break;
                case 2:
                    //Customer
                    break;
                case 3:
                    System.out.println("Exit Successful!");
                    break;
                default:
                    System.out.println("Choice invalid, enter valid choice!");

            }
        }


    }
}
