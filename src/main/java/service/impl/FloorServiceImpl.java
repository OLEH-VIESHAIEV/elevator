package service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Floor;
import model.Passenger;
import service.FloorService;
import service.Generator;

public class FloorServiceImpl implements FloorService {
    private final Generator generator;
    private static final Integer MIN_ON_FLOOR = 0;
    private static final Integer MAX_ON_FLOOR = 10;
    private static final Integer FIRST_FLOOR = 1;

    public FloorServiceImpl(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void generatePassengersOnFloor(List<Floor> floors) {
        for (int i = 0; i < floors.size(); i++) {
            Random random = new Random();
            Integer passengerQuantity = random
                    .ints(MIN_ON_FLOOR, MAX_ON_FLOOR + 1)
                    .findFirst()
                    .getAsInt();
            List<Passenger> passengersOnFloor = new ArrayList<>();
            for (int j = 0; j < passengerQuantity; j++) {
                Passenger passenger = new Passenger();
                passenger.setRequiredFloor(generator
                        .generateFloorForPassenger(FIRST_FLOOR,
                                floors.size(), floors.get(i).getNumber()));
                passenger.setTimeWaiting(LocalDateTime.now());
                passengersOnFloor.add(passenger);
            }
            floors.get(i).setPassengersOnCurrentFloor(passengersOnFloor);
        }
    }
}
