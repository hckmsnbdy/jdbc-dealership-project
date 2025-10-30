package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String FILE_NAME = "contracts.txt";

    // Save a contract by appending it to the contracts file
    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            StringBuilder line = new StringBuilder();

            // Check contract type
            if (contract instanceof SalesContract sc) {
                line.append("SALE|")
                        .append(contract.getDateOfContract()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(sc.getVehicle().getVin()).append("|")
                        .append(sc.getVehicle().getYear()).append("|")
                        .append(sc.getVehicle().getMake()).append("|")
                        .append(sc.getVehicle().getModel()).append("|")
                        .append(sc.getVehicle().getVehicleType()).append("|")
                        .append(sc.getVehicle().getColor()).append("|")
                        .append(sc.getVehicle().getOdometer()).append("|")
                        .append(String.format("%.2f", sc.getVehicle().getPrice())).append("|")
                        .append(String.format("%.2f", sc.getSalesTaxAmount())).append("|")
                        .append(String.format("%.2f", sc.getRecordingFee())).append("|")
                        .append(String.format("%.2f", sc.getProcessingFee())).append("|")
                        .append(String.format("%.2f", sc.getTotalPrice())).append("|")
                        .append(sc.isFinance() ? "YES" : "NO").append("|")
                        .append(String.format("%.2f", sc.getMonthlyPayment()));
            }

            else if (contract instanceof LeaseContract lc) {
                line.append("LEASE|")
                        .append(contract.getDateOfContract()).append("|")
                        .append(contract.getCustomerName()).append("|")
                        .append(contract.getCustomerEmail()).append("|")
                        .append(lc.getVehicle().getVin()).append("|")
                        .append(lc.getVehicle().getYear()).append("|")
                        .append(lc.getVehicle().getMake()).append("|")
                        .append(lc.getVehicle().getModel()).append("|")
                        .append(lc.getVehicle().getVehicleType()).append("|")
                        .append(lc.getVehicle().getColor()).append("|")
                        .append(lc.getVehicle().getOdometer()).append("|")
                        .append(String.format("%.2f", lc.getVehicle().getPrice())).append("|")
                        .append(String.format("%.2f", lc.getExpectedEndingValue())).append("|")
                        .append(String.format("%.2f", lc.getLeaseFee())).append("|")
                        .append(String.format("%.2f", lc.getTotalPrice())).append("|")
                        .append(String.format("%.2f", lc.getMonthlyPayment()));
            }

            // Write to file
            writer.write(line.toString());
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }
}
