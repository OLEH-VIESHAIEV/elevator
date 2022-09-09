package service.impl;

import model.Building;
import service.BuildingService;
import service.Generator;

public class BuildingServiceImpl implements BuildingService {
    private final Generator generator;

    public BuildingServiceImpl(Generator generator) {
        this.generator = generator;
    }

    @Override
    public Building create(Integer quantityFloors) {
        Building building = new Building(quantityFloors);
        building.setFloors(generator.generateFloors(building));
        return building;
    }
}
