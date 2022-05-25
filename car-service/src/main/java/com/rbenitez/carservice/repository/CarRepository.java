package com.rbenitez.carservice.repository;

import com.rbenitez.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    //Esto es nuevo, nos permite saber cuantos carros tiene un usario(Microservicio)
    List<Car> findByUserId(int userId);
}
