package model;

import java.util.List;

public class Building {
    private Integer totalNumberOfFloors;
    private Elevator elevator;
    private List<Floor> floors;

    public Building(Integer totalNumberOfFloors) {
        this.totalNumberOfFloors = totalNumberOfFloors;
    }

    public Integer getTotalNumberOfFloors() {
        return totalNumberOfFloors;
    }

    public void setTotalNumberOfFloors(Integer totalNumberOfFloors) {
        this.totalNumberOfFloors = totalNumberOfFloors;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Building{"
                + "totalNumberOfFloors=" + totalNumberOfFloors
                + ", elevator=" + elevator
                + ", floors=" + floors
                + '}';
    }
}
