package views;

import controller.MenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Menu;

public class InsertMenuPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label kodeLabel, namaLabel, hargaLabel, stokLabel;
    private TextField kodeField, namaField, hargaField, stokField;
    private Button insertBtn;

    public InsertMenuPage(Stage stage) {
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

        kodeField = new TextField();
        namaField = new TextField();
        hargaField = new TextField();
        stokField = new TextField();

        insertBtn = new Button("Insert");
    }

    private void setLayout() {
        formGrid.add(kodeLabel, 0, 0);
        formGrid.add(kodeField, 1, 0);
        formGrid.add(namaLabel, 0, 1);
        formGrid.add(namaField, 1, 1);
        formGrid.add(hargaLabel, 0, 2);
        formGrid.add(hargaField, 1, 2);
        formGrid.add(stokLabel, 0, 3);
        formGrid.add(stokField, 1, 3);
        formGrid.add(insertBtn, 1, 4);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        insertBtn.setOnAction(event -> {
            String kode = kodeField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());

            if (kode.isEmpty() || nama.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }

            Menu newMenu = new Menu(kode, nama, harga, stok);
            boolean inserted = MenuController.insertMenu(newMenu);

            if (inserted) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
                System.out.println("Failed to insert menu.");
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
