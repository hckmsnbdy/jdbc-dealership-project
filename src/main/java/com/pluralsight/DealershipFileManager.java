package com.pluralsight;

import com.pluralsight.Models.Dealership;
import com.pluralsight.Models.Vehicle;

import java.io.*;
import java.sql.Connection;

public class DealershipFileManager {
    private static final String INVENTORY_FILE = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        final Connection connection;
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader(INVENTORY_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String firstLine = bufferedReader.readLine();

            String[] dealershipData = firstLine.split("\\|");
            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];

            dealership = new Dealership(name, address, phone);

            // Read the remaining lines (vehicles)
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Example format:
                // 10112|1993|Ford|Explorer|SUV|Red|525123|995.00

                // Split line by "|"
                String[] data = line.split("\\|");

                // Extract each field
                String vin = data[0];
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String vehicleType = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                // Create Vehicle object
                Vehicle vehicle = new Vehicle(vin, year, odometer , make, model, color, vehicleType, price);

                // Add it to the dealership inventory
                dealership.addVehicle(vehicle);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dealership;
    }
    public void saveDealership(Dealership dealership) {
        try {
            FileWriter writer = new FileWriter(INVENTORY_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Write dealership info (first line)
            bufferedWriter.write(dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone());
            bufferedWriter.newLine();

            // Write all vehicles
            for (Vehicle v : dealership.getInventory()) {
                bufferedWriter.write(
                        v.getVin() + "|" +
                                v.getYear() + "|" +
                                v.getMake() + "|" +
                                v.getModel() + "|" +
                                v.getVehicleType() + "|" +
                                v.getColor() + "|" +
                                v.getOdometer() + "|" +
                                v.getPrice()
                );
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
