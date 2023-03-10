package com.example.carCrud;

import com.example.carCrud.exception.InvalidRequestException;
import com.example.carCrud.model.Brand;
import com.example.carCrud.model.Car;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/create")
    public ResponseEntity<Car> create(@Valid @RequestBody Car car, BindingResult result) {
        if(result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Car> update(@PathVariable Long id, @Valid @RequestBody Car updatedCar, BindingResult result) {
        if(result.hasErrors()) {
            throw new InvalidRequestException(result);
        }
        return new ResponseEntity<>(carService.update(id, updatedCar), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return new ResponseEntity<>(carService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Car>> getCarsByBrand(@RequestParam(required = false) String brand) {
        return new ResponseEntity<>(carService.getCarsByBrand(brand), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getById(id), HttpStatus.OK);
    }
}