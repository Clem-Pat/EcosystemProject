module com.example.ecosystemproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ecosystemproject to javafx.fxml;
    exports com.example.ecosystemproject;
}