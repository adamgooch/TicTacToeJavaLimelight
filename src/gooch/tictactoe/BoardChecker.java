package gooch.tictactoe;

import java.util.ArrayList;

/**
 * Author: Adam Gooch
 * Date: 8/6/12
 */
public interface BoardChecker {
    public boolean thereIsAWinner();
    public char getWinner();
}
