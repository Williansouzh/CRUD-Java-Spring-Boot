package com.api.parking_control.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parking_control.models.ParkingSpotModel;

@Repository
public interface ParkinSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
}
