package gooch.tictactoe;

public class Game {
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    private static final String PLAYER_ONE_WINS = "X WINS!";
    private static final String PLAYER_TWO_WINS = "O WINS!";
    private static final String NOBODY_WINS = "Nobody Wins.";

    private AI ai;
    private IO io;
    private BoardChecker checker;
    public int movesMade;
    private PlayType gameType;
    private boolean playerOnesTurn;

    public Game(AI ai, IO io, BoardChecker checker, PlayType playType) {
        this.ai = ai;
        this.io = io;
        this.checker = checker;
        this.gameType = playType;
        movesMade = 0;
        playerOnesTurn = true;
    }

    public void play() {
        io.displayBoard();
        while(!gameOver()) {
            playerMove();
            playerOnesTurn = !playerOnesTurn;
            io.displayBoard();
            movesMade++;
        }
        io.displayMessage(getWinnerMessage() + "\n");
    }

    public boolean gameOver() {
        if(checker.thereIsAWinner() || movesMade >= Board.NUMBER_OF_SQUARES)
            return true;
        return false;
    }

    public String getWinnerMessage() {
        if(checker.getWinner() == PLAYER_ONE) {
            return PLAYER_ONE_WINS;
        } else if(checker.getWinner() == PLAYER_TWO) {
            return PLAYER_TWO_WINS;
        } else {
            return NOBODY_WINS;
        }
    }

    private void playerMove() {
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
