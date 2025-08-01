package model;

import java.util.*;

public class Show {
    private String showId;
    private String movieName;
    private Screen screen;

    public Show(String showId, String movieName, Screen screen) {
        this.showId = showId;
        this.movieName = movieName;
        this.screen = screen;
    }

    public String getShowId() {
        return showId;
    }

    public Screen getScreen() {
        return screen;
    }

    public String getMovieName() {
        return movieName;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> available = new ArrayList<>();
        for (Seat seat : screen.getSeats()) {
            if (!seat.isBooked()) {
                available.add(seat);
            }
        }
        return available;
    }
}

