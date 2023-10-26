import java.util.Random;
import java.util.Scanner;

public class AdvancedTicTacToe {

    private static char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
    private static char currentPlayer = 'X';
    private static char computerPlayer = 'O';

    public static void main(String[] args) {
        boolean gameWon = false;

        while (true) {
            printBoard();

            if (currentPlayer == 'X') {
                System.out.println("Player X, enter row (0-2) and column (0-2) separated by a space (e.g., 1 1): ");
                getPlayerMove();
            } else {
                System.out.println("Computer's move: ");
                getComputerMove();
            }

            gameWon = checkWinner();
            if (gameWon) {
                printBoard();
                if (currentPlayer == 'X') {
                    System.out.println("Player X wins!");
                } else {
                    System.out.println("Computer wins!");
                }
                break;
            }

            if (boardIsFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2)
                    System.out.print("|");
            }
            System.out.println();
            if (i < 2)
                System.out.println("  -----");
        }
    }

    private static void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
        } else {
            System.out.println("Invalid move. Try again.");
            getPlayerMove();
        }
    }

    private static void getComputerMove() {
        Random random = new Random();
        int row, col;

        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        board[row][col] = computerPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            return true;
        return false;
    }

    private static boolean boardIsFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
}
