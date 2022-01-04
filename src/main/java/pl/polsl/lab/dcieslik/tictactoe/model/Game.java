package pl.polsl.lab.dcieslik.tictactoe.model;

import static java.lang.Integer.parseInt;

/**
 *
 * @author Dawid
 */
public class Game {

    private static String[] board = new String[9];
    private static Player firstPlayer;
    private static Player secondPlayer;
    private static WinnerChecker winnerChecker;
    private static String winner = "";
    private static String turn = "O";
    private static String error = "";

    public Game(String firstPlayerName, String secondPlayerName) {
        initBoard();
        firstPlayer = new Player(firstPlayerName);
        secondPlayer = new Player(secondPlayerName);
        winnerChecker = new WinnerChecker(board);
    }

    public void move(String slot) {
        if (!slot.equals("O") && !slot.equals("X")) {
            int index = parseInt(slot);
            index--;
            if (!(index >= 0 && index <= 8)) {
                error = "Invalid slot.";
            } else {
                error = "";
                if (turn.equals("O")) {
                    board[index] = "O";
                    setTurn("X");
                } else if (turn.equals("X")) {
                    board[index] = "X";
                    setTurn("O");
                }
                winner = winnerChecker.checkWinner();
            }
        } else {
            error = "Invalid slot.";
        }
    }

    public static String getPlayerName() {
        if (turn.equals("O")) {
            return firstPlayer.getName();
        } else {
            return secondPlayer.getName();
        }
    }

    public static String getWinnerName() {
        if (winner.equals("O")) {
            return firstPlayer.getName();
        } else if (winner.equals("X")) {
            return secondPlayer.getName();
        } else {
            return "";
        }
    }

    public static String[] getBoard() {
        return board;
    }

    public static void setBoard(String[] board) {
        Game.board = board;
    }

    public static Player getFirstPlayer() {
        return firstPlayer;
    }

    public static void setFirstPlayer(Player firstPlayer) {
        Game.firstPlayer = firstPlayer;
    }

    public static Player getSecondPlayer() {
        return secondPlayer;
    }

    public static void setSecondPlayer(Player secondPlayer) {
        Game.secondPlayer = secondPlayer;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        Game.winner = winner;
    }

    public static String getTurn() {
        return turn;
    }

    public static void setTurn(String turn) {
        Game.turn = turn;
    }

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        Game.error = error;
    }

    private static void initBoard() {
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }
}
