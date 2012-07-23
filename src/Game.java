/**
 * Author: Adam Gooch
 * Date: 7/16/12
 */
public class Game {
    public static int movesMade;
    private AI ai;
    private IO console;

    public Game(AI ai, IO io) {
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
        console.displayMessage("GAME OVER!\n");
    }

    protected boolean gameOver() {
        if(movesMade >= Board.NUMBER_OF_SQUARES  ||
                Board.winner)
            return true;
        return false;
    }
}
