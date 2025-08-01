package model;

import java.util.*;

public class Theater {
    private String theaterName;
    private List<Screen> screens;
    private List<Show> shows;

    public Theater(String name) {
        this.theaterName = name;
        this.screens = new ArrayList<>();
        this.shows = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }

    public String getTheaterName() {
        return theaterName;
    }
}
