/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iesalquerias.reports;

/**
 *
 * @author lopas
 */
public class ReportData {
    
    private String rute;
    private String icon;

    //Constructor
    public ReportData(String rute, String icon) {
        this.rute = rute;
        this.icon = icon;
    }

    //Getters y Setters
    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
    
}
