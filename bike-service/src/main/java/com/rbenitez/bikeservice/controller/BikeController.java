package com.rbenitez.bikeservice.controller;

import com.rbenitez.bikeservice.entity.Bike;
import com.rbenitez.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    //Listar
    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(bikes);
    }

    //Buscar por Id
    @GetMapping("{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id){
        Bike bike = bikeService.getBikeById(id);
        if(bike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    //Guardar
    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike newBike = bikeService.saveBike(bike);
        return ResponseEntity.ok(newBike);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable("userId") int userId){
        List<Bike> bikes = bikeService.getBikeByUserId(userId);

        /*
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }*/
        return ResponseEntity.ok(bikes);

    }

}
