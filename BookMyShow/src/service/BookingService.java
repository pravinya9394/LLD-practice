package service;

import model.Seat;
import model.Show;
import model.User;

import java.util.*;

public class BookingService {
    private final Map<String, Object> showLocks = new HashMap<>();

    public synchronized boolean bookSeats(Show show, List<Integer> seatNumbers, User user) {
        showLocks.putIfAbsent(show.getShowId(), new Object());

        synchronized (showLocks.get(show.getShowId())) {
            List<Seat> seats = show.getScreen().getSeats();
            for (int seatNumber : seatNumbers) {
                if (seatNumber < 1 || seatNumber > seats.size() || seats.get(seatNumber - 1).isBooked()) {
                    System.out.println("Seat " + seatNumber + " already booked or invalid.");
                    return false;
                }
            }

            for (int seatNumber : seatNumbers) {
                seats.get(seatNumber - 1).book();
            }

            System.out.println(user.getName() + " booked seats: " + seatNumbers + " for movie: " + show.getMovieName());
            return true;
        }
    }
}

