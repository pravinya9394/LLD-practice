package model;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private Slot slot;

    public Ticket(String ticketId, Vehicle vehicle, Slot slot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public Slot getSlot() { return slot; }
}