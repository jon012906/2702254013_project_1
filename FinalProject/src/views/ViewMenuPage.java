package views;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Menu;

import java.util.List;

public class ViewMenuPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private VBox contentBox;
    private TableView<Menu> tableView;
    private Button backButton;

    public ViewMenuPage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        populateTable();
        setEventHandlers();
    }

    private void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);

        contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20));

        Label titleLabel = new Label("View Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        backButton = new Button("Back");


        TableColumn<Menu, String> kodeColumn = new TableColumn<>("Kode Menu");
        kodeColumn.setCellValueFactory(cellData -> cellData.getValue().kodeProperty());

        TableColumn<Menu, String> namaColumn = new TableColumn<>("Nama Menu");
        namaColumn.setCellValueFactory(cellData -> cellData.getValue().namaProperty());

        TableColumn<Menu, Double> hargaColumn = new TableColumn<>("Harga Menu");
        hargaColumn.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());

        TableColumn<Menu, Integer> stokColumn = new TableColumn<>("Stok Menu");
        stokColumn.setCellValueFactory(cellData -> cellData.getValue().stokProperty().asObject());

        tableView.getColumns().addAll(kodeColumn, namaColumn, hargaColumn, stokColumn);
    }

    private void setLayout() {
        contentBox.getChildren().addAll(tableView, backButton);

        rootNode.setTop(new Label("PT Pudding - View Menu"));
        BorderPane.setAlignment(rootNode.getTop(), Pos.CENTER);
        rootNode.setCenter(contentBox);
    }

    private void populateTable() {
        List<Menu> menuList = MenuController.getAllMenus();
        tableView.setItems(FXCollections.observableArrayList(menuList));
    }

    private void setEventHandlers() {
        backButton.setOnAction(event -> {
            HomePage homePage = new HomePage(stage);
            Scene homePageScene = homePage.getScene();
            stage.setScene(homePageScene);
            stage.show();
        });
    }

    public Scene getScene() {
        return scene;
    }
}
