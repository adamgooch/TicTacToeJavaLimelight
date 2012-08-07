package gooch.tictactoe;

import java.util.ArrayList;

public interface BoardChecker {
    public boolean thereIsAWinner();
    public char getWinner();
    public ArrayList<Integer> getWinningSquares();
    public boolean boardIsFull();
}
