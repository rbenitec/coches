package com.rbenitez.bikeservice.service;

import com.rbenitez.bikeservice.entity.Bike;
import com.rbenitez.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id){
        return bikeRepository.getById(id);
    }

    public Bike saveBike(Bike bike){
        return bikeRepository.save(bike);
    }

    //Interactua con id del usuario
    public List<Bike> getBikeByUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }

}
