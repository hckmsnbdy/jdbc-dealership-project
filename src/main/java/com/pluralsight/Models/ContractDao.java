package com.pluralsight.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContractDao {

    private static final DateTimeFormatter CONTRACT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    public boolean saveSalesContract(SalesContract contract, int dealershipId) {
        String sql = """
                INSERT INTO sales_contracts
                    (VIN, dealership_id, customer_name, customer_email,
                     contract_date, total_price, monthly_payment, is_financed)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            LocalDate date = LocalDate.parse(
                    contract.getDateOfContract(),
                    CONTRACT_DATE_FORMAT
            );

            statement.setString(1, contract.getVehicle().getVin());
            statement.setInt(2, dealershipId);
            statement.setString(3, contract.getCustomerName());
            statement.setString(4, contract.getCustomerEmail());
            statement.setDate(5, Date.valueOf(date));
            statement.setDouble(6, contract.getTotalPrice());
            statement.setDouble(7, contract.getMonthlyPayment());
            statement.setBoolean(8, contract.isFinance());

            statement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveLeaseContract(LeaseContract contract, int dealershipId) {
        String sql = """
                INSERT INTO lease_contracts
                    (VIN, dealership_id, customer_name, customer_email,
                     contract_date, total_price, monthly_payment)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            LocalDate date = LocalDate.parse(
                    contract.getDateOfContract(),
                    CONTRACT_DATE_FORMAT
            );

            statement.setString(1, contract.getVehicle().getVin());
            statement.setInt(2, dealershipId);
            statement.setString(3, contract.getCustomerName());
            statement.setString(4, contract.getCustomerEmail());
            statement.setDate(5, Date.valueOf(date));
            statement.setDouble(6, contract.getTotalPrice());
            statement.setDouble(7, contract.getMonthlyPayment());

            statement.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ContractSummary> getAllContractsForDealership(int dealershipId) {
        List<ContractSummary> contracts = new ArrayList<>();

        String sql = """
                SELECT
                    'SALE' AS contract_type,
                    sc.contract_date,
                    sc.customer_name,
                    sc.customer_email,
                    v.VIN,
                    v.VehicleYear,
                    v.Make,
                    v.Model,
                    v.Color,
                    sc.total_price,
                    sc.monthly_payment
                FROM sales_contracts sc
                JOIN vehicles v ON sc.VIN = v.VIN
                WHERE sc.dealership_id = ?

                UNION ALL

                SELECT
                    'LEASE' AS contract_type,
                    lc.contract_date,
                    lc.customer_name,
                    lc.customer_email,
                    v.VIN,
                    v.VehicleYear,
                    v.Make,
                    v.Model,
                    v.Color,
                    lc.total_price,
                    lc.monthly_payment
                FROM lease_contracts lc
                JOIN vehicles v ON lc.VIN = v.VIN
                WHERE lc.dealership_id = ?

                ORDER BY contract_date DESC
                """;

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);
            statement.setInt(2, dealershipId);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("contract_type");
                    LocalDate date = rs.getDate("contract_date").toLocalDate();
                    String customerName = rs.getString("customer_name");
                    String customerEmail = rs.getString("customer_email");
                    String vin = rs.getString("VIN");
                    int year = rs.getInt("VehicleYear");
                    String make = rs.getString("Make");
                    String model = rs.getString("Model");
                    String color = rs.getString("Color");
                    double totalPrice = rs.getDouble("total_price");
                    double monthlyPayment = rs.getDouble("monthly_payment");

                    ContractSummary summary = new ContractSummary(
                            type,
                            date,
                            customerName,
                            customerEmail,
                            vin,
                            year,
                            make,
                            model,
                            color,
                            totalPrice,
                            monthlyPayment
                    );

                    contracts.add(summary);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }
}
