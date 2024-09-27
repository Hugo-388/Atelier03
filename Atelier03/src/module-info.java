module Atelier03 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	
	opens controller to javafx.graphics, javafx.fxml;
}
