package com.WheelerDealer.dao;

import com.WheelerDealer.db.DatabaseConnection;
import com.WheelerDealer.spring.misc.SearchCarModel;
import com.WheelerDealer.spring.request.car.AddCarRequestModel;
import com.WheelerDealer.spring.request.car.ChangeCarInfoRequestModel;
import com.WheelerDealer.spring.response.car.GetCarResponseModel;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface CarDao {
    Connection conn = DatabaseConnection.getConnection();

    List<GetCarResponseModel> getAllCars();

    List<GetCarResponseModel> searchCars(SearchCarModel searchCarModel, List<GetCarResponseModel> cars);

    List<GetCarResponseModel> getAvailableCars(LocalDate startDate, LocalDate endDate);

    boolean isCarAvailable(LocalDate startDate, LocalDate endDate, String carId);

    GetCarResponseModel getCar(String id);

    void updateCarInfo(String id, ChangeCarInfoRequestModel carInfo);

    void deleteCar(String id);

    void addCar(AddCarRequestModel car);

    double getPrice(String id);
}
