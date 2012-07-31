package gooch.tictactoe;

import gooch.tictactoe.*;

import java.io.Console;

/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        ConsoleIO io = new ConsoleIO(gameBoard);
        AI ai = new MiniMaxAI(gameBoard);
        Game game = new Game(ai, io, gameBoard, io.getPlayTypeFromUser(args) );
        game.play();
    }

}