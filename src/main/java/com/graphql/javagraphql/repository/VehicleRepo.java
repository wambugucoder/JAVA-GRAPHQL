package com.graphql.javagraphql.repository;

import com.graphql.javagraphql.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle,Integer> {
}
