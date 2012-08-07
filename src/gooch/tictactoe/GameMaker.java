package gooch.tictactoe;

public class GameMaker {
    public static Game game;
    public static Board board;

    public static void makeGame (IO io) {
        board = new Board();
        AI ai = new MiniMaxAI();
        PlayType type = io.getPlayType();
        BoardChecker checker = new NineSquareChecker(board);
        game = new Game(ai, io, checker, type);
        game.play();
    }
}
