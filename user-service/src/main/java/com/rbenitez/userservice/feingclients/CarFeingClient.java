package com.rbenitez.userservice.feingclients;

import com.rbenitez.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "car-service", url = "http://localhost:8002/car")  //  <--- Sin eureka

@FeignClient(name = "car-service", path = "/car")  //  <--- Con eureka, se quita la Url y aque esta registrado en eureka.
public interface CarFeingClient {
    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCarsByUserId(@PathVariable("userId") int userId);

}


