import java.lang.*;
import java.io.*;
import java.util.*;
    public class AppTest {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            ArrayList<Person> personArrayList = new ArrayList<Person>();
            ArrayList<Product> productArrayList = new ArrayList<Product>();

            int choice1 = -1;

            while (choice1 != 0) {
                App.mainMenu();
                choice1 = sc.nextInt();
                switch (choice1) {
                    case 0:
                        System.out.println("Exiting main menu...");
                        break;
                    case 1:
                        Seller seller = App.inputSeller();
                        personArrayList.add(seller);
                        App.saveSeller(seller,"SellerData.txt");

                        int choice2 = -1;
                        while (choice2 != 0) {
                            App.sellerMenu();
                            choice2 = sc.nextInt();
                            switch (choice2) {
                                case 0:
                                    System.out.println("Exiting Seller Menu...");
                                    break;
                                case 1:
                                try{
                                   Product product = App.inputProduct(seller);
                                    productArrayList.add(product);
                                    App.saveProduct(product, "ProductData.txt");
                                }
                                catch(InputMismatchException e){
                                    System.out.println(e.getMessage());
                                }
                                    break;
                                case 2:
                                    for (Product x : productArrayList) {
                                        System.out.println(x);
                                    }
                                    break;
                                case 3:
                                    App.searchProduct(productArrayList);
                                    break;

                                default:
                                    System.out.println("Invalid choice, enter valid choice!");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        int choice3 = -1;
                        while (choice3 != 0) {
                            App.customerMenu();
                            choice3 = sc.nextInt();
                            switch (choice3) {
                                case 0:
                                    System.out.println("Exiting customer menu...");
                                    break;
                                case 1:
                                    for (Product x : productArrayList)
                                        System.out.println(x);
                                    break;
                                case 2: {
                                    System.out.println("Enter Product ID you want to buy");
                                    String productID = sc.next();
                                    for (Product x : productArrayList) {
                                        if (x.getProductId().equals(productID)) {
                                            if (x.getQuantity() == 0) {
                                                System.out.println("out of stock");
                                            } else {
                                                System.out.println(x);
                                                System.out.println(x.getPrice() + " deducted from your account");
                                                x.setQuantity(x.getQuantity() - 1);
                                            }
                                        }
                                    }
                                    break;
                                }
                                default:
                                    System.out.println("Choice invalid, enter valid choice!");
                            }
                         }
                     }
             }
         }
   }
