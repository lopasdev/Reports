/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package es.iesalquerias.reports;

import java.io.File;
import java.lang.System.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebView;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class PrimaryController implements Initializable{

    @FXML // fx:id="btLoad"
    private Button btLoad; // Value injected by FXMLLoader

    @FXML // fx:id="cbReports"
    private ComboBox<String> cbReports; // Value injected by FXMLLoader

    @FXML // fx:id="pbBar"
    private ProgressBar pbBar; // Value injected by FXMLLoader

    @FXML // fx:id="wvReport"
    private WebView wvReport; // Value injected by FXMLLoader

    
    ObservableList<String> list;

    HashMap<String , ReportData> reportData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btLoad.setOnAction((e) -> loadReport());

        reportData = new HashMap<>();
        reportData.put("Reporte 1", new ReportData("Leaf1_Green.jrxml",
                "es/iesalquerias/reports/maverick.png"));
        reportData.put("Reporte 2", new ReportData("Ejer2.jrxml",
                "es/iesalquerias/reports/interstellar.jpg"));
        list = FXCollections.observableArrayList(reportData.keySet()
        );
        cbReports.setItems(list);
        cbReports.setValue( "Reporte 1");
    }
    
    @FXML
    void change(ActionEvent event) {

    }

    private void loadReport() {
        setLoadingUI();
        startLoadTask();
    }

    private void setLoadingUI() {
        pbBar.setVisible(true);
        btLoad.setDisable(true);
    }

    private void startLoadTask() {
        Task task = createTask();
        new Thread(task).start();
    }

    private Task createTask() {
        return new Task<Void>() {
            File file;

            @Override
            protected Void call() {
                try {
                    file = File.createTempFile("report", ".html");

                    // Crea el fichero HTML
                    JasperTestLoader jtl = new JasperTestLoader();
                    jtl.load(reportData.get(cbReports.valueProperty().get()));

                    JasperPrint jasperPrint = jtl.getJasperPrint();
                    JasperExportManager.exportReportToHtmlFile(jasperPrint,
                            file.getPath());
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(
                            PrimaryController.class.getName()).log(
                            Level.SEVERE, null, ex);
                } finally {
                    return null;
                }
            }

            @Override
            protected void cancelled() {
                System.out.println("cancelled");
            }

            @Override
            protected void succeeded() {
                try {
                    succeededOrFailed();
                    // Muestra el fichero html
                    wvReport.setPrefHeight(5000);
                    wvReport.getEngine().load(file.toURI().toURL()
                            .toExternalForm());
                } catch (MalformedURLException ex) {
                    java.util.logging.Logger.getLogger(PrimaryController
                            .class.getName()).log(Level.SEVERE, null, ex);
                    failed();
                }
            }

            @Override
            protected void failed() {
                succeededOrFailed();
            }

            private void succeededOrFailed() {
                
                pbBar.setVisible(false);
                btLoad.setDisable(false);
            }
        };
    }
}
    
  