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

//Schema design
//-- Floors: parent entity
//CREATE TABLE floor (
//        id SERIAL PRIMARY KEY,
//        floor_number INT NOT NULL UNIQUE
//);
//
//-- Slots: child entity of floor
//CREATE TABLE slot (
//        id VARCHAR(32) PRIMARY KEY,   -- or use (floor_id, slot_number) composite PK
//floor_id INT NOT NULL REFERENCES floor(id) ON DELETE CASCADE,
//slot_type VARCHAR(10) NOT NULL CHECK (slot_type IN ('SMALL','MEDIUM','LARGE'))
//        );
//
//        -- Vehicles: independent entity
//CREATE TABLE vehicle (
//        vehicle_number VARCHAR(20) PRIMARY KEY,  -- license plate
//vehicle_type VARCHAR(10) NOT NULL CHECK (vehicle_type IN ('CAR','BIKE','TRUCK'))
//        );
//
//        -- Tickets: allocation history (source of truth for occupancy)
//CREATE TABLE ticket (
//        id UUID PRIMARY KEY,
//        vehicle_number VARCHAR(20) NOT NULL REFERENCES vehicle(vehicle_number),
//slot_id VARCHAR(32) NOT NULL REFERENCES slot(id),
//created_at TIMESTAMP NOT NULL DEFAULT NOW(),
//closed_at TIMESTAMP NULL
//);
//
//        -- Optional indexes for performance
//CREATE INDEX idx_slot_floor ON slot(floor_id);
//CREATE INDEX idx_ticket_slot_open ON ticket(slot_id) WHERE closed_at IS NULL;
//CREATE INDEX idx_ticket_vehicle_open ON ticket(vehicle_number) WHERE closed_at IS NULL;
//
//-- Business rules: prevent double parking
//CREATE UNIQUE INDEX uq_open_ticket_per_slot
//ON ticket(slot_id) WHERE closed_at IS NULL;
//
//CREATE UNIQUE INDEX uq_open_ticket_per_vehicle
//ON ticket(vehicle_number) WHERE closed_at IS NULL;
