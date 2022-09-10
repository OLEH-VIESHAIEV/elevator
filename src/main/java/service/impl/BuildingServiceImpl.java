package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.Building;
import model.Floor;
import service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
    @Override
    public Building create(Integer quantityFloors) {
        Building building = new Building(quantityFloors);
        building.setFloors(generateFloors(building));
        return building;
    }

    private List<Floor> generateFloors(Building building) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= building.getTotalNumberOfFloors(); i++) {
            floors.add(new Floor(i));
        }
        return floors;
    }
}
