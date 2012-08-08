package gooch.tictactoe;

public class Game {
    public static final String PLAYER_ONE_WINS = "X WINS!\n";
    public static final String PLAYER_TWO_WINS = "O WINS!\n";
    public static final String NOBODY_WINS = "Nobody Wins.\n";
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';

    protected BoardChecker checker;
    private IO io;
    private GameRunner runner;

    public Game(IO io, Board board) {
        this.io = io;
        MiniMaxAI ai = new MiniMaxAI(board);
        PlayType type = io.getPlayType();
        this.runner = new GameRunner(ai, io, this, type);
        io.addActionListener(runner);
        ai.addActionListener(runner);
        checker = new NineSquareChecker(board);
    }

    public void begin() {
        runner.start();
    }

    public boolean gameOver() {
        if(checker.thereIsAWinner() || checker.boardIsFull())
            return true;
        return false;
    }

    private String getWinnerMessage() {
        if(checker.getWinner() == PLAYER_ONE) {
            return PLAYER_ONE_WINS;
        } else if(checker.getWinner() == PLAYER_TWO) {
            return PLAYER_TWO_WINS;
        } else {
            return NOBODY_WINS;
        }
    }

    public void end() {
        io.displayMessage(getWinnerMessage());
        io.highlightWin(checker.getWinningSquares());
        io.playAudioMessage(getWinnerMessage());
    }
}
