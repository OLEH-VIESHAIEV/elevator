import java.util.List;
import java.util.Random;
import model.Building;
import model.Floor;
import service.BuildingService;
import service.ElevatorService;
import service.FloorService;
import service.impl.BuildingServiceImpl;
import service.impl.ElevatorServiceImpl;
import service.impl.FloorServiceImpl;

public class Application {
    private static final Integer MIN_FLOOR = 5;
    private static final Integer MAX_FLOOR = 20;

    public static void main(String[] args) {

        Random random = new Random();
        Integer floorQuantity = random.ints(MIN_FLOOR, MAX_FLOOR + 1).findFirst().getAsInt();
        BuildingService buildingService = new BuildingServiceImpl();
        Building building = buildingService.create(floorQuantity);
        FloorService floorService = new FloorServiceImpl();
        floorService.generatePassengersOnFloor(building.getFloors());
        List<Floor> floors = building.getFloors();
        ElevatorService elevatorService = new ElevatorServiceImpl();
        elevatorService.run(floors);

    }
}
