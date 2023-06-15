import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;

public class AppTest extends Application implements EventHandler<ActionEvent> {
    private static Stage stage;
    public static boolean loggedIn = false;
    public static Background bg = new Background(new BackgroundFill(Color.AZURE,null,null));
    private static  ArrayList<Person> personArrayList;
    private static  ArrayList<Product> productArrayList;
    private static PauseTransition delay = new PauseTransition(Duration.seconds(2));
    public static void main(String[] args) {

        personArrayList = App.readObjects(new File("SellerData.txt"));
        productArrayList = App.readObjects(new File("ProductData.txt"));
        Scanner sc = new Scanner(System.in);
        launch(args);


        int choice1 = -1;
        while (choice1 != 0) {
            App.mainMenu();
            choice1 = sc.nextInt();
            switch (choice1) {
                case 0:
                    System.out.println("Exiting main menu...");

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
                                    Product product = App.inputProduct(seller, productArrayList);
                                    if (product != null) {
                                        productArrayList.add(product);
                                        ArrayList<Product> products = seller.getProducts();
                                        products.add(product);
                                        App.saveData(personArrayList, productArrayList);
                                    }
                                } catch (InputMismatchException t) {
                                    System.out.println(t.getMessage());
                                }

                                break;
                            case 2:
                                try {
                                    for (Product x : seller.getProducts()) {
                                        x.DisplayProduct();
                                    }
                                } catch (NullPointerException e) {
                                }
                                break;
                            case 3:
                                App.deleteProduct(productArrayList, seller);
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
                                } catch (NullPointerException e) {
                                }
                                break;
                            case 2: {
                                System.out.println("Enter Product ID or Name you want to buy");
                                String searchQuery = sc.next();
                                System.out.println("Enter Quantity");
                                int quantity = sc.nextInt();
                                boolean productFound = false;

                                for (Product x : productArrayList) {
                                    if (x.getProductId().equalsIgnoreCase(searchQuery) ||
                                            x.getName().equalsIgnoreCase(searchQuery)) {
                                        productFound = true;
                                        x.DisplayProduct();
                                        if (x.getQuantity() <= 0) {
                                            System.out.println("Out of stock");
                                        } else if (x.getQuantity() < quantity) {
                                            System.out.println("Not enough quantity");
                                        } else {
                                            System.out.println(quantity + " quantity reduced");
                                            System.out.println(x.getPrice() * quantity +
                                                    " deducted from your account");
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Image image1 = new Image("image1.png");
        stage.getIcons().add(image1);
        stage.setTitle("Ecommerce Application");
        stage.getIcons().add(new Image("image1.png"));

        Scene mainMenuScene = createMainMenuScene();
        stage.setScene(mainMenuScene);
        stage.show();
    }

    private void openSellerMenuScene(Scene previousScene,Seller seller) {
        Scene sellerMenuScene = createSellerMenuScene(previousScene,seller);
        stage.setScene(sellerMenuScene);
    }

    private void openCustomerMenuScene(Scene previousScene) {
        Scene customerMenuScene = createCustomerMenuScene(previousScene);
        stage.setScene(customerMenuScene);
    }

    private void openClearDataMenuScene(Scene previousScene) {
        Scene clearDataMenuScene = createClearDataMenuScene(previousScene);
        stage.setScene(clearDataMenuScene);
    }
    private void openAddProductMenuScene(Scene previousScene,Seller seller) {
        Scene addProductMenuScene = createAddProductMenuScene(previousScene,seller);
        stage.setScene(addProductMenuScene);
    }

    private void openViewYourProductsMenuScene(Scene previousScene,Seller seller) {
        Scene viewProductsMenuScene = createViewYourProductsMenuScene(previousScene,seller);
        stage.setScene(viewProductsMenuScene);
    }

    private void openRemoveProductMenuScene(Scene previousScene,Seller seller) {
        Scene removeProductMenuScene = createRemoveProductMenuScene(previousScene,seller);
        stage.setScene(removeProductMenuScene);
    }
    private void openViewProductsMenuScene(Scene previousScene) {
        Scene viewProductsMenuScene = createViewProductsMenuScene(previousScene);
        stage.setScene(viewProductsMenuScene);
    }

    private void openBuyProductScene(Scene previousScene) {
        Scene buyProductScene = createBuyProductScene(previousScene);
        stage.setScene(buyProductScene);
    }
    private void openSellerLogin(Scene previousScene) {
        Scene sellerLogin = createSellerLoginScene(previousScene);
        stage.setScene(sellerLogin);
    }


    private Scene createMainMenuScene() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button sellerButton = new Button("View as Seller");
        Button customerButton = new Button("View as Customer");
        Button clearDataButton = new Button("Clear Data");
        Button exitButton = new Button("Exit");

        sellerButton.setOnAction(e -> openSellerLogin(scene));
        customerButton.setOnAction(e -> openCustomerMenuScene(scene));
        clearDataButton.setOnAction(e -> openClearDataMenuScene(scene));
        exitButton.setOnAction(e->{
            App.saveData(personArrayList, productArrayList);
            stage.close();
        });

        root.getChildren().addAll(sellerButton, customerButton, clearDataButton, exitButton);
        root.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));

        return scene;
    }

    private Scene createSellerMenuScene(Scene previousScene,Seller seller) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button viewYourProductButton = new Button("View Your Products");
        Button backButton = new Button("Logout");
        Button addProductButton = new Button("Add Product");

        backButton.setOnAction(e -> stage.setScene(previousScene));
        viewYourProductButton.setOnAction(e -> openViewYourProductsMenuScene(scene,seller));
        addProductButton.setOnAction(e -> openAddProductMenuScene(scene,seller));

        Button removeProductButton = new Button("Remove Product");
        removeProductButton.setOnAction(e -> openRemoveProductMenuScene(scene,seller));

        root.getChildren().addAll(viewYourProductButton, addProductButton, removeProductButton, backButton);

        return scene;
    }
    private Scene createSellerLoginScene(Scene previousScene){
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);

