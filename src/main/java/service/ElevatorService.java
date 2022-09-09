package service;

import java.util.List;
import model.Floor;

public interface ElevatorService {
    void run(List<Floor> floors);
}
