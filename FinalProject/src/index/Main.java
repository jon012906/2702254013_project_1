package index;

import javafx.application.Application;
import javafx.stage.Stage;
import views.HomePage;
import views.LoginPage;

public class Main extends Application {
    protected DatabaseConnection dbConnection = new DatabaseConnection();

    public Main() {
        dbConnection.migrateTables();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        LoginPage loginPage = new LoginPage(stage);
        stage.setScene(loginPage.getScene());
        stage.setTitle("PT Pudding - Login");
        stage.show();
    }
}
