package gooch.tictactoe;

import java.util.ArrayList;

public interface IO {
    public static final String THINKING = "Thinking \n";
    public static final String PLAYER_ONE_WINS = "X WINS!\n";
    public static final String PLAYER_TWO_WINS = "O WINS!\n";
    public static final String NOBODY_WINS = "Nobody Wins.\n";

    public void displayBoard();
    public void displayMessage(String message);
    public void getPlayerMove(char playerMark);
    public PlayType getPlayType();
    public void addActionListener(Object object);
    public void doEndOfGameTasks(String message, int[] winningSquares);
}
