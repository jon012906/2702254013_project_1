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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class DeleteMenuPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label kodeLabel;
    private ComboBox<String> kodeComboBox;
    private Button deleteBtn;

    public DeleteMenuPage(Stage stage) {
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

        kodeComboBox = new ComboBox<>();

        deleteBtn = new Button("Delete");

        // Ambil semua kode menu dari MenuController dan isi ComboBox
        List<String> kodeMenu = MenuController.getAllKodeMenu();
        kodeComboBox.setItems(FXCollections.observableArrayList(kodeMenu));
    }

    private void setLayout() {
        formGrid.add(kodeLabel, 0, 0);
        formGrid.add(kodeComboBox, 1, 0);
        formGrid.add(deleteBtn, 1, 1);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        deleteBtn.setOnAction(event -> {
            String kode = kodeComboBox.getValue();

            boolean deleted = MenuController.deleteMenu(kode);

            if (deleted) {
                HomePage homepage = new HomePage(stage);
                Scene homepageScene = homepage.getScene();
                stage.setScene(homepageScene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fail to delete");
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
