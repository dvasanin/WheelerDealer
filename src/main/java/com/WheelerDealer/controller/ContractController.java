package com.WheelerDealer.controller;

import com.WheelerDealer.dao.*;
import com.WheelerDealer.spring.request.contract.ContractApprovalRequestModel;
import com.WheelerDealer.spring.request.contract.ContractSampleRequestModel;
import com.WheelerDealer.spring.request.contract.SignedContractRequestModel;
import com.WheelerDealer.spring.response.contract.ContractResponseModel;
import com.WheelerDealer.spring.response.contract.ContractSampleResponseModel;
import com.WheelerDealer.spring.response.contract.SignedContractResponseModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@RestController
public class ContractController {
    private static final ContractDao contractDao = new ContractDaoSQL();
    private static final CarDao carDao = new CarDaoSQL();
    private static final UserDao userDao = new UserDaoSQL();

    private static double getContractPrice(LocalDate startDate, LocalDate endDate, String carId) {
        double price = carDao.getPrice(carId);
        int days = (int) DAYS.between(startDate, endDate) + 1;
        return price * days;
    }

    //contracts/sample - POST
    @PostMapping("/contracts/sample")
    public ContractSampleResponseModel getContractSample(@RequestBody ContractSampleRequestModel conSample) {
        double contractPrice = getContractPrice(conSample.getStartDate(), conSample.getEndDate(), conSample.getCarId());

        return new ContractSampleResponseModel(conSample.getUserId(), conSample.getCarId(),
                conSample.getStartDate(), conSample.getEndDate(), contractPrice, false);
    }

    //contracts - POST - sign contract (id)
    @PostMapping("/contracts")
    public SignedContractResponseModel postSingedContract
            (@RequestBody SignedContractRequestModel contract) {
        if (contractDao.userHasPendingContract(contract.getUserId())) {
            return new SignedContractResponseModel(false, "User already has pending contract!!");
        }
        if (!carDao.isCarAvailable(contract.getStartDate(), contract.getEndDate(), contract.getCarId())) {
            return new SignedContractResponseModel(false, "Car is not available for whole duration of the contract!!");
        }
        contractDao.addContractToDB(contract);
        return new SignedContractResponseModel(true, "Contract created, waiting for approval!!");
    }

    //contracts - GET ALL
    @GetMapping("/contracts")
    public List<ContractResponseModel> getAllcontracts(@RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return null;
        }
        return contractDao.getAllContracts();
    }

    //contracts/pending - GET ALL PENDING (@RequestHeader ("authorization"))
    @GetMapping("/contracts/pending")
    public List<ContractResponseModel> getAllPendingContracts
            (@RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return null;
        }
        return contractDao.getAllPendingContracts();
    }


    //contracts/{id}/history - GET (show prev. contracts)
    @GetMapping("/contracts/{userId}/history")
    public List<ContractResponseModel> getContractHistory(@PathVariable("userId") String id) {
        return contractDao.getContractHistory(id);
    }

    //contracts/{id}/approved - POST - show approved contracts
    @PostMapping("/contracts/{contractId}/approval")
    public void approveContract(@RequestHeader("authorization") String adminId,
                                @PathVariable("contractId") String contractId,
                                @RequestBody ContractApprovalRequestModel adminApproval) {

        if (!userDao.isAdmin(adminId)) {
            return;
        }
        if (adminApproval.isApproved()) {
            contractDao.updateContractApproval(contractId, true);
        } else
            contractDao.deleteContract(contractId);
    }
}
