package service;

import model.Slot;
import model.Ticket;
import model.Vehicle;

import java.util.UUID;

public class TicketService {
    public Ticket createTicket(Vehicle vehicle, Slot slot) {
        return new Ticket(UUID.randomUUID().toString(), vehicle, slot);
    }
}