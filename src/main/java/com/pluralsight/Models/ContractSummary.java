package com.pluralsight.Models;

import java.time.LocalDate;

public class ContractSummary {
    private String contractType; // "SALE" or "LEASE"
    private LocalDate contractDate;
    private String customerName;
    private String customerEmail;
    private String vin;
    private int year;
    private String make;
    private String model;
    private String color;
    private double totalPrice;
    private double monthlyPayment;

    public ContractSummary(String contractType,
                           LocalDate contractDate,
                           String customerName,
                           String customerEmail,
                           String vin,
                           int year,
                           String make,
                           String model,
                           String color,
                           double totalPrice,
                           double monthlyPayment) {
        this.contractType = contractType;
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public String getContractType() {
        return contractType;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
