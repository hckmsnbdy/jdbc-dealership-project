package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    public SalesContract(String dateOfContact,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicle,
                         boolean finance) {
        super(dateOfContact, customerName, customerEmail, vehicle);
        this.finance = finance;

        this.salesTaxAmount = vehicle.getPrice() * 0.05;
        this.recordingFee = 100;

        if (vehicle.getPrice() < 10000)
            this.processingFee = 295;
        else
            this.processingFee = 495;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }
    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }
    public double getRecordingFee() {
        return recordingFee;
    }
    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }
    public double getProcessingFee() {
        return processingFee;
    }
    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }
    public boolean isFinance() {
        return finance;
    }
    public void setFinance(boolean finance) {
        this.finance = finance;
    }


    @Override
    public double getTotalPrice() {
        double totalPrice = vehicle.getPrice()
                + salesTaxAmount
                + recordingFee
                + processingFee;
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance)
            return 0;

        double totalPrice = getTotalPrice();
        double annualRate;
        int months;

        if (totalPrice >= 10000) {
            annualRate = 0.0425;
            months = 48;
        } else {
            annualRate = 0.0525;
            months = 24;
        }

        double monthlyRate = annualRate / 12.0;

        double monthlyPayment = (totalPrice * monthlyRate)
                / (1 - Math.pow(1 + monthlyRate, -months));

        return monthlyPayment;
    }
}
