package model;

import model.enums.SlotType;

public class Slot {
    private String slotId;
    private SlotType slotType;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public Slot(String slotId, SlotType slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public boolean isAvailable() { return !isOccupied; }
    public boolean isOccupied() { return isOccupied; }
    public void assignVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }
    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
    public String getSlotId() { return slotId; }
    public SlotType getSlotType() { return slotType; }
}
