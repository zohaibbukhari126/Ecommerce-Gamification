import java.util.*;
import java.io.*;

public class App {
    public static ArrayList<Person> readPersons(File file) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Person x = (Person) ois.readObject();
                personArrayList.add(x);
            }
        } catch (ClassCastException e) {
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EOFException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return personArrayList;
    }

    public static ArrayList<Product> readProduct(File file) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Product x = (Product) ois.readObject();
                productArrayList.add(x);
            }
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        } catch (EOFException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productArrayList;
    }

    public static void clearFile(File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().truncate(0);
            fos.close();
            System.out.println("File cleared successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writePerson(ArrayList<Person> personArrayList, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Person x : personArrayList) {
                oos.writeObject(x);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void writeProduct(ArrayList<Product> productArrayList, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Product x : productArrayList) {
                oos.writeObject(x);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void searchProduct(ArrayList<Product> productArrayList) {
        System.out.println("Enter Product ID of product you want to remove");
        Scanner sc = new Scanner(System.in);
        String tempId = sc.next();
        for (Product x : productArrayList) {
            if (x.getProductId().equals(tempId)) {
                productArrayList.remove(x);
                System.out.println("Item removed successfully");
                return;
            }
        }
        System.out.println("Product with that id not found, sorry!");
    }

    public static Seller inputSeller() {
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

    public static Product inputProduct(Seller seller) {
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
        Product product = new Product(id, name, description, price, category, quantity, seller);
        return product;
    }

    public static void mainMenu() {
        System.out.println("\n0.Exit");
        System.out.println("1.View as Seller");
        System.out.println("2.View as Customer");
        System.out.println("3.Clear Data");
    }

    public static void clearDataMenu() {
        System.out.println("\n0.Exit");
        System.out.println("1.Clear Seller Data");
        System.out.println("2.Clear Product Data");
        System.out.println();
    }

    public static void sellerMenu() {
        System.out.println("\n0.Exit");
        System.out.println("1.Add Product");
        System.out.println("2.View All Products");
        System.out.println("3.Remove Product");
        System.out.println("4.View Your Products");

    }

    public static void customerMenu() {
        System.out.println("\n0.Exit");
        System.out.println("1.View All Products");
        System.out.println("2.Buy Product");
    }
}

class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Person(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }
}

class Customer extends Person {
    private String customerId;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String customerId) {
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

class Seller extends Person implements Serializable {
    private String sellerId;
    private ArrayList<Product> products;

    @Override
    public String toString() {
        return "Seller{" + "sellerId='" + sellerId + '\'' + '}';
    }

    public Seller(String firstName, String lastName, String email, String phoneNumber, String sellerId) {
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}

class Product implements Serializable {
    private String productId;
    private String name;
    private String description;
    private double price;
    private String category;
    private int quantity;
    private Seller seller;

    public Product(String productId, String name, String description, double price, String category, int quantity,
                   Seller seller) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.seller = seller;
    }

    void DisplayProduct() {
        System.out.println("Product ID : " + getProductId());
        System.out.println("Product Name : " + getName());
        System.out.println("Product Description : " + getDescription());
        System.out.println("Product Price : " + getPrice());
        System.out.println("Product Category : " + getCategory());
        System.out.println("Product Quantity : " + getQuantity());
        System.out.println("Product Seller ID : " + getSeller().getSellerId());
        System.out.println();
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
        return "Product{" + "productId='" + productId +
                '\'' + ", name='" + name +
                '\'' + ", description='" + description +
                '\'' + ", price=" + price +
                ", category='" + category +
                '\'' + ", quantity=" + quantity +
                ", seller=" + seller + '}';
    }
}
