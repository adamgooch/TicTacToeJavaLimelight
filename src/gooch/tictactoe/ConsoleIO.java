package gooch.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements IO {

    private static final String OPENING_MESSAGE = "Welcome To Tic Tac Toe\n" +
            "How would you like to play?\n" +
            "0: Player vs. Player\n" +
            "1: Player vs. Computer\n" +
            "2: Computer vs. Computer\n" +
            "Please enter a number: ";
    private static final String ASK_FOR_MOVE = "What is your move? ";
    private static final String INVALID_MOVE = "Invalid Move: Please enter a valid move ";

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
        displayMessage(ASK_FOR_MOVE);
        while(!isValidPlayerMove(playerMark)){
            displayMessage(INVALID_MOVE);
        };
    }

    protected boolean isValidPlayerMove(char playerMark) {
        String userInput = getUserInput();
        int desiredSquare = Board.NUMBER_OF_SQUARES;
        try {
            desiredSquare = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            // bad user input doesn't matter, gameBoard doesn't care
        }
        return gameBoard.putMarkInSquare(playerMark, desiredSquare);
    }

    public int getPlayTypeFromUser(String[] args) {
        int playType;
        if(args.length > 0) {
            playType = validatedUserPlayType(Integer.parseInt(args[0]));
        } else {
            displayMessage(OPENING_MESSAGE);
            playType = validatedUserPlayType(Integer.parseInt(getUserInput()));
        }
        return playType;
    }

    private String getUserInput() {
        String userInput = "";
        try {
            userInput = inputReader.readLine();
        } catch (IOException e) {
            // Something wrong with the system
        }
        return userInput;
    }

    private int validatedUserPlayType(int userInput) {
        if(userInput >= Game.PLAYER_VS_PLAYER && userInput <= Game.AI_VS_AI)
            return userInput;
        else
            return Game.PLAYER_VS_AI;
    }

}
