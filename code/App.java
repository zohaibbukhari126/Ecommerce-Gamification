    import java.util.ArrayList;

    import java.util.List;

        class App{
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


    class Order {
        private String orderId;
        private Customer customer;
        private ArrayList<Product> products;
        private String status;

        public Order(String orderId, Customer customer, ArrayList<Product> products, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.products = products;
            this.status = status;
        }

        public void updateStatus(String status) {
            this.status = status;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(ArrayList<Product> products) {
            this.products = products;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
    class Customer extends Person {
        private String customerId;
        private List<Order> orders;

        public Customer(String firstName, String lastName,
                        String email, String phoneNumber, String customerId) {
            super(firstName, lastName, email, phoneNumber);
            this.customerId = customerId;
            this.orders = new ArrayList<>();
        }

        public void placeOrder(Order order) {
            orders.add(order);
        }

        public Order viewOrder(String orderId) {
            for (Order order : orders) {
                if (order.getOrderId().equals(orderId)) {
                    return order;
                }
            }
            return null;
        }
        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
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
