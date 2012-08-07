package gooch.tictactoe;

public class PlayTicTacToe {
    public static void main (String[] args) {
        ConsoleIO io = new ConsoleIO();
        GameMaker gameMaker = new GameMaker(io);
        gameMaker.game.play();
    }

}
