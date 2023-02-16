/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iesalquerias.reports;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author lopas
 */
public class JasperTestLoader {
    private static final String url = "jdbc:mysql://localhost:3306/sakila";
    private static final String user = "root";
    private static final String passwd = "";

    private JasperReport report;
    private JasperPrint jasperPrint;

    public void load(ReportData info) throws ClassNotFoundException, 
            SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(
                                                            url,
                                                            user,
                                                            passwd);

        JasperDesign jasperDesign = JRXmlLoader.load(getClass()
                .getResourceAsStream(info.getRute())); //new File(REPORT_JXML)
        report = JasperCompileManager.compileReport(jasperDesign);

        HashMap params = new HashMap();
        // Cargar logo
        var icon = ClassLoader.getSystemResource(info.getIcon()).getFile();
        System.out.println(icon);
        params.put("icon", icon);
        // llamar reporte
        jasperPrint = JasperFillManager.fillReport(report, params, connection);        
    }

    public JasperReport getReporte() {
        return report;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }
}
