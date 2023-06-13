import java.lang.*;
import java.io.*;
import java.util.*;
public class AppTest {
    public static boolean loggedIn = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Person> personArrayList;
        ArrayList<Product> productArrayList;
        personArrayList = App.readObjects(new File("SellerData.txt"));
        productArrayList = App.readObjects(new File("ProductData.txt"));


        int choice1 = -1;
        while (choice1 != 0) {
            App.mainMenu();
            choice1 = sc.nextInt();
            switch (choice1) {
                case 0:
                    System.out.println("Exiting main menu...");
                    App.saveData(personArrayList, productArrayList);
                    break;
                case 1:
                    Seller seller = App.inputSeller(personArrayList);
                    personArrayList.add(seller);
                    App.saveData(personArrayList, productArrayList);
                    int choice2 = -1;
                    while (choice2 != 0 && loggedIn == true) {
                        App.sellerMenu();
                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 0:
                                System.out.println("Exiting Seller Menu...");
                                AppTest.loggedIn = false;
                                App.saveData(personArrayList, productArrayList);
                                break;
                            case 1:
                                try {
                                    Product product = App.inputProduct(seller,productArrayList);
                                    if (product != null) {
                                        productArrayList.add(product);
                                        ArrayList<Product> products = seller.getProducts();
                                        products.add(product);
                                        App.saveData(personArrayList, productArrayList);
                                    }
                                } catch(InputMismatchException t) {
                                    System.out.println(t.getMessage());
                                }

                                break;
                            case 2:
                                for(Product x: seller.getProducts()){
                                    x.DisplayProduct();
                                }
                                break;
                            case 3:
                                App.deleteProduct(productArrayList,seller);
                                App.saveData(personArrayList, productArrayList);
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
                                try {
                                    for (Product x : productArrayList) {
                                        x.DisplayProduct();
                                    }
                                }
                                catch(NullPointerException e){}
                                break;
                            case 2: {
                                System.out.println("Enter Product ID or Name you want to buy");
                                String searchQuery = sc.next();
                                System.out.println("Enter Quantity");
                                int quantity = sc.nextInt();
                                boolean productFound = false;

                                for (Product x : productArrayList) {
                                    if (x.getProductId().equalsIgnoreCase(searchQuery) || x.getName().equalsIgnoreCase(searchQuery)) {
                                        productFound = true;
                                        x.DisplayProduct();
                                        if (x.getQuantity() <= 0) {
                                            System.out.println("Out of stock");
                                        } else if (x.getQuantity() < quantity) {
                                            System.out.println("Not enough quantity");
                                        } else {
                                            System.out.println(quantity + " quantity reduced");
                                            System.out.println(x.getPrice() * quantity + " deducted from your account");
                                            x.setQuantity(x.getQuantity() - quantity);
                                            System.out.println("Remaining Quantity: " + x.getQuantity());
                                        }
                                        break;
                                    }
                                }

                                if (!productFound) {
                                    System.out.println("Product not found!");
                                }
                                break;
                            }
                            default:
                                System.out.println("Choice invalid, enter valid choice!");
                        }
                    }
                    break;
                case 3:
                    int choice4 = -1;
                    while (choice4 != 0) {
                        App.clearDataMenu();
                        choice4 = sc.nextInt();
                        switch (choice4) {
                            case 0:
                                System.out.println("Exiting back to main menu...");
                                break;
                            case 1:
                                App.clearFile(new File("SellerData.txt"));
                                break;
                            case 2:
                                App.clearFile(new File("ProductData.txt"));
                                break;
                            default:
                                System.out.println("Invalid Choice, enter valid choice!");
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid Input Between (0-3)");

            }
        }
    }
}
