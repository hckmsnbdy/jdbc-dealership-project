package com.pluralsight;

public abstract class Contract {
    String dateOfContact;
    String customerName;
    String customerEmail;
    Vehicle vehicle;

    public Contract(String dateOfContact,
                    String customerName,
                    String customerEmail,
                    Vehicle vehicle) {
        this.dateOfContact = dateOfContact;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    public String getDateOfContact() {
        return dateOfContact;
    }
    public void setDateOfContact(String dateOfContact) {
        this.dateOfContact = dateOfContact;
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
