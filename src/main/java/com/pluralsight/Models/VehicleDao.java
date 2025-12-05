
package com.pluralsight.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    public List<Vehicle> getAllVehiclesForDealership(int dealershipId) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
                SELECT v.VIN,
                       v.Make,
                       v.Model,
                       v.VehicleYear,
                       v.Color,
                       v.Price,
                       v.Mileage,
                       v.VehicleType
                FROM vehicles v
                JOIN inventory i ON v.VIN = i.VIN
                WHERE i.dealership_id = ?
                """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("VehicleYear");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String color = rs.getString("Color");
                    int mileage = rs.getInt("Mileage");
                    double price = rs.getDouble("Price");
                    String type = rs.getString("VehicleType");

                    Vehicle vehicle = new Vehicle(
                            vin,
                            year,
                            mileage,
                            make,
                            model,
                            color,
                            type,
                            price
                    );

                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByPriceRange(int dealershipId, double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.Price BETWEEN ? AND ?
            ORDER BY v.Price
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setDouble(2, minPrice);
            statement.setDouble(3, maxPrice);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByMakeModel(int dealershipId, String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.Make  LIKE ?
              AND v.Model LIKE ?
            ORDER BY v.VehicleYear DESC
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setString(2, make);
            statement.setString(3, model);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByYearRange(int dealershipId, int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.VehicleYear BETWEEN ? AND ?
            ORDER BY v.VehicleYear
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setInt(2, minYear);
            statement.setInt(3, maxYear);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByColor(int dealershipId, String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.Color = ?
            ORDER BY v.VehicleYear DESC
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setString(2, color);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByMileageRange(int dealershipId, int minMiles, int maxMiles) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.Mileage BETWEEN ? AND ?
            ORDER BY v.Mileage
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setInt(2, minMiles);
            statement.setInt(3, maxMiles);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public List<Vehicle> getByType(int dealershipId, String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
            SELECT v.VIN,
                   v.Make,
                   v.Model,
                   v.VehicleYear,
                   v.Color,
                   v.Price,
                   v.Mileage,
                   v.VehicleType
            FROM vehicles v
            JOIN inventory i ON v.VIN = i.VIN
            WHERE i.dealership_id = ?
              AND v.VehicleType = ?
            ORDER BY v.Price
            """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setString(2, type);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapRowToVehicle(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    private Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        String vin = rs.getString("VIN");
        int year = rs.getInt("VehicleYear");
        String make = rs.getString("Make");
        String model = rs.getString("Model");
        String color = rs.getString("Color");
        int mileage = rs.getInt("Mileage");
        double price = rs.getDouble("Price");
        String type = rs.getString("VehicleType");

        return new Vehicle(
                vin,
                year,
                mileage,
                make,
                model,
                color,
                type,
                price
        );
    }
    public boolean addVehicle(int dealershipId, Vehicle vehicle) {
        String insertVehicleSql = """
            INSERT INTO vehicles
                (VIN, Make, Model, VehicleYear, Color, Price, Mileage, VehicleType, SOLD)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)
            """;

        String insertInventorySql = """
            INSERT INTO inventory (dealership_id, VIN)
            VALUES (?, ?)
            """;

        try (Connection connection = DataManager.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement vehicleStmt = connection.prepareStatement(insertVehicleSql);
                 PreparedStatement inventoryStmt = connection.prepareStatement(insertInventorySql)) {

                // Insert into vehicles
                vehicleStmt.setString(1, vehicle.getVin());
                vehicleStmt.setString(2, vehicle.getMake());
                vehicleStmt.setString(3, vehicle.getModel());
                vehicleStmt.setInt(4, vehicle.getYear());
                vehicleStmt.setString(5, vehicle.getColor());
                vehicleStmt.setDouble(6, vehicle.getPrice());
                vehicleStmt.setInt(7, vehicle.getOdometer());
                vehicleStmt.setString(8, vehicle.getVehicleType());
                vehicleStmt.executeUpdate();

                // Insert into inventory
                inventoryStmt.setInt(1, dealershipId);
                inventoryStmt.setString(2, vehicle.getVin());
                inventoryStmt.executeUpdate();

                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean removeFromInventory(int dealershipId, String vin) {
        String deleteInventorySql = """
            DELETE FROM inventory
            WHERE dealership_id = ? AND VIN = ?
            """;

        String markSoldSql = """
            UPDATE vehicles
            SET SOLD = 1
            WHERE VIN = ?
            """;

        try (Connection connection = DataManager.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement deleteStmt = connection.prepareStatement(deleteInventorySql);
                 PreparedStatement soldStmt = connection.prepareStatement(markSoldSql)) {

                deleteStmt.setInt(1, dealershipId);
                deleteStmt.setString(2, vin);
                int rows = deleteStmt.executeUpdate();

                soldStmt.setString(1, vin);
                soldStmt.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);

                return rows > 0;

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
