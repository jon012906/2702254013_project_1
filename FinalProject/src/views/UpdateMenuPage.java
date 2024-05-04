package views;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Menu;

import java.util.List;

public class UpdateMenuPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label kodeLabel, namaLabel, hargaLabel, stokLabel;
    private ComboBox<String> kodeComboBox;
    private TextField namaField, hargaField, stokField;
    private Button updateBtn;

    public UpdateMenuPage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        setEventHandlers();
    }

    private void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 400, 200);
        
        formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20));

        kodeLabel = new Label("Kode Menu:");
        namaLabel = new Label("Nama Menu:");
        hargaLabel = new Label("Harga Menu:");
        stokLabel = new Label("Stok Menu:");

        kodeComboBox = new ComboBox<>();
        namaField = new TextField();
        hargaField = new TextField();
        stokField = new TextField();

        updateBtn = new Button("Update");

  
        List<String> kodeMenu = MenuController.getAllKodeMenu();
        kodeComboBox.setItems(FXCollections.observableArrayList(kodeMenu));
    }

    private void setLayout() {
        formGrid.add(kodeLabel, 0, 0);
        formGrid.add(kodeComboBox, 1, 0);
        formGrid.add(namaLabel, 0, 1);
        formGrid.add(namaField, 1, 1);
        formGrid.add(hargaLabel, 0, 2);
        formGrid.add(hargaField, 1, 2);
        formGrid.add(stokLabel, 0, 3);
        formGrid.add(stokField, 1, 3);
        formGrid.add(updateBtn, 1, 4);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        updateBtn.setOnAction(event -> {
            String kode = kodeComboBox.getValue();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());

            boolean updated = MenuController.updateMenu(kode, nama, harga, stok);

            if (updated) {
                HomePage homepage = new HomePage(stage);
                Scene homepageScene = homepage.getScene();
                stage.setScene(homepageScene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Error");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong happens.");
                alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
