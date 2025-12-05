package com.pluralsight.Models;

import java.util.ArrayList;

public class Dealership {
    private int dealership_id;
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.dealership_id = dealership_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getDealership_id() {
        return dealership_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }
    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(int min, int max) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            double price = v.getPrice();
            if (price >= min && price <= max) {
                results.add(v);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            boolean matchMake = v.getMake().equalsIgnoreCase(make);
            boolean matchModel = v.getModel().equalsIgnoreCase(model);

            if (matchMake && matchModel) {
                results.add(v);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            int year = v.getYear();
            if (year >= minYear && year <= maxYear) {
                results.add(v);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                results.add(v);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int minMiles, int maxMiles) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            int miles = v.getOdometer();
            if (miles >= minMiles && miles <= maxMiles) {
                results.add(v);
            }
        }

        return results;
    }


    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> results = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                results.add(v);
            }
        }

        return results;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    // Returns the vehicle with the given VIN or null if not found
    public boolean removeVehicle(String vin) {
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle v = inventory.get(i);
            if (v.getVin().equalsIgnoreCase(vin)) {
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    public Vehicle getVehicleByVin(String vin) {
        for (Vehicle v : inventory) {
            if (v.getVin().equalsIgnoreCase(vin)) {
                return v;
            }
        }
        return null;
    }






}
