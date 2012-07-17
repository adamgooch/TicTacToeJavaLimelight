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
public class TicTacToe {
    private TicTacToeBoard gameBoard;
    private TicTacToeAi ai;

    public TicTacToe() {
        gameBoard = new TicTacToeBoard();
        ai = new TicTacToeAi(gameBoard);
    }

    public void play() {
        for(int i = 0; i < 1; i++) {
            System.out.print(gameBoard.toString());
            System.out.print("What is your move? ");
            int desiredSquare = getPlayerMove();
            while(!gameBoard.putMarkInSquare('X', desiredSquare)){
                System.out.print("Invalid Move: Please enter a valid move ");
                desiredSquare = getPlayerMove();
            }
            System.out.print(gameBoard.toString());
            ai.move();
            System.out.print(gameBoard.toString());
        }
    }

    private int getPlayerMove() {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader inputReader = new BufferedReader(is);
        String desiredSquare = " ";
        try {
            desiredSquare = inputReader.readLine();
        } catch (IOException e) {
            // Should never happen
        }
        return Integer.parseInt(desiredSquare);
    }
}
