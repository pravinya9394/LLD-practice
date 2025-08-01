package service;

import model.*;
import repository.ParkingLotRepository;

public class ParkingService {
    private final TicketService ticketService;

    public ParkingService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Slot slot = ParkingLotRepository.getAvailableSlot();
        if (slot != null) {
            slot.assignVehicle(vehicle);
            return ticketService.createTicket(vehicle, slot);
        }
        return null;
    }

    public void unparkVehicle(Ticket ticket) {
        if (ticket != null && ticket.getSlot() != null && ticket.getSlot().isOccupied()) {
            Slot slot = ticket.getSlot();
            slot.removeVehicle();
            System.out.println("Vehicle " + ticket.getVehicle().getVehicleNumber() + " unparked from Slot " + slot.getSlotId());
        }
    }
}
