package views;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Menu;

public class HomePage {

    protected Stage stage;
    protected Scene scene;

    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox buttonHBox;
    protected Button editBtn, deleteBtn, insertBtn;
    protected TableView<Menu> tableView;
    protected ObservableList<Menu> menuList;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);
       
        List<Menu> allMenus = MenuController.getAllMenus();
        menuList = FXCollections.observableArrayList(allMenus);
        
        
        tableView = new TableView<>();
        
  
        TableColumn<Menu, String> kodeColumn = new TableColumn<>("Kode Menu");
        kodeColumn.setCellValueFactory(cellData -> cellData.getValue().kodeProperty());
        
        TableColumn<Menu, String> namaColumn = new TableColumn<>("Nama Menu");
        namaColumn.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        
        TableColumn<Menu, Double> hargaColumn = new TableColumn<>("Harga Menu");
        hargaColumn.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());
        
        TableColumn<Menu, Integer> stokColumn = new TableColumn<>("Stok Menu");
        stokColumn.setCellValueFactory(cellData -> cellData.getValue().stokProperty().asObject());
        
        tableView.getColumns().addAll(kodeColumn, namaColumn, hargaColumn, stokColumn);
        tableView.setItems(menuList);
        
        insertBtn = new Button("Insert");
      
        insertBtn.setOnAction(event -> {
            InsertMenuPage newPage = new InsertMenuPage(stage); 
            Scene newPageScene = newPage.getScene(); 
            stage.setScene(newPageScene);
            stage.show();
        });
        
        editBtn = new Button("Edit");

        editBtn.setOnAction(event -> {

            Menu selectedMenu = tableView.getSelectionModel().getSelectedItem();
            if (selectedMenu != null) {
                UpdateMenuPage newPage = new UpdateMenuPage(stage, selectedMenu); 
                Scene newPageScene = newPage.getScene(); 
                stage.setScene(newPageScene);
                stage.show();
            }
        });
        
        deleteBtn = new Button("Delete");

        deleteBtn.setOnAction(event -> {

            Menu selectedMenu = tableView.getSelectionModel().getSelectedItem();
            if (selectedMenu != null) {

                MenuController.deleteMenu(selectedMenu.getKode());

                menuList.remove(selectedMenu);
            }
        });
    }
    
    

    protected void setLayout() {
        vbox = new VBox(8);
        vbox.getChildren().addAll(tableView);
        
        buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(insertBtn, editBtn, deleteBtn);

        rootNode.setCenter(vbox);
        rootNode.setBottom(buttonHBox);
    }


    protected Scene getScene() {
        return this.scene;
    }

    public HomePage(Stage stage) {
        init();
        setLayout();
        this.stage = stage;
        this.stage.setTitle("Home Page");
    }
}
