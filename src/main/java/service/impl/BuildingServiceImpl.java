package service.impl;

import model.Building;
import model.FloorGenerator;
import service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
    @Override
    public Building create(Integer quantityFloors) {
        Building building = new Building(quantityFloors);
        building.setFloors(FloorGenerator.generateFloors(building));
        return building;
    }
}
