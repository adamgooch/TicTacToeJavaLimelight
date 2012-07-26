import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Adam Gooch
 * Date: 7/22/12
 */
public class ConsoleIO implements IO {
    private Board gameBoard;
    protected BufferedReader inputReader;

    public ConsoleIO(Board gameBoard) {
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
    public void getPlayerMove(char playerMark) {
        displayMessage("What is your move? ");
        while(!isValidPlayerMove(playerMark)){
            displayMessage("Invalid Move: Please enter a valid move ");
        };
    }

    protected boolean isValidPlayerMove(char playerMark) {
        String userInput;
        int desiredSquare = 10;
        try {
            userInput = inputReader.readLine();
            desiredSquare = Integer.parseInt(userInput);
        } catch (IOException e) {
            // Something wrong with the system
        } catch (NumberFormatException e) {
            // bad user input doesn't matter, gameBoard doesn't care
        }
        return gameBoard.putMarkInSquare(playerMark, desiredSquare);
    }
}
