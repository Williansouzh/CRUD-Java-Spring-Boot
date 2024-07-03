package com.api.parking_control.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.parking_control.models.ParkingSpotModel;
import com.api.parking_control.repositories.ParkinSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {
    final ParkinSpotRepository parkingRepository;
    public ParkingSpotService(ParkinSpotRepository parkinSpotRepository){
        this.parkingRepository = parkinSpotRepository;
    }
    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingRepository.save(parkingSpotModel);
    }
    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingRepository.existsByLicensePlateCar(licensePlateCar);
    }
    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }
    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingRepository.existsByApartmentAndBlock(apartment, block);
    }
    public List<ParkingSpotModel> findAll() {
        return parkingRepository.findAll();
    }
    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingRepository.findById(id);
    }
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingRepository.delete(parkingSpotModel);
    }
}
