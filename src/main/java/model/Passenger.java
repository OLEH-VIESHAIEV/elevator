package model;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Passenger implements Comparator<Passenger> {
    private Integer requiredFloor;
    private LocalDateTime timeWaiting;

    public Passenger() {
    }

    public Passenger(Integer requiredFloor, LocalDateTime timeWaiting) {
        this.requiredFloor = requiredFloor;
        this.timeWaiting = timeWaiting;
    }

    public Integer getRequiredFloor() {
        return requiredFloor;
    }

    public void setRequiredFloor(Integer requiredFloor) {
        this.requiredFloor = requiredFloor;
    }

    public LocalDateTime getTimeWaiting() {
        return timeWaiting;
    }

    public void setTimeWaiting(LocalDateTime timeWaiting) {
        this.timeWaiting = timeWaiting;
    }

    @Override
    public String toString() {
        return "Passenger{"
                + "requiredFloor=" + requiredFloor
                + ", timeWaiting=" + timeWaiting
                + '}';
    }

    @Override
    public int compare(Passenger o1, Passenger o2) {
        return o1.getTimeWaiting().compareTo(o2.getTimeWaiting());
    }
}
