import model.*;
import model.enums.SlotType;
import model.enums.VehicleType;
import repository.ParkingLotRepository;
import service.ParkingService;
import service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing parking lot...");

        List<Slot> slots = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Slot slot = new Slot("S" + i, SlotType.MEDIUM);
            slots.add(slot);
            ParkingLotRepository.addSlot(slot); // register for optimized lookup
        }

        Floor floor = new Floor(1, slots);
        ParkingLotRepository.addFloor(floor);

        System.out.println("Added 1 floor with 5 medium slots.");

        ParkingService parkingService = new ParkingService(new TicketService());

        Vehicle vehicle = new Vehicle("MH12AB1234", VehicleType.CAR);
        System.out.println("Attempting to park vehicle: " + vehicle.getVehicleNumber());

        Ticket ticket = parkingService.parkVehicle(vehicle);
        if (ticket != null) {
            System.out.println("Vehicle parked successfully.");
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Allocated Slot: " + ticket.getSlot().getSlotId());

            System.out.println("Attempting to unpark vehicle...");
            parkingService.unparkVehicle(ticket);
        } else {
            System.out.println("Parking full. Vehicle could not be parked.");
        }
    }
}
