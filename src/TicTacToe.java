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
    public static int movesMade;
    private TicTacToeBoard gameBoard;
    private TicTacToeAi ai;
    protected BufferedReader inputReader;

    public TicTacToe(TicTacToeBoard gameBoard, TicTacToeAi ai) {
        this.gameBoard = gameBoard;
        this.ai = ai;
        InputStreamReader userInput = new InputStreamReader(System.in);
        inputReader = new BufferedReader(userInput);
        movesMade = 0;
    }

    public void play() {
        System.out.print(gameBoard.printBoard());
        while(!gameOver()){
            getPlayerMove();
            System.out.print(gameBoard.printBoard());
            if(!gameOver()){
                ai.move();
                System.out.print(gameBoard.printBoard());
            }
        }
        System.out.println("GAME OVER\n");
    }

    protected void getPlayerMove(){
        System.out.print("What is your move? ");
            while(!isValidPlayerMove()){
            System.out.print("Invalid Move: Please enter a valid move ");
        };
        movesMade++;
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

    protected boolean gameOver() {
        if(movesMade >= TicTacToeBoard.NUMBER_OF_SQUARES  ||
                gameBoard.winner())
            return true;
        return false;
    }
}
