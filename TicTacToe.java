import java.util.Scanner;

/**
 * A console-based two-player Tic-Tac-Toe game.
 *
 * <p>Players take turns entering a number from 1-9 to place their mark
 * (X or O) on the board. The first player to get three marks in a row —
 * horizontally, vertically, or diagonally — wins. If the board fills up
 * with no winner, the game ends in a draw. Invalid input is rejected
 * with a prompt to try again.</p>
 *
 * <p>Run with: {@code javac TicTacToe.java && java TicTacToe}</p>
 */
public class TicTacToe {
    private static char[][] board = {
        {'1', '2', '3'},
        {'4', '5', '6'},
        {'7', '8', '9'}
    };
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter a number (1-9): ");
            int move;
            
            if (scanner.hasNextInt()) {
                move = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Enter a number between 1-9.");
                scanner.next();
                continue;
            }

            if (move < 1 || move > 9) {
                System.out.println("Invalid move! Enter a number between 1 and 9.");
                continue;
            }
            if (!isValidMove(move)) {
                System.out.println("Cell " + move + " is already taken. Pick another one.");
                continue;
            }

            makeMove(move);
            if (checkWin()) {
                printBoard();
                System.out.println("🎉 Player " + currentPlayer + " wins! 🎉");
                gameRunning = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw! 🤝");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }
        scanner.close();
    }

    /** Prints the current board state to the console. */
    private static void printBoard() {
        System.out.println("\n " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n");
    }

    /**
     * Returns true if {@code move} is within 1-9 and the target cell is free.
     */
    private static boolean isValidMove(int move) {
        if (move < 1 || move > 9) return false;
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] != 'X' && board[row][col] != 'O';
    }

    /** Places the current player's mark in the cell numbered {@code move}. */
    private static void makeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;
    }

    /** Switches the turn to the other player. */
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /** Returns true if the current player has three marks in a row. */
    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    /** Returns true if every cell on the board holds a player mark. */
    private static boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell != 'X' && cell != 'O') {
                    return false;
                }
            }
        }
        return true;
    }
}
