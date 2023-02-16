module es.iesalquerias.reports {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.java;
    requires jasperreports ;
    requires javafx.swing;

    opens es.iesalquerias.reports to javafx.fxml, javafx.web, javafx.swing,
            javafx.controls;
    exports es.iesalquerias.reports;
    
}
