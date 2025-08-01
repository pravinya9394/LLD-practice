import model.Player;
import service.TicTacToe;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", 'X');
        Player player2 = new Player("Bob", 'O');
        TicTacToe game = new TicTacToe(player1, player2);
        game.playGame();
    }
}