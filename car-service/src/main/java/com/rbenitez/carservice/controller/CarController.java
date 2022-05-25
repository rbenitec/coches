package com.rbenitez.carservice.controller;

import com.rbenitez.carservice.entity.Car;
import com.rbenitez.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;
    //List
    @GetMapping()
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }
    //Encontrar
    @GetMapping("{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }
    //Guardar
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car newCar = carService.saveCar(car);
        return ResponseEntity.ok(newCar);
    }

    //Metodo para encontrar todos los carros que tiene un usario
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carService.CarByUserId(userId);
        /*
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }*/

        return ResponseEntity.ok(cars);
    }

}
