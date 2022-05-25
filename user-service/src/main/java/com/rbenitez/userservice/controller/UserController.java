package com.rbenitez.userservice.controller;

import com.rbenitez.userservice.entity.User;
import com.rbenitez.userservice.model.Bike;
import com.rbenitez.userservice.model.Car;
import com.rbenitez.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //Lista
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);

    }
    //buscar

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);

    }
    //Guardar
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    //leer la cantidad de carros y bikes que tiene el usuario // Se usa Rest Template

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }


    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);

        if(user == null){
            return  ResponseEntity.notFound().build();
        }
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }





    /*    --- Metodo creado falta probar ---
    @GetMapping("/cars/{userId}")
    public List<Car> getCars(@PathVariable("userId") int userId){
        return userService.getCars(userId);
    }

    @GetMapping("/bikes/{userId}")
    public List<Bike> getBikes(@PathVariable("userId") int userId){
        return userService.getBikes(userId);
    }
    */

    //guardar car y bake desde el microservicio user // Se usa OpenFeing

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCarbyUserId(@PathVariable("userId") int userId,@RequestBody Car car){

        if(userService.getUserById(userId)==null){
            return ResponseEntity.notFound().build();
        }
        Car carNew = userService.saveCar(userId,car);
        return ResponseEntity.ok(carNew);
    }


    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBikeByUserId(@PathVariable("userId") int userId,@RequestBody Bike bike) {
        if(userService.getUserById(userId)==null){
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.saveBike(userId,bike);
        return ResponseEntity.ok(bikeNew);
    }
    //____________________________________________________________________

    // obtener todos los usuarios, los carros, y los bikes
    @GetMapping("/getall/{userId}")
    public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String,Object> res = userService.getUserAndVehicles(userId);
        /*
        if(res.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        */

        return ResponseEntity.ok(res);
    }
}
