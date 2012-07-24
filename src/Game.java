/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Game {
    private AI ai;
    private IO console;

    public Game(AI ai, IO io) {
        this.ai = ai;
        this.console = io;
    }

    public void play() {
        console.displayBoard();
        while(!gameOver()) {
            console.getPlayerMove();
            console.displayBoard();
            if(!gameOver()){
                ai.move();
                console.displayBoard();
            }
        }
        console.displayMessage("GAME OVER!\n");
        console.displayMessage(getWinnerMessage());
    }

    protected boolean gameOver() {
        if(BoardAnalyzer.thereIsAWinner() ||
                Board.squaresTaken >= Board.NUMBER_OF_SQUARES)
            return true;
        return false;
    }

    protected String getWinnerMessage() {
        if(BoardAnalyzer.winner == 'X') {
            return "YOU WIN!\n";
        } else if(BoardAnalyzer.winner == 'O') {
            return "I WIN!\n";
        } else {
            return "Nobody wins.\n";
        }
    }
}
