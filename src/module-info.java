module CollectorCardsCentral {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	exports login;
	opens login;
	exports signUp;
	opens signUp;
	exports userDelivery;
	opens userDelivery;
	opens application to javafx.graphics, javafx.fxml;
}