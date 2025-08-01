package repository;

import model.Ladder;
import model.Player;
import model.Snake;

import java.util.*;

public class GameRepository {
    private Map<Integer, Integer> snakes = new HashMap<>();
    private Map<Integer, Integer> ladders = new HashMap<>();
    private List<Player> players = new ArrayList<>();
    private int boardSize = 100;

    public GameRepository(List<Snake> snakesList, List<Ladder> laddersList, List<Player> playersList) {
        // Convert snakes and ladders to maps for easy lookup
        for (Snake snake : snakesList) {
            snakes.put(snake.getHead(), snake.getTail());
        }
        for (Ladder ladder : laddersList) {
            ladders.put(ladder.getBottom(), ladder.getTop());
        }
        this.players = playersList;
    }

    public void playGame() {
        boolean gameWon = false;
        Random dice = new Random();

        while (!gameWon) {
            for (Player player : players) {
                int diceRoll = dice.nextInt(6) + 1; // Roll the dice (1 to 6)
                int newPosition = player.getPosition() + diceRoll;

                if (newPosition > boardSize) {
                    newPosition = player.getPosition(); // If roll exceeds 100, stay in place
                } else if (snakes.containsKey(newPosition)) {
                    System.out.println(player.getName() + " got bitten by a snake at position " + newPosition);
                    newPosition = snakes.get(newPosition); // Move down to the snake's tail
                } else if (ladders.containsKey(newPosition)) {
                    System.out.println(player.getName() + " climbed a ladder at position " + newPosition);
                    newPosition = ladders.get(newPosition); // Move up to the ladder's top
                }

                player.setPosition(newPosition);
                System.out.println(player.getName() + " is now at position " + newPosition);

                if (newPosition == boardSize) {
                    System.out.println(player.getName() + " has won the game!");
                    gameWon = true;
                    break; // End the game when a player wins
                }
            }
        }
    }
}