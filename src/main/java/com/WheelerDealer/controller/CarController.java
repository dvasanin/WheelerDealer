package com.WheelerDealer.controller;


import com.WheelerDealer.dao.*;
import com.WheelerDealer.spring.misc.SearchCarModel;
import com.WheelerDealer.spring.request.car.AddCarRequestModel;
import com.WheelerDealer.spring.request.car.ChangeCarInfoRequestModel;
import com.WheelerDealer.spring.response.car.GetCarResponseModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CarController {
    private final CarDao carDao = new CarDaoSQL();
    private final UserDao userDao = new UserDaoSQL();
    private final ContractDao contractDao = new ContractDaoSQL();


    //cars - GET
    @GetMapping("/cars")
    public List<GetCarResponseModel> getAllCars() {
        return carDao.getAllCars();
    }

    //TODO Zavrsi prvo contracts
    //cars - GET (@RequestParam)
    @GetMapping("/cars/search")
    public List<GetCarResponseModel> searchCars(@RequestParam(required = false) String make,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) Integer year,
                                                @RequestParam(required = false) Boolean automatic,
                                                @RequestParam(required = false) Double price,
                                                @RequestParam(required = false) Integer power,
                                                @RequestParam(required = false) Integer doors) {
        return carDao.searchCars(new SearchCarModel(make, model, year, power, doors, price, automatic),
                carDao.getAllCars());
    }

    //cars/{carId} - GET
    @GetMapping("/cars/{carId}")
    public GetCarResponseModel getCar(@PathVariable("carId") String id) {
        return carDao.getCar(id);
    }

    //cars/{carId} - UPDATE
    @PatchMapping("/cars/{carId}")
    public void updateCar(@PathVariable("carId") String carId,
                          @RequestHeader("authorization") String adminId,
                          @RequestBody ChangeCarInfoRequestModel carInfo) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.updateCarInfo(carId, carInfo);
    }

    //cars/{carId} - DELETE (@RequestHeader ("authorization"))
    @DeleteMapping("/cars/{carId}")
    public void deleteCar(@PathVariable("carId") String carId,
                          @RequestHeader("authorization") String adminId) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.deleteCar(carId);
    }

    //cars - POST (@RequestHeader ("authorization")) ADD
    @PostMapping("/cars")
    public void addCar(@RequestHeader("authorization") String adminId,
                       @RequestBody AddCarRequestModel car) {
        if (!userDao.isAdmin(adminId)) {
            return;
        }
        carDao.addCar(car);
    }

    @GetMapping("/cars/available")
    public List<GetCarResponseModel> availableCars(@RequestParam String startDate,
                                                   @RequestParam String endDate) {
        LocalDate startDateLocal = LocalDate.parse(startDate);
        LocalDate endDateLocal = LocalDate.parse(endDate);
        return carDao.getAvailableCars(startDateLocal, endDateLocal);
    }

    @GetMapping("/cars/available/search")
    public List<GetCarResponseModel> searchCars(@RequestParam String startDate,
                                                @RequestParam String endDate,
                                                @RequestParam(required = false) String make,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) Integer year,
                                                @RequestParam(required = false) Boolean automatic,
                                                @RequestParam(required = false) Double price,
                                                @RequestParam(required = false) Integer power,
                                                @RequestParam(required = false) Integer doors) {
        LocalDate startDateLocal = LocalDate.parse(startDate);
        LocalDate endDateLocal = LocalDate.parse(endDate);
        return carDao.searchCars(new SearchCarModel(make, model, year, power, doors, price, automatic),
                carDao.getAvailableCars(startDateLocal, endDateLocal));

    }

    @GetMapping("/cars/{carId}/calendar")
    public List<LocalDate> getCarOccupied(@PathVariable("carId") String id) {
        return contractDao.getCarOccupiedDates(id);
    }
}
