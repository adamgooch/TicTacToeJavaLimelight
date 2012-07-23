import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Tank
 * Date: 7/22/12
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToeConsoleIO implements TicTacToeIO {
    private TicTacToeBoard gameBoard;
    protected BufferedReader inputReader;

    public TicTacToeConsoleIO (TicTacToeBoard gameBoard) {
        this.gameBoard = gameBoard;
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        inputReader = new BufferedReader(inputStreamReader);
    }

    @Override
    public void displayBoard() {
        System.out.print(gameBoard.asString());
    }

    @Override
    public void displayMessage(String message) {
        System.out.print(message);
    }

    @Override
    public void getPlayerMove() {
        displayMessage("What is your move? ");
        while(!isValidPlayerMove()){
            System.out.print("Invalid Move: Please enter a valid move ");
        };
    }

    protected boolean isValidPlayerMove() {
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
