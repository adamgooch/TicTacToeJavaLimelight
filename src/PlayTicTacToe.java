/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */
public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        IO io = new ConsoleIO(gameBoard);
        AI ai = new MiniMaxAI(gameBoard);
        int playType = Game.PLAYER_VS_AI;
        playType = Integer.parseInt(args[0]);
        System.out.println("play type = " + args[0]);
        Game game = new Game(ai, io, gameBoard, playType);
        game.play();
    }

}
