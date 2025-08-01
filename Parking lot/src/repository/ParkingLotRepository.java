package repository;

import model.Floor;
import model.Slot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
    private static final List<Floor> floors = new ArrayList<>();
    private static final List<Slot> availableSlots = new ArrayList<>();

    public static void addFloor(Floor floor) {
        floors.add(floor);
    }

    public static List<Floor> getFloors() {
        return floors;
    }

    public static void addSlot(Slot slot) {
        availableSlots.add(slot);
    }

    public static Slot getAvailableSlot() {
        for (Slot slot : availableSlots) {
            if (slot.isAvailable()) {
                return slot;
            }
        }
        return null;
    }
}