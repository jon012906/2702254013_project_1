module FinalProject {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens views to javafx.graphics, javafx.fxml;
	opens controller to javafx.base;
	opens models to javafx.base;
	opens index to javafx.base;

}
