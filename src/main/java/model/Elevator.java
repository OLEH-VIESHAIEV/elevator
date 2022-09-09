package model;

import java.util.List;

public class Elevator {
    private Integer maxCapacity;
    private List<Passenger> passengersInside;
    private boolean up;
    private Integer currentFloor;
    private Integer maxFloor;

    public Elevator(final Integer maxCapacity) {

        this.maxCapacity = maxCapacity;
    }

    public Integer getMaxCapacity() {

        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {

        this.maxCapacity = maxCapacity;
    }

    public List<Passenger> getPassengersInside() {

        return passengersInside;
    }

    public void setPassengersInside(List<Passenger> passengersInside) {

        this.passengersInside = passengersInside;
    }

    public Boolean isUp() {
        return up;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Integer getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Integer getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(Integer maxFloor) {
        this.maxFloor = maxFloor;
    }

    @Override
    public String toString() {
        return "Elevator{"
                + "maxCapacity=" + maxCapacity
                + ", passengersInside=" + passengersInside
                + '}';
    }
}
