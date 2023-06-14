import java.util.*;
import java.io.*;

public class App {
    public static void saveData(ArrayList<Person> personArrayList, ArrayList<Product> productArrayList) {
        writeObjects(personArrayList, new File("SellerData.txt"));
        writeObjects(productArrayList, new File("ProductData.txt"));
    }

    public static void clearFile(File file) {
        try {
            ArrayList<Object> emptyList = new ArrayList<>();
            writeObjects(emptyList, file);

            System.out.println("File cleared successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static <T> ArrayList<T> readObjects(File file) {
        ArrayList<T> objectArrayList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                T obj = (T) ois.readObject();
                objectArrayList.add(obj);
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return objectArrayList;
    }

    public static <T> void writeObjects(ArrayList<T> objectArrayList, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (T obj : objectArrayList) {
                oos.writeObject(obj);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteProduct(ArrayList<Product> productArrayList,Seller seller) {
        System.out.println("Enter Product ID of product you want to remove");
        Scanner sc = new Scanner(System.in);
        String tempId = sc.next();
        for (Product x : productArrayList) {
            if (x.getSeller() == seller && x.getProductId().equals(tempId)) {
                productArrayList.remove(x);
                System.out.println("Item removed successfully");
                return;
            }
        }
        System.out.println("None of your products match that id, sorry!");
    }

    public static Seller inputSeller(ArrayList<Person> personArrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a registered seller? (1: Login / 2: Register)");
        int choice = sc.nextInt();
        do {
            if(choice!=1 && choice!=2){
                System.out.println("Invalid entry, Enter 1 to login, 2 to Register!");
                choice = sc.nextInt();}

            if (choice == 1) {
                System.out.println("Enter your Seller ID:");
                String sellerId = sc.next();
                for (Person person : personArrayList) {
                    if (person instanceof Seller) {
                        Seller seller = (Seller) person;
                        if (seller.getSellerId().equals(sellerId)) {
                            System.out.println("Login successful!");
                            AppTest.loggedIn = true;
                            System.out.println(seller);
                            return seller;
                        }
                    }
                }
                System.out.println("Seller ID not found!");
            } else if (choice == 2) {
                System.out.println("Enter your Seller ID:");
                String sellerId = sc.next();
                for (Person person : personArrayList) {
                    if (person instanceof Seller) {
                        Seller seller = (Seller) person;
                        if (seller.getSellerId().equals(sellerId)) {
                            System.out.println("Seller ID already exists!");
                            return null;
                        }
                    }
                }
                System.out.println("Enter first name");
                String firstName = sc.next();
                System.out.println("Enter last name");
                String lastName = sc.next();
                System.out.println("Enter email");
                String email = sc.next();
                System.out.println("Enter phone number");
                String phoneNumber = sc.next();
                Seller seller = new Seller(firstName, lastName, email, phoneNumber, sellerId);
                personArrayList.add(seller);
                System.out.println("Registration successful!");
                return seller;
            }
        }
        while(choice!=1 && choice !=2);

        return null;
    }

    public static Product inputProduct(Seller seller,ArrayList<Product> productArrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product ID");
        String id = sc.next();
        for (Product x : productArrayList) {
            if (x.getProductId().equalsIgnoreCase(id)) {
                System.out.println("Product with the same ID already exists!");
                return null;
            }
        }
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
        System.out.println("2.View Your Products");
        System.out.println("3.Remove Product");
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

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName +
                "\n" + "Email: " + getEmail() + "\n" +
                "Phone Number: " + getPhoneNumber();
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
        return super.toString()+"\nSeller ID: "+sellerId;
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
    private String productId = "Null";
    private String name = "null";
    private String description ="n/a";
    private double price = 3;
    private String category = "124";
    private int quantity = 0;
    private Seller seller = new Seller("0","0","0","0","0");

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
