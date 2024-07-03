package com.api.parking_control.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parking_control.dtos.ParkingSpotDto;
import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge= 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    final ParkingSpotService parkingSpotservice;
    public ParkingSpotController(ParkingSpotService parkingSpotService){
        this.parkingSpotservice = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        if(parkingSpotservice.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit licensePlateCar is already in use!");
        }
        if(parkingSpotservice.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit parkingSpotNumber is already in use!");
        }
        if(parkingSpotservice.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflit parkingSpot already registered for this apartment/block");
        }

        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotservice.save(parkingSpotModel));
    }
    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotservice.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotservice.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotservice.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ParkingSpotNotFound");
        }
        parkingSpotservice.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("ParkingSpot deleted");
    }
    @PutMapping("{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotservice.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ParkingSpotNotFound");
        }
        var parkingSpotModel = parkingSpotModelOptional.get();
        parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
        parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
        parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
        parkingSpotModel.setApartment(parkingSpotDto.getApartment());
        parkingSpotModel.setBlock(parkingSpotDto.getBlock());
        parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotservice.save(parkingSpotModel));
    }
}
