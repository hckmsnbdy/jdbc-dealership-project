package com.pluralsight.Models;

import com.pluralsight.Contract;

public class LeaseContract extends Contract {

        private double expectedEndingValue;
        private double leaseFee;

        public LeaseContract(String dateOfContract,
                             String customerName,
                             String customerEmail,
                             Vehicle vehicle) {
            super(dateOfContract, customerName, customerEmail, vehicle);

            this.expectedEndingValue = vehicle.getPrice() * 0.50;
            this.leaseFee = vehicle.getPrice() * 0.07;
        }

        public void setExpectedEndingValue(double expectedEndingValue) {
            this.expectedEndingValue = expectedEndingValue;
        }
        public void setLeaseFee(double leaseFee) {
            this.leaseFee = leaseFee;
        }
        public double getExpectedEndingValue() {
            return expectedEndingValue;
        }
        public double getLeaseFee() {
            return leaseFee;
        }

        @Override
        public double getTotalPrice() {
            return expectedEndingValue + leaseFee;
        }

        @Override
        public double getMonthlyPayment() {
            double totalPrice = getTotalPrice();
            double annualRate = 0.04;
            int months = 36;

            double monthlyRate = annualRate / 12.0;

            double monthlyPayment = (totalPrice * monthlyRate)
                    / (1 - Math.pow(1 + monthlyRate, -months));

            return monthlyPayment;
        }
    }