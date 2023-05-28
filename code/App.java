import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.Scanner;


class App{
        public static void searchProduct(List <Product> productArrayList){
            System.out.println("Enter Product ID of product you want to remove");
            Scanner sc = new Scanner(System.in);
            String tempId = sc.next();
            boolean found1 = false;
            for (Product x : productArrayList) {
                if (x.getProductId().equals(tempId)) {
                    productArrayList.remove(x);
                    System.out.println("Item removed successfully");
                    found1 = true;
                    return;
                }
            }
            if (found1 == false)
                System.out.println("Product with that id not found, sorry!");
                return;
          }
        public static Seller inputSeller(){
            Scanner sc = new Scanner(System.in);
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
            Seller seller = new Seller(firstName, lastName, email, phoneNumber, sellerId);

            return seller;
        }

        public static Product inputProduct(Seller seller){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Product ID");
            String id = sc.next();
            System.out.println("Enter Product name");
            String name = sc.next();
            sc.nextLine();
            System.out.println("Enter Product Description");
            String description = sc.nextLine();
            System.out.println("Enter Price");
            double price = sc.nextDouble();
            System.out.println("Enter Category");
            String category = sc.next();
            System.out.println("Enter Quantity");
            int quantity = sc.nextInt();
            Product product = new Product(id, name, description, price,
                    category, quantity, seller);
            return product;
        }
        public static void mainMenu(){
            System.out.println("\n0.Exit");
            System.out.println("1.Login as Seller");
            System.out.println("2.Login as Customer");

        }
        public static void sellerMenu() {
            System.out.println("\n0.Exit");
            System.out.println("1.Add Product");
            System.out.println("2.View All Products");
            System.out.println("3.Remove Product");

        }
        public static void customerMenu(){  
            System.out.println("\n0.Exit");
            System.out.println("1.View All Products");
            System.out.println("2.Buy Product");
        }
         public static void saveSeller(Seller seller, String fileName) {
                try (FileWriter writer = new FileWriter(fileName,true)) {
                    writer.write(seller.getFirstName() + "\n");
                writer.write(seller.getLastName() + "\n");
                writer.write(seller.getEmail() + "\n");
                writer.write(seller.getPhoneNumber() + "\n");
                writer.write(seller.getSellerId() + "\n");
    
                System.out.println("Seller details saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving seller details: " + e.getMessage());
            }
        }
        public static void saveProduct(Product product, String fileName) {
            try (FileWriter writer = new FileWriter(fileName,true)) {
               writer.write(product.getProductId()+ "\n");
               writer.write(product.getName()+ "\n");
               writer.write(product.getDescription()+ "\n");
               writer.write(String.valueOf(product.getPrice())+ "\n");
               writer.write(product.getCategory()+ "\n");
               writer.write(product.getQuantity()+ "\n");
               writer.write(product.getSeller().getSellerId()+ "\n");
                System.out.println("Product saved successfully.");
                writer.close();
            } catch (IOException e) {
                System.out.println("Error saving product details: " + e.getMessage());
            }
        }
        }
class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    public static int totalPerson = -1;
    public Person(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        totalPerson++;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
class Customer extends Person {
    private String customerId;

    public Customer(String firstName, String lastName,
                    String email, String phoneNumber, String customerId) {
        super(firstName, lastName, email, phoneNumber);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }





}

class Seller extends Person {
    private String sellerId;
    private List<Product> products;

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId='" + sellerId + '\'' +
                '}';
    }

    public Seller(String firstName, String lastName,
                  String email, String phoneNumber, String sellerId) {
        super(firstName, lastName, email, phoneNumber);
        this.sellerId = sellerId;
        this.products = new ArrayList<>();


    }


    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

class Product {
    private String productId;
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;
    private Seller seller;

    public Product(String productId, String name,
                   String description, double price, String category, int quantity, Seller seller) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.seller = seller;
    }

    void DisplayProduct() {
        System.out.println("Product ID :" + " " + getProductId());
        System.out.println("Product Name :" + " " + getName());
        System.out.println("Product Description :" + " " + getDescription());
        System.out.println("Product Price :" + " " + getPrice());
        System.out.println("Product Category :" + " " + getCategory());
        System.out.println("Product Quantity :" + " " + getQuantity());
        System.out.println();
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", seller=" + seller +
                '}';
    }
}
