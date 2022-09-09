package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloorGenerator {
    public static List<Floor> generateFloors(Building building) {
        List<Floor> floors = new ArrayList<>();
        for (int i = 1; i <= building.getTotalNumberOfFloors(); i++) {
            floors.add(new Floor(i));
        }
        return floors;
    }

    public static Integer generateFloorForPassenger(Integer min, Integer max, Integer current) {
        Random random = new Random();
        Integer newFloor = random.ints(min, max + 1).findFirst().getAsInt();
        while (newFloor == current) {
            newFloor = generateFloorForPassenger(min, max, newFloor);
        }
        return newFloor;
    }
}
