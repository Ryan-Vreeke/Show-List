module com.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires json.simple;
	requires java.desktop;
    opens com.javafx.list to javafx.fxml;
    exports com.javafx.list;
}