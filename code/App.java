import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class App{ }


class Store {
    private String name;
    private String address;
    private List<Product> products;

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ArrayList<Product> searchProduct(String keyword) {
        ArrayList<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

class Person {
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
    private List<Order> orders;
    private List<Activity> activities;
    private Points points;

    public Customer(String firstName, String lastName,
     String email, String phoneNumber, String customerId) {
        super(firstName, lastName, email, phoneNumber);
        this.customerId = customerId;
        this.orders = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.points = new Points();
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

    public void earnPoints(Activity activity) {
        activities.add(activity);
        points.addPoints(activity.getPointsEarned());
    }

    public void redeemPoints(Reward reward) {
        if (points.getTotalPoints() >= reward.getPointsRequired()) {
            points.subtractPoints(reward.getPointsRequired());
        }
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities= activities;
    }public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}

class Seller extends Person {
    private String sellerId;
    private List<Product> products;
    public Seller(String firstName, String lastName,
     String email, String phoneNumber, String sellerId) {
        super(firstName, lastName, email, phoneNumber);
        this.sellerId = sellerId;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void updateProduct(Product product) {
        for (Product p : products) {
            if (p.getProductId().equals(product.getProductId())) {
                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setCategory(product.getCategory());
                p.setQuantity(product.getQuantity());
            }
        }
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
    private Seller seller;public Product(String productId, String name,
     String description, double price, String category, int quantity, Seller seller) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.seller = seller;
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
}

class Order {
    private String orderId;
    private Customer customer;
    private ArrayList<Product> products;
    private String status;
    private Transaction transaction;

    public Order(String orderId, Customer customer, ArrayList<Product> products, String status, Transaction transaction) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
        this.transaction = transaction;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

class Activity {
    private String activityId;
    private String description;
    private int pointsEarned;
    private Date date;
    public Activity(String activityId, String description, int pointsEarned, Date date) {
        this.activityId = activityId;
        this.description = description;
        this.pointsEarned = pointsEarned;
        this.date = date;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

class Points {
    private int totalPoints;
    private List<Reward> rewards;public Points() {
        this.totalPoints = 0;
        this.rewards = new ArrayList<>();
    }

    public void addPoints(int points) {
        totalPoints += points;
    }

    public void subtractPoints(int points) {
        totalPoints -= points;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}

class Reward {
    private String rewardId;
    private String name;
    private String description;
    private int pointsRequired;
    private Date expiryDate;

    public Reward(String rewardId, String name, String description, int pointsRequired, Date expiryDate) {
        this.rewardId = rewardId;
        this.name = name;
        this.description = description;
        this.pointsRequired = pointsRequired;
        this.expiryDate = expiryDate;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
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

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public Date getExpiryDate() {

        return expiryDate;
    }public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

class Payment {
    private String paymentId;
    private String type;
    private String details;
    public Payment(String paymentId, String type, String details) {
        this.paymentId = paymentId;
        this.type = type;
        this.details = details;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

class Transaction {
    private String transactionId;
    private Order order;
    private Payment payment;
    private Date date;
    private String status;
    public Transaction(String transactionId, Order order, Payment payment, Date date, String status) {
        this.transactionId = transactionId;
        this.order = order;
        this.payment = payment;
        this.date = date;
        this.status = status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class Delivery {
    private String deliveryId;
    private String orderId;
    private Customer customer;
    private String address;
    private String deliveryStatus;
    private Date deliveryDate;
    private String trackingNumber;public Delivery(String deliveryId, String orderId, Customer customer, String address, String deliveryStatus, Date deliveryDate, String trackingNumber) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.customer = customer;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.deliveryDate = deliveryDate;
        this.trackingNumber = trackingNumber;
    }

    public void updateStatus(String status) {
        this.deliveryStatus = status;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getDeliveryDate() {

        return deliveryDate;
    }public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}