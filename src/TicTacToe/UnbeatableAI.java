package TicTacToe;

/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */

public class UnbeatableAI implements AI {
    private Board gameBoard;
    private int openSquare;


    public UnbeatableAI(Board gameBoard){
        this.gameBoard = gameBoard;
    }

    public void move() {
        if(Game.movesMade <= 1) {
            makeFirstMove();
        } else if(!possibleWin() && !possibleThreat() && !createThreat()) {
            takeFirstAvailableSquare();
        }
        Game.movesMade++;
    }

    protected boolean possibleWin() {
        for(int i = 0; i < Board.BOARD_DIMENSION; i++) {
            if(canWinInRow('O', i))
                return gameBoard.putMarkInSquare('O', openSquare);
            if(canWinInColumn('O', i))
                return gameBoard.putMarkInSquare('O', openSquare);
        }
        if(canWinInDiagonal('O'))
            return gameBoard.putMarkInSquare('O', openSquare);
        return false;
    }

    private boolean canWinInRow(char mark, int row) {
        int count = countMarks(mark, row, "row");
        return canWin(mark, count);
    }

    private boolean canWinInColumn(char mark, int column) {
        int count = countMarks(mark, column, "column");
        return canWin(mark, count);
    }

    private boolean canWin(char mark, int count){
        if(mark == 'O' && count == 2)
            return true;
        else if(mark == 'X' && count == -2)
            return true;
        return false;
    }

    private int countMarks(char mark, int constant, String constantIdentifier){
        int count = 0;
        for(int i = 0; i < Board.BOARD_DIMENSION; i++) {
            int row = constantIdentifier.equals("row") ? constant : i;
            int column = constantIdentifier.equals("column") ? constant : i;
            int square = getSquare(row, column);
            count = count + incOrDecCount(square);
        }
        return count;
    }

    private boolean canWinInDiagonal(char mark) {
        int count = 0;
        for(int i = 0; i < 9; i += 4) {
            count = count + incOrDecCount(i);
        }
        if(mark == 'O' && count == 2) {
            return true;
        } else if(mark == 'X' && count == -2){
            return true;
        }
        count = 0;
        for(int i = 2; i < 8; i += 2) {
            count = count + incOrDecCount(i);
        }
        if(mark == 'O' && count == 2) {
            return true;
        } else if(mark == 'X' && count == -2){
            return true;
        }
        return false;
    }

    private int incOrDecCount(int square) {
        if(gameBoard.getMarkInSquare(square) == 'O') {
            return 1;
        } else if(gameBoard.getMarkInSquare(square) == 'X') {
            return -1;
        } else {
            openSquare = square;
            return 0;
        }
    }

    protected boolean possibleThreat() {
        for(int i = 0; i < Board.BOARD_DIMENSION; i++) {
            if(canWinInRow('X', i))
                return gameBoard.putMarkInSquare('O', openSquare);
            if(canWinInColumn('X', i))
                return gameBoard.putMarkInSquare('O', openSquare);
        }
        if(canWinInDiagonal('X'))
            return gameBoard.putMarkInSquare('O', openSquare);
        return false;
    }

    private int getSquare(int row, int column) {
        return row * Board.BOARD_DIMENSION + column;
    }

    private boolean createThreat() {
        if( gameBoard.getMarkInSquare(4) == 'O' &&
                gameBoard.getMarkInSquare(1) != 'O'){
            gameBoard.putMarkInSquare('O', 1);
            return true;
        }
        return false;
    }

    private void makeFirstMove() {
        if(gameBoard.getMarkInSquare(0) == 'X' ||
           gameBoard.getMarkInSquare(2) == 'X' ||
           gameBoard.getMarkInSquare(6) == 'X' ||
           gameBoard.getMarkInSquare(8) == 'X') {
            gameBoard.putMarkInSquare('O', 4);
        } else if(gameBoard.getMarkInSquare(7) == 'X' ||
                gameBoard.getMarkInSquare(5) == 'X') {
            gameBoard.putMarkInSquare('O', 8);
        } else {
            takeFirstAvailableSquare();
        }
    }

    private void takeFirstAvailableSquare() {
        for(int i = 0; i < Board.NUMBER_OF_SQUARES; i++) {
            if(gameBoard.putMarkInSquare('O', i)){
                break;
            }
        }
    }

}
