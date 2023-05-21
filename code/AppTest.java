import java.util.*;
import java.lang.*;
public class AppTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList = new ArrayList<Person>();
        ArrayList<Product> productArrayList = new ArrayList<Product>();

int choice1 = -1;

        while(choice1 != 0){
            App.mainMenu();
            choice1 = sc.nextInt();
            switch(choice1){
                case 0:
                    System.out.println("Exiting main menu...");
                    break;
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


                    int choice2 = -1;
                   while(choice2 != 0) {
                       App.sellerMenu();
                       choice2 = sc.nextInt();
                       switch (choice2) {
                           case 0:
                               System.out.println("Exiting Seller Menu...");
                               break;
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
                               System.out.println("Enter Product ID of product you want to remove");
                               String tempId = sc.next();
                               boolean found1 = false;
                               for(Product x: productArrayList){
                                   if(x.getProductId().equals(tempId)) {
                                       productArrayList.remove(x);
                                       System.out.println("Item removed successfully");
                                       found1 = true;
                                       break;
                                   }
                               }
                            if(found1 == false)
                                System.out.println("Product with that id not found, sorry!");
                               break;

                           default:
                               System.out.println("Invalid choice, enter valid choice!");
                               break;
                       }
                   }
                    break;
                case 2:
                   int choice3 = -1;
                   while (choice3 != 0){
                       App.customerMenu();
                       choice3 = sc.nextInt();
                       switch(choice3){
                           case 0:
                               System.out.println("Exiting customer menu...");
                               break;
                           case 1:
                               for(Product x: productArrayList)
                                   System.out.println(x);
                               break;
                           case 2:
                               System.out.println("Enter Product ID you want to buy");
                               String productID = sc.next();
                               for(Product x: productArrayList){
                                   if(x.getProductId().equals(productID)){
                                       System.out.println(x);
                                       System.out.println(x.getPrice()+" deducted from your account");
                                       x.setQuantity(x.getQuantity()-1);
                                       if(x.getQuantity()==0)
                                           productArrayList.remove(x);
                                   }
                               }

                       }
                }

                default:
                    System.out.println("Choice invalid, enter valid choice!");

            }
        }


    }
}
