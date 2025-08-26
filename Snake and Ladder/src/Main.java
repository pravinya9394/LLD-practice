import model.Ladder;
import model.Player;
import model.Snake;
import repository.GameRepository;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Define some snakes
        List<Snake> snakes = Arrays.asList(
                new Snake(16, 6),
                new Snake(47, 26),
                new Snake(49, 11),
                new Snake(56, 53),
                new Snake(62, 19),
                new Snake(87, 24),
                new Snake(93, 73),
                new Snake(95, 75),
                new Snake(98, 78)
        );

        // Define some ladders
        List<Ladder> ladders = Arrays.asList(
                new Ladder(1, 38),
                new Ladder(4, 14),
                new Ladder(9, 31),
                new Ladder(21, 42),
                new Ladder(28, 84),
                new Ladder(36, 44),
                new Ladder(51, 67),
                new Ladder(71, 91),
                new Ladder(80, 100)
        );

        // Define players
        List<Player> players = Arrays.asList(
                new Player("Player 1"),
                new Player("Player 2")
        );

        // Create a game board and start the game
        GameRepository gameBoard = new GameRepository(snakes, ladders, players);
        gameBoard.playGame();
    }
}

//-- Players table
//        CREATE TABLE player (
//        id SERIAL PRIMARY KEY,
//        name VARCHAR(50) NOT NULL
//);
//
//        -- Board table
//        CREATE TABLE board (
//        id SERIAL PRIMARY KEY,
//        size INT NOT NULL -- usually 100
//);
//
//-- Snakes (belongs to a board)
//        CREATE TABLE snake (
//        id SERIAL PRIMARY KEY,
//        board_id INT NOT NULL REFERENCES board(id) ON DELETE CASCADE,
//        head INT NOT NULL,
//        tail INT NOT NULL,
//        CHECK (tail < head) -- rule: snake goes down
//);
//
//        -- Ladders (belongs to a board)
//        CREATE TABLE ladder (
//        id SERIAL PRIMARY KEY,
//        board_id INT NOT NULL REFERENCES board(id) ON DELETE CASCADE,
//        start INT NOT NULL,
//        end INT NOT NULL,
//        CHECK (end > start) -- rule: ladder goes up
//);
//
//        -- Game session (tracks which board is being played)
//        CREATE TABLE game (
//        id SERIAL PRIMARY KEY,
//        board_id INT NOT NULL REFERENCES board(id) ON DELETE CASCADE,
//        status VARCHAR(20) DEFAULT 'IN_PROGRESS', -- e.g. IN_PROGRESS, FINISHED
//        winner_id INT REFERENCES player(id) -- nullable until someone wins
//);
//
//        -- Mapping players to a game (many-to-many)
//        CREATE TABLE game_player (
//        game_id INT NOT NULL REFERENCES game(id) ON DELETE CASCADE,
//        player_id INT NOT NULL REFERENCES player(id) ON DELETE CASCADE,
//        position INT DEFAULT 0, -- track current position
//        PRIMARY KEY (game_id, player_id)
//);
