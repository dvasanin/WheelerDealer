package com.WheelerDealer.dao;

import com.WheelerDealer.db.DatabaseConnection;
import com.WheelerDealer.spring.request.contract.SignedContractRequestModel;
import com.WheelerDealer.spring.response.contract.ContractResponseModel;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface ContractDao {
    Connection conn = DatabaseConnection.getConnection();

    List<LocalDate> getCarOccupiedDates(String carId);

    List<ContractResponseModel> getAllContracts();

    List<ContractResponseModel> getAllPendingContracts();

    List<ContractResponseModel> getContractHistory(String userId);

    void deleteContract(String contractId);

    void updateContractApproval(String contractId, Boolean approval);

    boolean userHasPendingContract(String userId);

    void addContractToDB(SignedContractRequestModel contract);
}
