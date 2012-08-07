package gooch.tictactoe;

public class Game implements InputListener {
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static final String PLAYER_ONE_WINS = "X WINS!\n";
    public static final String PLAYER_TWO_WINS = "O WINS!\n";
    public static final String NOBODY_WINS = "Nobody Wins.\n";

    private AI ai;
    private IO io;
    private BoardChecker checker;
    private PlayType gameType;
    private boolean playerOnesTurn;

    public Game(AI ai, IO io, BoardChecker checker, PlayType playType) {
        this.ai = ai;
        this.io = io;
        this.gameType = playType;
        this.checker = checker;
        playerOnesTurn = true;
    }

    public void play() {
        io.displayBoard();
        getPlayerMove();
    }

    public void inputReceived() {
        io.displayBoard();
        playerOnesTurn = !playerOnesTurn;
        if(gameOver()) {
            io.displayMessage(getWinnerMessage());
            io.highlightWin(checker.getWinningSquares());
            io.playAudioMessage(getWinnerMessage());
        } else {
            getPlayerMove();
        }
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

    private void getPlayerMove() {
        if(playerOnesTurn)
            playerOneMove();
        else
            playerTwoMove();
    }

    private void playerOneMove() {
        if(gameType != PlayType.AI_VS_AI)
            io.getPlayerMove(PLAYER_ONE); // human player always goes first
        else
            ai.move(PLAYER_ONE);
    }

    private void playerTwoMove() {
        if(gameType != PlayType.PLAYER_VS_PLAYER)
            ai.move(PLAYER_TWO);
        else
            io.getPlayerMove(PLAYER_TWO);
    }
}
