package service;

import java.util.Random;

public interface Generator {
    static Integer generateFloorForPassenger(Integer min, Integer max, Integer current) {
        Random random = new Random();
        Integer newFloor = random.ints(min, max + 1).findFirst().getAsInt();
        while (newFloor == current) {
            newFloor = generateFloorForPassenger(min, max, newFloor);
        }
        return newFloor;
    }

    Integer generateFloorQuantity(Integer min, Integer max);
}
