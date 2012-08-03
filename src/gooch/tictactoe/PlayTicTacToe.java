package gooch.tictactoe;

public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        ConsoleIO io = new ConsoleIO(gameBoard);
        AI ai = new MiniMaxAI(gameBoard);
        Game game = new Game(ai, io, gameBoard, io.getPlayTypeFromUser(args));
        game.play();
    }

}
