package com.rbenitez.userservice.feingclients;

import com.rbenitez.userservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(name = "bike-service", url = "http://localhost:8003/bike")  // <--- Sin Eureka

@FeignClient(name = "bike-service", path = "/bike")  // <--- Con Eureka
public interface BikeFeingClient {

    //Para gurdar un Bike de un usuario
    @PostMapping()
    Bike save(@RequestBody Bike bike);

    //Para obtener las bikes de un usuario
    @GetMapping("/byuser/{userId}")
    List<Bike> getBikesByUserId(@PathVariable("userId") int userId);
}
