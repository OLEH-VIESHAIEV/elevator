package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Building;
import model.Floor;
import service.Generator;

public class GeneratorImpl implements Generator {
    @Override
    public List<Floor> generateFloors(Building building) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= building.getTotalNumberOfFloors(); i++) {
            floors.add(new Floor(i));
        }
        return floors;
    }

    @Override
    public Integer generateFloorForPassenger(Integer min, Integer max, Integer current) {
        Random random = new Random();
        Integer newFloor = random.ints(min, max + 1).findFirst().getAsInt();
        while (newFloor == current) {
            newFloor = generateFloorForPassenger(min, max, newFloor);
        }
        return newFloor;
    }

    @Override
    public Integer generateFloorQuantity(Integer min, Integer max) {
        Random random = new Random();
        Integer floorQuantity = random.ints(min, max + 1).findFirst().getAsInt();
        return floorQuantity;
    }
}