        Button backButton = new Button("Back to main menu");
        backButton.setOnAction(e -> stage.setScene(previousScene));

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        TextField sellerIdTextField = new TextField();
        sellerIdTextField.setPromptText("Seller ID");

        TextField firstNameTextField = new TextField();
        firstNameTextField.setPromptText("First Name");

        TextField lastNameTextField = new TextField();
        lastNameTextField.setPromptText("Last Name");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email");

        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("Phone Number");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String sellerId = sellerIdTextField.getText();
            Seller seller = findSeller(sellerId);

            if (seller != null) {
                messageLabel.setText("Login successful!");
                delay.play();
                delay.setOnFinished(event -> openSellerMenuScene(scene,seller));
            } else {
                messageLabel.setText("Seller ID not found!");
            }
        });
        registerButton.setOnAction(e -> {
            String sellerId = sellerIdTextField.getText();
            Seller existingSeller = findSeller(sellerId);
            if (existingSeller != null) {
                messageLabel.setText("Seller ID already exists!");
            } else {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
                    messageLabel.setText("Please fill in all the required fields.");
                } else {
                    Seller seller = new Seller(firstName, lastName, email, phoneNumber, sellerId);
                    personArrayList.add(seller);
                    App.saveData(personArrayList, productArrayList);
                    messageLabel.setText("Registration successful!");
                    delay.play();
                    delay.setOnFinished(event -> openSellerMenuScene(scene, seller));
                }

            }
        });

        root.getChildren().addAll(sellerIdTextField, firstNameTextField, lastNameTextField, emailTextField, phoneNumberTextField, loginButton, registerButton, messageLabel, backButton);

        return scene;
    }

    private Scene createCustomerMenuScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> stage.setScene(previousScene));
        Button viewProductsButton = new Button("View All Products");
        Button buyProductButton = new Button("Buy Product");

        backButton.setOnAction(event -> stage.setScene(previousScene));

        viewProductsButton.setOnAction(e -> {
            openViewProductsMenuScene(scene);
        });

        buyProductButton.setOnAction(e -> {
            openBuyProductScene(scene);
        });

        root.getChildren().addAll(viewProductsButton, buyProductButton, backButton);
        return scene;
    }

    private Scene createClearDataMenuScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button clearSellerDataButton = new Button("Clear Seller Data");
        Button clearProductDataButton = new Button("Clear Product Data");
        Button backButton = new Button("Back to Main Menu");

        clearSellerDataButton.setOnAction(e -> {

            App.clearFile(new File("SellerData.txt"));
            personArrayList.clear();
            });


        clearProductDataButton.setOnAction(e -> {
            App.clearFile(new File("ProductData.txt"));
            productArrayList.clear();
        });

        backButton.setOnAction(event -> stage.setScene(previousScene));

        root.getChildren().addAll(clearSellerDataButton, clearProductDataButton, backButton);
        root.setBackground(bg);

        return scene;
    }
    private Scene createAddProductMenuScene(Scene previousScene, Seller seller) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Seller Menu");
        Label messageLabel = new Label();
        backButton.setOnAction(event -> stage.setScene(previousScene));

        TextField idTextField = new TextField();
        idTextField.setPromptText("Product ID");
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Product Name");
        TextField descriptionTextField = new TextField();
        descriptionTextField.setPromptText("Product Description");
        TextField priceTextField = new TextField();
        priceTextField.setPromptText("Price");
        TextField categoryTextField = new TextField();
        categoryTextField.setPromptText("Category");
        TextField quantityTextField = new TextField();
        quantityTextField.setPromptText("Quantity");

        Button saveButton = new Button("Save Product");
        saveButton.setOnAction(event -> {
            String id = idTextField.getText();
            String name = nameTextField.getText();
            String description = descriptionTextField.getText();
            String priceStr = priceTextField.getText();
            String category = categoryTextField.getText();
            String quantityStr = quantityTextField.getText();

            if (id.isEmpty() || name.isEmpty() || description.isEmpty() || priceStr.isEmpty()
                    || category.isEmpty() || quantityStr.isEmpty()) {
                messageLabel.setText("Please fill in all the required fields.");
            } else {
                try {
                    double price = Double.parseDouble(priceStr);
                    int quantity = Integer.parseInt(quantityStr);
                    for (Product product : productArrayList) {
                        if (product.getProductId().equalsIgnoreCase(id)) {
                            messageLabel.setText("Product with the same ID already exists!");
                            return;
                        }
                    }

                    Product product = new Product(id, name, description, price, category, quantity, seller);
                    productArrayList.add(product);
                    ArrayList<Product> products = seller.getProducts();
                    products.add(product);
                    App.saveData(personArrayList, productArrayList);

                    messageLabel.setText("Product saved successfully!");
                } catch (NumberFormatException e) {
                    messageLabel.setText("Invalid price or quantity format.");
                }
            }
        });

        root.getChildren().addAll(backButton, idTextField, nameTextField, descriptionTextField,
                priceTextField, categoryTextField, quantityTextField,messageLabel, saveButton);
        root.setBackground(bg);

        return scene;
    }

    private Scene createViewYourProductsMenuScene(Scene previousScene, Seller seller) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        Scene scene = new Scene(scrollPane, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Seller Menu");
        root.getChildren().add(0, backButton);
        backButton.setOnAction(event -> stage.setScene(previousScene));

        for (Product product : seller.getProducts()) {
            Label productIdLabel = new Label("Product ID: " + product.getProductId());
            Label productNameLabel = new Label("Product Name: " + product.getName());
            Label productDescLabel = new Label("Product Description: " + product.getDescription());
            Label productPriceLabel = new Label("Product Price: " + product.getPrice());
            Label productCategoryLabel = new Label("Product Category: " + product.getCategory());
            Label productQuantityLabel = new Label("Product Quantity: " + product.getQuantity());
            Label productSellerIDLabel = new Label("Product Seller ID: " + product.getSeller().getSellerId());
            Label newLineLabel = new Label("\n");
            root.getChildren().addAll(productIdLabel, productNameLabel,
                    productDescLabel, productPriceLabel,
                    productCategoryLabel, productQuantityLabel, productSellerIDLabel, newLineLabel);

        }
        root.setBackground(bg);
        scene.setRoot(scrollPane);

        return scene;
    }

    private Scene createRemoveProductMenuScene(Scene previousScene,Seller seller) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Seller Menu");
        Label messageLabel = new Label();
        TextField idTextField = new TextField();
        idTextField.setPromptText("Product ID");
        Button removeButton = new Button("Remove Product");
        removeButton.setOnAction(event -> {
            String id = idTextField.getText();
            if (id.isEmpty()) {
                messageLabel.setText("Please fill in all the required fields.");
            } else {
                for (Product product : seller.getProducts()) {
                    if (product.getProductId().equalsIgnoreCase(id)) {
                        if (product.getSeller().getSellerId().equalsIgnoreCase(seller.getSellerId())) {
                            productArrayList.remove(product);
                            seller.getProducts().remove(product);
                            App.saveData(personArrayList, productArrayList);
                            messageLabel.setText("Product removed successfully!");
                            return;
                        } else {
                            messageLabel.setText("You are not the seller of this product!");
                            return;
                        }
                    }
                }
                messageLabel.setText("Product with the given ID does not exist!");
            }
        });

        backButton.setOnAction(event -> stage.setScene(previousScene));

        root.getChildren().addAll(idTextField,removeButton, messageLabel,backButton);
        root.setBackground(bg);

        return scene;
    }
    private Scene createBuyProductScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Customer Menu");
        backButton.setOnAction(event -> stage.setScene(previousScene));
        Button buyButton = new Button("Buy Product");

        Label searchQueryLabel = new Label("Enter Product ID or Name you want to buy:");
        TextField searchQueryField = new TextField();
        Label quantityLabel = new Label("Enter Quantity:");
        TextField quantityField = new TextField();
        Label resultLabel = new Label();

        buyButton.setOnAction(e -> {
            String searchQuery = searchQueryField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            boolean productFound = false;

            for (Product product : productArrayList) {
                if (product.getProductId().equalsIgnoreCase(searchQuery) ||
                        product.getName().equalsIgnoreCase(searchQuery)) {
                    productFound = true;
                    if (product.getQuantity() <= 0) {
                        resultLabel.setText("Out of stock");
                    } else if (product.getQuantity() < quantity) {
                        resultLabel.setText("Not enough quantity");
                    } else {
                        product.setQuantity(product.getQuantity() - quantity);
                        resultLabel.setText(quantity + " quantity reduced. " +
                                (product.getPrice() * quantity) + " deducted from your account. " +
                                "Remaining Quantity: " + product.getQuantity());
                    }
                    break;
                }
            }

            if (!productFound) {
                resultLabel.setText("Product not found!");
            }
        });

        root.getChildren().addAll(searchQueryLabel, searchQueryField,
                quantityLabel, quantityField, buyButton, resultLabel, backButton);
        root.setBackground(bg);

        return scene;
    }
    private Scene createViewProductsMenuScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Customer Menu");

        backButton.setOnAction(event -> stage.setScene(previousScene));
        root.getChildren().add(0,backButton);


        for (Product product : productArrayList) {
            Label productIdLabel = new Label("Product ID: " + product.getProductId());
            Label productNameLabel = new Label("Product Name: " + product.getName());
            Label productDescLabel = new Label("Product Description: " + product.getDescription());
            Label productPriceLabel = new Label("Product Price: " + product.getPrice());
            Label productCategoryLabel = new Label("Product Category: " + product.getCategory());
            Label productQuantityLabel = new Label("Product Quantity: " + product.getQuantity());
            Label productSellerIDLabel = new Label("Product Seller ID: " + product.getSeller().getSellerId());
            Label newLineLabel = new Label("\n");
            root.getChildren().addAll(productIdLabel, productNameLabel,
                    productDescLabel,productPriceLabel,
                    productCategoryLabel,productQuantityLabel,productSellerIDLabel,newLineLabel);

        }
        root.setBackground(bg);
        scene.setRoot(scrollPane);
        return scene;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ecommerce Application");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Seller findSeller(String sellerId) {
        for (Person person : personArrayList) {
            if (person instanceof Seller) {
                Seller seller = (Seller) person;
                if (seller.getSellerId().equals(sellerId)) {
                    return seller;
                }
            }
        }
        return null;
    }
}
