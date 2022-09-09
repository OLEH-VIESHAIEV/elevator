package service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Elevator;
import model.Floor;
import model.Passenger;
import service.ElevatorService;
import service.Generator;

public class ElevatorServiceImpl implements ElevatorService {
    private final Generator generator;
    private static final Integer MAX_PASSENGERS = 5;
    private static final Integer FIRST_FLOOR = 1;
    private static int[] direct = new int[MAX_PASSENGERS];
    private static int[][] arrDisplay;

    public ElevatorServiceImpl(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run(List<Floor> floors) {
        List<Passenger> passengersInsideElevator;
        Integer passengerLetEntry;
        int count = 0;
        int index = 0;
        Elevator elevator = new Elevator(MAX_PASSENGERS);
        elevator.setUp(true);
        Floor floor = floors.get(count);
        List<Passenger> passengers = passengersUp(floor, MAX_PASSENGERS);
        elevator.setPassengersInside(passengers);
        floor.getPassengersOnCurrentFloor().removeAll(passengers);

        direct = addNumsForDisplay(elevator);
        arrDisplay = new int[floors.size()][];
        boolean empty = false;

        while (!empty) {

            System.out.println("*** STEP " + (++count) + " ***");
            if (!elevator.isUp()) {
                floor = floors.get(--index);
            }
            if (!elevator.isUp() && index == 0) {
                elevator.setUp(true);
            }
            if (elevator.isUp()) {
                floor = floors.get(index++);
            }

            passengers = passengersOut(elevator.getPassengersInside(), floor.getNumber());
            for (Passenger passenger : passengers) {
                passenger.setRequiredFloor(generator.generateFloorForPassenger(FIRST_FLOOR,
                        floors.size(), passenger.getRequiredFloor()));
            }
            elevator.getPassengersInside().removeAll(passengers);
            firstFloorOut(passengers, floor);

            passengerLetEntry = MAX_PASSENGERS - elevator.getPassengersInside().size();
            if (elevator.getPassengersInside().isEmpty()) {
                floor.getPassengersOnCurrentFloor().addAll(passengers);

                if (floor.getPassengersOnCurrentFloor().isEmpty()) {
                    floor.getPassengersOnCurrentFloor().addAll(passengers);
                    int floorNumber = moveOnCall(floors);
                    floor = floors.get(floorNumber);
                }
                passengersInsideElevator = selectPassenger(floor, elevator, passengerLetEntry);
                floor.getPassengersOnCurrentFloor().removeAll(passengersInsideElevator);
                firstFloorOut(passengers, floor);
            } else {
                if (elevator.isUp()) {
                    passengersInsideElevator = passengersUp(floor, passengerLetEntry);
                } else {
                    passengersInsideElevator = passengersDown(floor, passengerLetEntry);
                }
                elevator.getPassengersInside().addAll(passengersInsideElevator);
                floor.getPassengersOnCurrentFloor().removeAll(passengersInsideElevator);
                floor.getPassengersOnCurrentFloor().addAll(passengers);
            }
            direct = addNumsForDisplay(elevator);
            setArr(floors);
            display(elevator, floor);

            empty = isEmptyFloors(floors) && isEmptyElevator(elevator);

            if (elevator.isUp()) {
                floor = floor.getNext();
            } else {
                floor = floor.getPrevious();
            }
        }
        System.out.println("----All people are outside!----");
    }

    private List<Passenger> passengersUp(Floor floor, Integer passengerQuantity) {
        return floor
                .getPassengersOnCurrentFloor()
                .stream()
                .filter(p -> p.getRequiredFloor() > floor.getNumber())
                .limit(passengerQuantity)
                .collect(Collectors.toList());
    }

    private List<Passenger> passengersDown(Floor floor, Integer passengerQuantity) {
        return floor
                .getPassengersOnCurrentFloor()
                .stream()
                .filter(p -> p.getRequiredFloor() < floor.getNumber())
                .limit(passengerQuantity)
                .collect(Collectors.toList());
    }

    private int[] addNumsForDisplay(Elevator elevator) {
        return elevator.getPassengersInside()
                .stream()
                .mapToInt(Passenger::getRequiredFloor)
                .toArray();
    }

    private List<Passenger> passengersOut(List<Passenger> passengersInsideElevator,
                                          Integer floorNumber) {
        return passengersInsideElevator
                .stream()
                .filter(p -> p.getRequiredFloor() == floorNumber)
                .collect(Collectors.toList());
    }

    private void firstFloorOut(List<Passenger> passengers, Floor floor) {
        if (floor.getNumber() == FIRST_FLOOR) {
            passengers.clear();
        }
    }

    private int moveOnCall(List<Floor> floors) {
        List<List<Passenger>> allPassengers = floors.stream()
                .map(Floor::getPassengersOnCurrentFloor)
                .collect(Collectors.toList());

        LocalDateTime time = LocalDateTime.MAX;
        Passenger passenger;
        int floorNumber = 0;

        for (int i = 0; i < allPassengers.size(); i++) {
            for (int j = 0; j < allPassengers.get(i).size(); j++) {
                passenger = allPassengers.get(i).get(j);
                if (time.isAfter(passenger.getTimeWaiting())) {
                    time = passenger.getTimeWaiting();
                    floorNumber = i;
                }
            }
        }
        return floorNumber;
    }

    private List<Passenger> selectPassenger(Floor floor,
                                            Elevator elevator,Integer passengerLetEntry) {
        List<Passenger> passengersInsideElevator = new ArrayList<>();
        boolean up;
        try {
            up = upDirection(floor.getPassengersOnCurrentFloor().get(0), floor.getNumber());
        } catch (IndexOutOfBoundsException e) {
            return passengersInsideElevator;
        }
        if (up) {
            passengersInsideElevator = passengersUp(floor, passengerLetEntry);
        } else {
            passengersInsideElevator = passengersDown(floor, passengerLetEntry);
        }
        elevator.getPassengersInside().addAll(passengersInsideElevator);
        elevator.setUp(up);
        return passengersInsideElevator;
    }

    private boolean upDirection(Passenger passenger, int floorNumber) {
        return passenger.getRequiredFloor() > floorNumber;
    }

    private static void setArr(List<Floor> floors) {
        for (int i = 0; i < floors.size(); i++) {
            Floor floor = floors.get(i);
            List<Passenger> passengers = floor.getPassengersOnCurrentFloor();
            arrDisplay[i] = new int[passengers.size()];
            for (int j = 0; j < passengers.size(); j++) {
                arrDisplay[i][j] = passengers.get(j).getRequiredFloor();
            }
        }
    }

    private static void display(Elevator elevator, Floor floor) {
        for (int i = arrDisplay.length - 1, n = arrDisplay.length; i >= 0; i--, n--) {
            System.out.print(" floor" + n + " | ");
            for (int j = 0; j < arrDisplay[i].length; j++) {
                System.out.printf("%3d", arrDisplay[i][j]);
            }
            System.out.print("  ||     ");
            if (elevator.isUp() && floor.getNumber() == n) {
                System.out.print("^");
                for (int value : direct) {
                    System.out.printf("%3d", value);
                }
            } else if (!elevator.isUp() && floor.getNumber() == n) {
                System.out.print("v");
                for (int value : direct) {
                    System.out.printf("%3d", value);
                }
            }
            System.out.println();
        }
    }

    private boolean isEmptyFloors(List<Floor> floors) {
        return floors.stream()
                .allMatch(x -> x.getPassengersOnCurrentFloor().isEmpty());
    }

    private boolean isEmptyElevator(Elevator elevator) {
        return elevator.getPassengersInside().isEmpty();
    }
}
