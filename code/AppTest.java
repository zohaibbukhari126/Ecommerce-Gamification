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
public class AppTest extends Application implements EventHandler<ActionEvent> {
    private static Stage stage;
    public static Label label1 = new Label();
    public static boolean loggedIn = false;
    public static Background bg = new Background(new BackgroundFill(Color.AZURE,null,null));
    private static  ArrayList<Person> personArrayList;
    private static  ArrayList<Product> productArrayList;
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

    private void openSellerMenuScene(Scene previousScene) {
        Scene sellerMenuScene = createSellerMenuScene(previousScene);
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


    private Scene createMainMenuScene() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button sellerButton = new Button("View as Seller");
        Button customerButton = new Button("View as Customer");
        Button clearDataButton = new Button("Clear Data");
        Button exitButton = new Button("Exit");

        sellerButton.setOnAction(e -> openSellerMenuScene(scene));
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

    private Scene createSellerMenuScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Main Menu");
        Button addProductButton = new Button("Add Product");
        Button viewProductsButton = new Button("View Your Products");
        Button removeProductButton = new Button("Remove Product");

        backButton.setOnAction(event -> stage.setScene(previousScene));


        addProductButton.setOnAction(e -> {

        });

        viewProductsButton.setOnAction(e -> {

        });

        removeProductButton.setOnAction(e -> {
        });
        root.getChildren().addAll(addProductButton,viewProductsButton,removeProductButton,backButton);
        root.setBackground(bg);

        return scene;
    }

    private Scene createCustomerMenuScene(Scene previousScene) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 960, 600, Color.AZURE);
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> stage.setScene(previousScene));

        root.getChildren().add(backButton);
        root.setBackground(bg);

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
            App.clearFile(new File("SellerData.txt"));});

        clearProductDataButton.setOnAction(e -> {
            App.clearFile(new File("ProductData.txt"));
        });

        backButton.setOnAction(event -> stage.setScene(previousScene));

        root.getChildren().addAll(clearSellerDataButton, clearProductDataButton, backButton);
        root.setBackground(bg);

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
}
