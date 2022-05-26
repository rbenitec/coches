package com.rbenitez.userservice.service;

import com.rbenitez.userservice.config.RestTemplateConfig;
import com.rbenitez.userservice.entity.User;
import com.rbenitez.userservice.feingclients.BikeFeingClient;
import com.rbenitez.userservice.feingclients.CarFeingClient;
import com.rbenitez.userservice.model.Bike;
import com.rbenitez.userservice.model.Car;
import com.rbenitez.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeingClient carFeingClient;

    @Autowired
    BikeFeingClient bikeFeingClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    //Se agrego, quiero obtener los carros del usuario // Se usa Rest Template

    public List<Car> getCars(int userId){
//        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/"+userId, List.class); // <----sin eureka
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byuser/"+userId, List.class); // <----con eureka
        return cars;
    }

    public List<Bike> getBikes(int userId){
//        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);// <----sin eureka
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byuser/"+userId, List.class);
        return bikes;
    }

    //Trabajando con OpenFeing Guardar un carro desde el microservicio Usuario
    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeingClient.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeingClient.save(bike);
        return bikeNew;
    }

    //Obtener los car y bikes de un usuario

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();

        //Obtenemos el usuario ------------------------------------------------------------------>
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje","no existe el usuario");
            return result;
        }
        result.put("User", user);

        //Obtenemos los carros del usuario ------------------------------------------------------------------>
        List<Car> cars = carFeingClient.getCarsByUserId(userId);
        if(cars.isEmpty()){
            result.put("Cars", "ese user no tiene cars");
        }else {
            result.put("Cars", cars);
        }

        //Obtenemis los bikes del usuario ------------------------------------------------------------------>
        List<Bike> bikes = bikeFeingClient.getBikesByUserId(userId);
        if(bikes.isEmpty()){
            result.put("Bikes", "EL usuario no tiene Bikes");
        }else {
            result.put("Bikes", bikes);
        }

        return result;

    }
}
