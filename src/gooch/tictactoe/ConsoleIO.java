package gooch.tictactoe;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleIO implements IO {

    private static final String OPENING_MESSAGE = "Welcome To Tic Tac Toe\n" +
            "How would you like to play?\n" +
            "0: Player vs. Player\n" +
            "1: Player vs. Computer\n" +
            "2: Computer vs. Computer\n" +
            "Please enter a number: ";
    private static final String ASK_FOR_MOVE = "What is your move? ";
    private static final String INVALID_MOVE = "Invalid Move: Please enter a valid move ";
    private static final int PLAYER_VS_PLAYER = 0;
    private static final int PLAYER_VS_AI = 1;
    private static final int AI_VS_AI = 2;
    private static final int INVALID_SQUARE = 9;

    protected BufferedReader inputReader;

    public ConsoleIO() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        inputReader = new BufferedReader(inputStreamReader);
    }

    @Override
    public void displayBoard() {
        System.out.print(GameMaker.board.asString());
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
        }
        GameMaker.game.moveGameForward();
    }

    protected boolean isValidPlayerMove(char playerMark) {
        String userInput = getUserInput();
        int desiredSquare = INVALID_SQUARE;
        try {
            desiredSquare = Math.abs(Integer.parseInt(userInput));
        } catch (NumberFormatException e) {
            // bad user input doesn't matter, gameBoard doesn't care
        }
        return GameMaker.board.putMarkInSquare(playerMark, desiredSquare);
    }

    public PlayType getPlayType() {
        displayMessage(OPENING_MESSAGE);
        int userInput = Integer.parseInt(getUserInput());
        PlayType playType = associatePlayType(userInput);
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

    public PlayType associatePlayType(int num) {
        switch(num) {
            case AI_VS_AI:
                return PlayType.AI_VS_AI;
            case PLAYER_VS_PLAYER:
                return PlayType.PLAYER_VS_PLAYER;
            default:
                return PlayType.PLAYER_VS_AI;
        }
    }

    @Override
    public void highlightWin(ArrayList winningSquares) {

    }

    @Override
    public void playAudioMessage(String winner) {

    }

}
