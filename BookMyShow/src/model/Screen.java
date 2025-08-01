package model;

import java.util.*;

public class Screen {
    private String screenId;
    private List<Seat> seats;

    public Screen(String screenId, int seatCount) {
        this.screenId = screenId;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            seats.add(new Seat(i));
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getScreenId() {
        return screenId;
    }
}
