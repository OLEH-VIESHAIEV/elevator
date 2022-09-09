package service;

import java.util.List;
import model.Building;
import model.Floor;

public interface Generator {
    List<Floor> generateFloors(Building building);

    Integer generateFloorForPassenger(Integer min, Integer max, Integer current);

    Integer generateFloorQuantity(Integer min, Integer max);
}
