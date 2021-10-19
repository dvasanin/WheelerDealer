package com.WheelerDealer.dao;

import com.WheelerDealer.spring.request.contract.SignedContractRequestModel;
import com.WheelerDealer.spring.response.contract.ContractResponseModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContractDaoSQL implements ContractDao {
    private static PreparedStatement preparedStatement;
    private static Statement statement;

    @Override
    public List<LocalDate> getCarOccupiedDates(String carId) {
        List<LocalDate> dates = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT start_date, end_date" +
                    " FROM contracts" +
                    " WHERE car_id = '" + carId + "';");
            while (rs.next()) {
                dates.addAll(LocalDate.parse(rs.getString(1))
                        .datesUntil(LocalDate.parse(rs.getString(2))
                                .plusDays(1)).collect(Collectors.toList()));
            }
            return dates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }

    @Override
    public List<ContractResponseModel> getAllContracts() {
        List<ContractResponseModel> contracts = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contracts");
            while (rs.next()) {
                ContractResponseModel contract =
                        new ContractResponseModel(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDate(4).toLocalDate(),
                                rs.getDate(5).toLocalDate(), rs.getDouble(6),
                                rs.getBoolean(7), rs.getBoolean(8));
                contracts.add(contract);
            }
            return contracts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    @Override
    public List<ContractResponseModel> getAllPendingContracts() {
        List<ContractResponseModel> contracts = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contracts" +
                    " WHERE approved = false");
            while (rs.next()) {
                ContractResponseModel contract =
                        new ContractResponseModel(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDate(4).toLocalDate(),
                                rs.getDate(5).toLocalDate(), rs.getDouble(6),
                                rs.getBoolean(7), rs.getBoolean(8));
                contracts.add(contract);
            }
            return contracts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    @Override
    public List<ContractResponseModel> getContractHistory(String userId) {
        List<ContractResponseModel> contracts = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contracts" +
                    " WHERE user_id = '" + userId + "';");
            while (rs.next()) {
                ContractResponseModel contract =
                        new ContractResponseModel(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDate(4).toLocalDate(),
                                rs.getDate(5).toLocalDate(), rs.getDouble(6),
                                rs.getBoolean(7), rs.getBoolean(8));
                contracts.add(contract);
            }
            return contracts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    @Override
    public void deleteContract(String contractId) {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM contracts " +
                    " WHERE contract_id = ?");
            preparedStatement.setString(1, contractId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContractApproval(String contractId, Boolean approval) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE contracts " +
                    " SET approved = ?" +
                    " WHERE contract_id = ?");
            preparedStatement.setBoolean(1, approval);
            preparedStatement.setString(2, contractId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean userHasPendingContract(String userId) {
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM contracts" +
                    " WHERE user_id = '" + userId + "' AND approved = false");
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addContractToDB(SignedContractRequestModel contract) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO contracts " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, false)");
            preparedStatement.setString(1, contract.getContract_id().toString());
            preparedStatement.setString(2, contract.getUserId());
            preparedStatement.setString(3, contract.getCarId());
            preparedStatement.setDate(4, Date.valueOf(contract.getStartDate()));
            preparedStatement.setDate(5, Date.valueOf(contract.getEndDate()));
            preparedStatement.setDouble(6, contract.getTotalPrice());
            preparedStatement.setBoolean(7, contract.isSigned());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
