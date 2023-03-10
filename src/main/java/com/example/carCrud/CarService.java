package com.example.carCrud;

import com.example.carCrud.exception.CarNotFoundException;
import com.example.carCrud.model.Brand;
import com.example.carCrud.model.Car;
import com.example.carCrud.repository.BrandRepository;
import com.example.carCrud.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;

    public Car createCar(Car car) {
        Brand brand = brandRepository.findByName(car.getBrand().getName());
        if (brand == null) {
            brand = new Brand();
            brand.setName(car.getBrand().getName());
            brand = brandRepository.save(brand);
        }
        car.setBrand(brand);
        return carRepository.save(car);
    }

    public Car update(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    // Check if the Brand exists
                    Brand brand = brandRepository.findByName(updatedCar.getBrand().getName());
                    if (brand == null) {
                        // If the Brand doesn't exist, create a new one
                        brand = new Brand();
                        brand.setName(updatedCar.getBrand().getName());
                        brand = brandRepository.save(brand);
                    }
                    car.setBrand(brand);
                    car.setModel(updatedCar.getModel());
                    car.setVin(updatedCar.getVin());
                    car.setRegistrationNumber(updatedCar.getRegistrationNumber());
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public List<Car> getCarsByBrand(@RequestParam(required = false) String brand) {
        if (brand == null) {
            return carRepository.findAll();
        } else {
            return carRepository.findByBrandName(brand);
        }
    }

    public Car getById(@PathVariable Long id) {
        return carRepository.findById(id).orElseThrow();
    }
}
