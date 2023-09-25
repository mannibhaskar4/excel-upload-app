package com.example.exceluploadapp.service;

public class ExcelSummary {
    private double totalHours;
    private double totalTotal;

    public ExcelSummary(double totalHours, double totalTotal) {
        this.totalHours = totalHours;
        this.totalTotal = totalTotal;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public double getTotalTotal() {
        return totalTotal;
    }
}
