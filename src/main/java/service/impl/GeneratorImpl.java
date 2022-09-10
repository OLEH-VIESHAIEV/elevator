package service.impl;

import java.util.Random;
import service.Generator;

public class GeneratorImpl implements Generator {
    @Override
    public Integer generateFloorQuantity(Integer min, Integer max) {
        Random random = new Random();
        Integer floorQuantity = random.ints(min, max + 1).findFirst().getAsInt();
        return floorQuantity;
    }
}
