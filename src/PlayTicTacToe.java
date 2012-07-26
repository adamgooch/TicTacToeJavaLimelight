/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        IO io = new ConsoleIO(gameBoard);
        AI ai = new MiniMaxAI(gameBoard);
        Game game = new Game(ai, io, gameBoard, Game.PLAYER_VS_AI);
        game.play();
    }

}
