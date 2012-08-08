package gooch.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRunner implements ActionListener {
    private AI ai;
    private IO io;
    private PlayType gameType;
    private Game game;
    private boolean playerOnesTurn;

    public GameRunner(AI ai, IO io, Game game, PlayType playType) {
        this.ai = ai;
        this.io = io;
        this.game = game;
        this.gameType = playType;
        playerOnesTurn = true;
    }

    public void start() {
        io.displayBoard();
        getPlayerMove();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        io.displayBoard();
        playerOnesTurn = !playerOnesTurn;
        if(game.gameOver()) {
            game.end();
        } else {
            getPlayerMove();
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
            io.getPlayerMove(Game.PLAYER_ONE); // human player always goes first
        else
            ai.move(Game.PLAYER_ONE);
    }

    private void playerTwoMove() {
        if(gameType != PlayType.PLAYER_VS_PLAYER)
            ai.move(Game.PLAYER_TWO);
        else
            io.getPlayerMove(Game.PLAYER_TWO);
    }

}
