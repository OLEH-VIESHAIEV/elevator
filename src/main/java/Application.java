import java.util.List;
import model.Building;
import model.Floor;
import service.BuildingService;
import service.ElevatorService;
import service.FloorService;
import service.Generator;
import service.impl.BuildingServiceImpl;
import service.impl.ElevatorServiceImpl;
import service.impl.FloorServiceImpl;
import service.impl.GeneratorImpl;

public class Application {
    private static final Integer MIN_FLOOR = 5;
    private static final Integer MAX_FLOOR = 20;

    public static void main(String[] args) {
        Generator generator = new GeneratorImpl();
        Integer floorQuantity = generator.generateFloorQuantity(MIN_FLOOR, MAX_FLOOR);
        BuildingService buildingService = new BuildingServiceImpl();
        Building building = buildingService.create(floorQuantity);//
        FloorService floorService = new FloorServiceImpl();
        floorService.generatePassengersOnFloor(building.getFloors());
        List<Floor> floors = building.getFloors();
        ElevatorService elevatorService = new ElevatorServiceImpl();
        elevatorService.run(floors);
    }
}
