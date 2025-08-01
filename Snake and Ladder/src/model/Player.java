package model;

public class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0; // All players start at position 0
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int newPosition) {
        this.position = newPosition;
    }
}

