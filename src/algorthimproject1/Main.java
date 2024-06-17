package algorthimproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

    private static Label lbl = new Label();
    private static City[] cities;

    private ComboBox<String> endBox, startBox;

    @Override
    public void start(Stage primaryStage) {
        // Create text fields

        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("File");

        MenuItem openItem = new MenuItem("Open");
        MenuItem DPItem = new MenuItem("DP Table");
        fileMenu.getItems().addAll(openItem, DPItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu);

        startBox = new ComboBox<>();
        startBox.setStyle("-fx-background-color: #e1e1e1; "
                + // Green color
                "-fx-text-fill: white; "
                + // Text color
                "-fx-font-size: 16px; "
                + // Font size
                "-fx-padding: 10px 20px;"); // Padding
        startBox.setPrefWidth(170); // Set preferred width
        startBox.setPrefHeight(50); // Set preferred height

        startBox.setPromptText("Start City");

        endBox = new ComboBox<>();
        endBox.setStyle("-fx-background-color: #e1e1e1; "
                + // Green color
                "-fx-text-fill: white; "
                + // Text color
                "-fx-font-size: 16px; "
                + // Font size
                "-fx-padding: 10px 20px;"); // Padding
        endBox.setPrefWidth(170); // Set preferred width
        endBox.setPrefHeight(50); // Set preferred height

        Label lbl1 = new Label("➔ To ➔");
        lbl1.setStyle(
                "-fx-text-fill: black; "
                + // Text color
                "-fx-font-size: 16px; "
                + // Font size
                "-fx-padding: 10px 20px;"); // Padding

        lbl.setPadding(new Insets(0, 600, 0, 0));
        lbl.setAlignment(Pos.BASELINE_LEFT);
        lbl.setStyle(
                "-fx-font-size: 30px; "); // Padding

        Image image = new Image("file:///C:/Users/zaid7/Downloads/AlgoBG2.png");
        ImageView imageView = new ImageView(image);

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(startBox, lbl1, endBox);
        hbox1.setAlignment(Pos.CENTER);
        //hbox1.setPadding(new Insets(400, 0, 0, 180));

        Button btnFind = new Button("Find Min Cost");
        btnFind.setAlignment(Pos.CENTER);
        btnFind.setStyle("-fx-background-color: #0f7dc9; "
                + // Green color
                "-fx-text-fill: white; "
                + // Text color
                "-fx-font-size: 16px; "
                + // Font size
                "-fx-padding: 10px 20px;"); // Padding

        // Set width and height of the button
        btnFind.setPrefWidth(150); // Set preferred width
        btnFind.setPrefHeight(50); // Set preferred height

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(400, 0, 0, 120));
        vBox.getChildren().addAll(hbox1, btnFind);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox);
        borderPane.setTop(menuBar);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, borderPane, lbl);

        openItem.setOnAction(opne -> {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Data File");

                // Set the initial directory for the file chooser (optional)
                //   fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                // Set filters to only show text files (*.txt)
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                // Show the file chooser dialog and get the selected file
                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                if (selectedFile != null) {
                    // Pass the selected file path to the readDataFromFile method
                    readDataFromFile(selectedFile.getAbsolutePath());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("File Reader");
                    alert.setHeaderText(null);
                    alert.setContentText("File successfully opened: " + selectedFile.getName());
                    alert.showAndWait();
                    startBox.getItems().addAll("Start", "A", "B", "C", "D", "E", "F", "End");
                    for (int i = 0; i < cities.length; i++) {
                        startBox.getItems().addAll(cities[i].name);
                        endBox.getItems().addAll(cities[i].name);
                        endBox.setPromptText("End City");

                    }

                }
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("File Reader");
                alert.setHeaderText(null);
                alert.setContentText("Erorr Opening: ");
                alert.showAndWait();
            }

        });

        btnFind.setOnAction(event -> {
            PathWithCost pathWithCost = MinCostPath.minimumCostTraversal(cities, startBox.getSelectionModel().getSelectedItem(), endBox.getSelectionModel().getSelectedItem());
            if (pathWithCost.cost == MinCostPath.INF) {
                lbl.setText("no Contected path from \n           " + startBox.getSelectionModel().getSelectedItem() + " To " + endBox.getSelectionModel().getSelectedItem());
            } else {
                lbl.setText("Minimum Cost ➔ " + pathWithCost.cost + "\nPath ➔ " + pathWithCost.path);
            }

        });

        DPItem.setOnAction(dpItem -> {
            Stage DPStage = new Stage();
            DPItem.setText("Dynamic Programming Table");

            TextArea txtArea = new TextArea();
            txtArea.appendText(MinCostPath.DPTable.toString());

            Scene scene = new Scene(txtArea, 500, 400);
            DPStage.setScene(scene);
            DPStage.showAndWait();

        });

        Scene scene = new Scene(stackPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX UI");
        primaryStage.show();
    }

    private static void readDataFromFile(String fileName) {
        try ( BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int numCities = Integer.parseInt(br.readLine().trim());
            cities = new City[numCities];
            String[] startEnd = br.readLine().split(", ");
//            System.out.println(startEnd[0]);
//            System.out.println(startEnd[1]);

            cities[0] = new City(startEnd[0]);// Start City
            cities[numCities - 1] = new City(startEnd[1]);// End city

            for (int i = 0; i < numCities - 1; i++) {
                String[] parts = br.readLine().split(", ");
                String cityName = parts[0];
                cities[i] = new City(cityName); // Create the city object

                for (int j = 1; j < parts.length; j++) {
                    String neighborData = parts[j];
                    // Validate the format of neighborData using regular expressions
                    if (neighborData.matches("\\[.*\\]")) {
                        neighborData = neighborData.substring(1, neighborData.length() - 1);
                        String[] neighborParts = neighborData.split(",");
                        if (neighborParts.length >= 2) {
                            String neighborName = neighborParts[0];
                            int petrolCost = Integer.parseInt(neighborParts[1].trim());
                            int hotelCost = Integer.parseInt(neighborParts[2].trim());
                            cities[i].neighbors.add(new Neighbor(neighborName, petrolCost, hotelCost));
                            System.out.println("Added neighbor: " + neighborName + ", Petrol Cost: " + petrolCost + ", Hotel Cost: " + hotelCost);
                        } else {
                            System.out.println("Invalid neighbor data: " + neighborData);
                        }
                    } else {
                        System.out.println("Invalid neighbor data format: " + neighborData);
                    }
                }

            }
            for (int i = 0; i < cities.length; i++) {
                System.out.println(cities[i].name);
                System.out.println(cities[i].neighbors.toString());
                System.out.println("--------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
                // System.out.println(parts[0]);
                //  System.out.println(parts[1]);
                //  System.out.println(parts[2]);
                //   Add neighbors to the city
//                for (int j = 1; j < parts.length; j++) {
//                    String neighborData = parts[j].substring(1, parts[j].length() - 1);
//                  //  System.out.println("Neighbor Data: " + neighborData);
//                    String[] neighborParts = neighborData.split(",");
//                    String neighborName = neighborParts[0];
//                    System.out.println("Name " + neighborName);
//                    cities[i].neighbors.add(new Neighbor(neighborName,10,10));
//                    if (neighborParts.length >= 2) {
//                        int petrolCost = Integer.parseInt(neighborParts[1].trim());
//                        System.out.println("Petrol Cost: " + petrolCost);
//                    } else {
//                        System.out.println("Invalid neighbor data: " + neighborData);
//                    }
//                }
 */
