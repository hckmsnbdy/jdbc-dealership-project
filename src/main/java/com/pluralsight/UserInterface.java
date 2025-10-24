package com.pluralsight;

import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    // This holds the dealership we are working with
    private Dealership dealership;

    // Scanner to read user input from console
    private Scanner scanner = new Scanner(System.in);

    private void init() {
        // Create the file manager that knows how to read inventory.csv
        DealershipFileManager fileManager = new DealershipFileManager();

        // Load the dealership (name, address, phone, vehicles list)
        this.dealership = fileManager.getDealership();
    }

    public void display() {
        // Load dealership data before starting the menu
        init();

        boolean running = true;

        while (running) {
            // Show menu options
            System.out.println("===== DEALERSHIP MENU =====");
            System.out.println("1 - Find vehicles by price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("0 - Quit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine(); // read user input as text

            switch (choice) {
                case "1":
                    processGetByPriceRange();
                    break;
                case "2":
                    processGetByMakeModel();
                    break;
                case "3":
                    processGetByYearRange();
                    break;
                case "4":
                    processGetByColor();
                    break;
                case "5":
                    processGetByMileageRange();
                    break;
                case "6":
                    processGetByType();
                    break;
                case "7":
                    // list all vehicles
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "0":
                    // quit the loop
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    // not implemented yet
                    System.out.println("Option not implemented yet.");
                    break;
            }
        }
    }
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        // If the list is empty or null, tell the user
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        // Loop through each vehicle and print its details
        for (Vehicle v : vehicles) {
            // This assumes your Vehicle class has getters like getVin(), getYear(), etc.
            System.out.println(
                    "VIN: " + v.getVin() +
                            " | Year: " + v.getYear() +
                            " | Make: " + v.getMake() +
                            " | Model: " + v.getModel() +
                            " | Type: " + v.getVehicleType() +
                            " | Color: " + v.getColor() +
                            " | Miles: " + v.getOdometer() +
                            " | Price: $" + v.getPrice()
            );
        }
    }
    private void processAllVehiclesRequest() {
        // Get the full inventory list from the dealership
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();

        // Display them to the screen
        displayVehicles(allVehicles);
    }
    private void processGetByPriceRange() {
        // Ask user for min and max price
        System.out.print("Enter minimum price: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter maximum price: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        // Call dealership to get matching vehicles
        ArrayList<Vehicle> matches = dealership.getVehiclesByPrice((int)min, (int)max);

        // Display results
        displayVehicles(matches);
    }
    private void processGetByMakeModel() {
        // Ask user for make (brand) and model
        System.out.print("Enter make (brand): ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        // Ask dealership for matching vehicles
        ArrayList<Vehicle> matches = dealership.getVehiclesByMakeModel(make, model);

        // Display them
        displayVehicles(matches);
    }
    private void processGetByYearRange() {
        System.out.print("Enter minimum year: ");
        int minYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());

        ArrayList<Vehicle> matches = dealership.getVehiclesByYear(minYear, maxYear);

        displayVehicles(matches);
    }
    private void processGetByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> matches = dealership.getVehiclesByColor(color);

        displayVehicles(matches);
    }
    private void processGetByMileageRange() {
        System.out.print("Enter minimum mileage: ");
        int minMiles = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum mileage: ");
        int maxMiles = Integer.parseInt(scanner.nextLine());

        ArrayList<Vehicle> matches = dealership.getVehiclesByMilage(minMiles, maxMiles);

        displayVehicles(matches);
    }
    private void processGetByType() {
        System.out.print("Enter vehicle type (car / truck / SUV / van): ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> matches = dealership.getVehiclesByType(type);

        displayVehicles(matches);
    }



}
