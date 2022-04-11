module com.zst.autovermietung {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.zst.autovermietung to javafx.fxml;
    exports com.zst.autovermietung;
}