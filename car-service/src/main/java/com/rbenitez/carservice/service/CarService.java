package com.rbenitez.carservice.service;

import com.rbenitez.carservice.entity.Car;
import com.rbenitez.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    //Combinar entre microservicios
    public List<Car> CarByUserId(int userId){
        return carRepository.findByUserId(userId);
    }


}
