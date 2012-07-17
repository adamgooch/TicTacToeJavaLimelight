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
    protected InputStreamReader userInputReader;

    public TicTacToe(TicTacToeBoard gameBoard, TicTacToeAi ai) {
        //gameBoard = new TicTacToeBoard();
        this.gameBoard = gameBoard;
        this.ai = ai;
        userInputReader = new InputStreamReader(System.in);
    }

    public void play() {
        //for(int i = 0; i < 1; i++) {
            System.out.print(gameBoard.printBoard());
            System.out.print("What is your move? ");
            while(!getValidPlayerMove()){
                System.out.print("Invalid Move: Please enter a valid move ");
            };
            System.out.print(gameBoard.printBoard());
            ai.move();
            System.out.print(gameBoard.printBoard());
        //}
    }

    protected boolean getValidPlayerMove() {
        BufferedReader inputReader = new BufferedReader(userInputReader);
        String userInput = "9";
        int desiredSquare = 9;
        try {
            userInput = inputReader.readLine();
            System.out.println("input = " + userInput);
            desiredSquare = Integer.parseInt(userInput);
        } catch (IOException e) {
            // Something wrong with the system
        } catch (NumberFormatException e) {
            // bad user input
        }
        return gameBoard.putMarkInSquare('X', desiredSquare);
    }
}
