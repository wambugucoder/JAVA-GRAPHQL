package com.graphql.javagraphql.queryResolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.javagraphql.model.Vehicle;
import com.graphql.javagraphql.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VehicleQuery implements GraphQLQueryResolver {
    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> getAllVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleService.getVehicle(id);
    }
}
