package gooch.tictactoe;

public class PlayTicTacToe {

    public static void main (String[] args) {
        Board gameBoard = new Board();
        ConsoleIO io = new ConsoleIO(gameBoard);
        AI ai = new MiniMaxAI(gameBoard);
        BoardChecker checker = new NineSquareChecker(gameBoard);
        PlayType playType;
        if (args.length > 0) {
            playType = io.associatePlayType(Integer.parseInt(args[0]));
        } else {
            playType = io.getPlayTypeFromUser();
        }
        Game game = new Game(ai, io, checker, playType);
        game.play();
    }

}
