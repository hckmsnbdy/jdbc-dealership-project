package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
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
                int vin = Integer.parseInt(data[0]);
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
}
