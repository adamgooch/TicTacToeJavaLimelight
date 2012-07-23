import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/16/12
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeGame {
    public static int movesMade;
    private TicTacToeAI ai;
    private TicTacToeIO console;

    public TicTacToeGame(TicTacToeAI ai, TicTacToeIO io) {
        this.ai = ai;
        this.console = io;
        movesMade = 0;
    }

    public void play() {
        console.displayBoard();
        while(!gameOver()) {
            console.getPlayerMove();
            movesMade++;
            console.displayBoard();
            if(!gameOver()){
                ai.move();
                console.displayBoard();
            }
        }
        console.displayMessage("GAME OVER!");
    }

    protected boolean gameOver() {
        if(movesMade >= TicTacToeBoard.NUMBER_OF_SQUARES  ||
                TicTacToeBoard.winner)
            return true;
        return false;
    }
}
