package com.pluralsight;

import com.pluralsight.Models.Vehicle;

public abstract class Contract {
    private String dateOfContract;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    public Contract(String dateOfContact,
                    String customerName,
                    String customerEmail,
                    Vehicle vehicle) {
        this.dateOfContract = dateOfContact;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }
    public void setDateOfContract(String dateOfContact) {
        this.dateOfContract = dateOfContact;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

}
