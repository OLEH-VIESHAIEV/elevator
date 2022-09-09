package model;

import java.util.List;

public class Floor {
    private List<Passenger> passengersOnCurrentFloor;
    private Integer number;
    private Floor next;
    private Floor previous;

    public Floor(Integer number) {
        this.number = number;
    }

    public List<Passenger> getPassengersOnCurrentFloor() {
        return passengersOnCurrentFloor;
    }

    public void setPassengersOnCurrentFloor(List<Passenger> passengersOnCurrentFloor) {
        this.passengersOnCurrentFloor = passengersOnCurrentFloor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Floor getNext() {
        return next;
    }

    public void setNext(Floor next) {
        this.next = next;
    }

    public Floor getPrevious() {
        return previous;
    }

    public void setPrevious(Floor previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Floor{"
                + "passengersOnCurrentFloor=" + passengersOnCurrentFloor
                + ", number=" + number
                + '}';
    }
}
