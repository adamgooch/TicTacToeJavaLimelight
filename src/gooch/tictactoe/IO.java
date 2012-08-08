package gooch.tictactoe;

import java.util.ArrayList;

public interface IO {
    public void displayBoard();
    public void displayMessage(String message);
    public void getPlayerMove(char playerMark);
    public PlayType getPlayType();
    public void highlightWin(ArrayList winningSquares);
    public void playAudioMessage(String winner);
    public void addActionListener(Object object);
}
