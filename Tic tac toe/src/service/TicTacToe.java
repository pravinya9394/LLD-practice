package service;
// TicTacToe.java
import model.Player;

import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private Player player1;
    private Player player2;
    private int movesMade;

    public TicTacToe(Player p1, Player p2) {
        board = new char[3][3];
        initializeBoard();
        this.player1 = p1;
        this.player2 = p2;
        this.movesMade = 0;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("Current board:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(Player player, int row, int col) {
        if (row < 0 || col < 0 || row >= 3 || col >= 3 || board[row][col] != '-') {
            System.out.println("Invalid move. Try again.");
            return false;
        }

        board[row][col] = player.getSymbol();
        movesMade++;
        return true;
    }

    public boolean checkWin(Player player) {
        char sym = player.getSymbol();

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == sym && board[i][1] == sym && board[i][2] == sym) ||
                    (board[0][i] == sym && board[1][i] == sym && board[2][i] == sym)) {
                return true;
            }
        }

        // Diagonals
        if ((board[0][0] == sym && board[1][1] == sym && board[2][2] == sym) ||
                (board[0][2] == sym && board[1][1] == sym && board[2][0] == sym)) {
            return true;
        }

        return false;
    }

    public boolean isDraw() {
        return movesMade == 9;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = player1;

        while (true) {
            printBoard();
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") turn. Enter row and column (0, 1, 2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (makeMove(currentPlayer, row, col)) {
                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                } else if (isDraw()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                // Switch turn
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
        scanner.close();
    }
}
