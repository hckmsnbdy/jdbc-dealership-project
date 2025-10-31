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
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("11 - List all contracts");
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
                case "10":
                    sellOrLeaseVehicle();
                    break;
                case "11":
                    listAllContracts();
                    break;
                case "0":
                    // quit the loop
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from the menu.");
                    break;
            }
        }
    }

    // Reads an int from the user, keeps asking until a valid number is entered
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // Reads a double from the user
    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number (like 12999.99).");
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
        int min = readInt("Enter minimum price: ");
        int max = readInt("Enter maximum price: ");

        ArrayList<Vehicle> matches = dealership.getVehiclesByPrice(min, max);
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
        int minYear = readInt("Enter minimum year: ");
        int maxYear = readInt("Enter maximum year: ");

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
        int minMiles = readInt("Enter minimum mileage: ");
        int maxMiles = readInt("Enter maximum mileage: ");

        ArrayList<Vehicle> matches = dealership.getVehiclesByMileage(minMiles, maxMiles);
        displayVehicles(matches);
    }

    private void processGetByType() {
        System.out.print("Enter vehicle type (car / truck / SUV / van): ");
        String type = scanner.nextLine();

        ArrayList<Vehicle> matches = dealership.getVehiclesByType(type);

        displayVehicles(matches);
    }
    private void processAddVehicleRequest() {
        System.out.println("=== ADD NEW VEHICLE ===");

        int vin = readInt("Enter VIN: ");
        int year = readInt("Enter year: ");

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter type (car/truck/SUV/van): ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        int odometer = readInt("Enter odometer reading: ");
        double price = readDouble("Enter price: ");

        Vehicle newVehicle = new Vehicle(vin, year, odometer, make, model, color, type, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager dfm = new DealershipFileManager();
        dfm.saveDealership(dealership);

        System.out.println("Vehicle added and saved.");
    }

    private void processRemoveVehicleRequest() {
        int vin = readInt("Enter VIN of vehicle to remove: ");

        boolean removed = dealership.removeVehicle(vin);

        if (removed) {
            DealershipFileManager dfm = new DealershipFileManager();
            dfm.saveDealership(dealership);
            System.out.println("Vehicle removed and file updated.");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }


    private void sellOrLeaseVehicle() {
        ContractFileManager contractFileManager = new ContractFileManager();

        // 1. Ask for VIN
        int vin = readInt("Enter VIN of the vehicle: ");

        // 2. Find vehicle in current inventory
        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found with VIN: " + vin);
            return;
        }

        // 3. Ask for contract info
        System.out.print("Enter contract date (yyyyMMdd): ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine().trim();

        // 4. Ask for contract type
        System.out.print("Is this a SALE or a LEASE? (S/L): ");
        String type = scanner.nextLine().trim().toUpperCase();

        Contract contract = null;

        if (type.equals("S")) {
            // Ask if finance
            System.out.print("Does the customer want to finance? (Y/N): ");
            String financeInput = scanner.nextLine().trim().toUpperCase();
            boolean finance = financeInput.equals("Y");

            // Create sales contract
            contract = new SalesContract(
                    date,
                    customerName,
                    customerEmail,
                    vehicle,
                    finance
            );

        } else if (type.equals("L")) {
            // Check vehicle age: cannot lease if older than 3 years
            int currentYear = java.time.LocalDate.now().getYear();
            int vehicleAge = currentYear - vehicle.getYear();
            if (vehicleAge > 3) {
                System.out.println("This vehicle is too old to lease (must be 3 years old or newer).");
                return;
            }

            // Create lease contract
            contract = new LeaseContract(
                    date,
                    customerName,
                    customerEmail,
                    vehicle
            );
        } else {
            System.out.println("Invalid choice. Aborting.");
            return;
        }

        // 5. Save contract to file
        contractFileManager.saveContract(contract);

        // 6. Remove vehicle from dealership inventory
        dealership.removeVehicle(vehicle);

        // 7. Also persist updated inventory to inventory.csv
        DealershipFileManager dfm = new DealershipFileManager();
        dfm.saveDealership(dealership);

        // 8. Show summary
        System.out.println("Contract saved.");
        System.out.printf("Vehicle: %d %s %s (%s)%n",
                contract.getVehicle().getYear(),
                contract.getVehicle().getMake(),
                contract.getVehicle().getModel(),
                contract.getVehicle().getColor());
        System.out.printf("Total Price: %.2f%n", contract.getTotalPrice());
        System.out.printf("Monthly Payment: %.2f%n", contract.getMonthlyPayment());
    }
    private void listAllContracts() {
        ContractFileManager cfm = new ContractFileManager();
        var lines = cfm.readAllContracts();

        if (lines == null || lines.isEmpty()) {
            System.out.println("No contracts found.");
            return;
        }

        System.out.println("=== CONTRACTS ===");

        for (String line : lines) {

            String[] parts = line.split("\\|");
            String kind = parts[0];

            if ("SALE".equalsIgnoreCase(kind)) {
                if (parts.length >= 18) {
                    System.out.printf(
                            "[SALE] %s - %s (%s)%n",
                            parts[1], // date
                            parts[2], // customer name
                            parts[3]  // customer email
                    );
                    System.out.printf("  Vehicle: %s %s %s (%s) VIN:%s%n",
                            parts[5],  // year
                            parts[6],  // make
                            parts[7],  // model
                            parts[9],  // color
                            parts[4]   // vin
                    );
                    System.out.printf("  Total: %s  | Monthly: %s  | Finance: %s%n",
                            parts[15], // total price
                            parts[17], // monthly payment
                            parts[16]  // YES/NO
                    );
                } else {
                    System.out.println("  (malformed SALE line) " + line);
                }
            } else if ("LEASE".equalsIgnoreCase(kind)) {
                if (parts.length >= 16) {
                    System.out.printf(
                            "[LEASE] %s - %s (%s)%n",
                            parts[1], // date
                            parts[2], // name
                            parts[3]  // email
                    );
                    System.out.printf("  Vehicle: %s %s %s (%s) VIN:%s%n",
                            parts[5],   // year
                            parts[6],   // make
                            parts[7],   // model
                            parts[9],   // color
                            parts[4]    // vin
                    );
                    System.out.printf("  Total: %s  | Monthly: %s%n",
                            parts[14],  // total
                            parts[15]   // monthly
                    );
                } else {
                    System.out.println("  (malformed LEASE line) " + line);
                }
            } else {
                // unknown line type
                System.out.println("Unknown contract type: " + line);
            }
        }
    }



}
