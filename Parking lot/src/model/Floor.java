package model;

import java.util.List;

public class Floor {
    private int floorNumber;
    private List<Slot> slots;

    public Floor(int floorNumber, List<Slot> slots) {
        this.floorNumber = floorNumber;
        this.slots = slots;
    }

    public List<Slot> getSlots() { return slots; }
    public int getFloorNumber() { return floorNumber; }
}