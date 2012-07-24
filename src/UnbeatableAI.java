/**
 * Author: Adam Gooch
 * Date: 7/17/12
 */

public class UnbeatableAI implements AI {
    private Board gameBoard;
    private BoardAnalyzer analyzer;
    private int openSquare;


    public UnbeatableAI(Board gameBoard){
        this.gameBoard = gameBoard;
        analyzer = new BoardAnalyzer(gameBoard);
    }

    public void move() {
        if(Board.squaresTaken <= 1) {
            makeFirstMove();
        } else if(analyzer.numberOfPossibleWins('O') > 0) {
            gameBoard.putMarkInSquare('O', analyzer.squareMarkCanWinIn('O'));
        } else if(analyzer.numberOfPossibleWins('X') > 0) {
            gameBoard.putMarkInSquare('O', analyzer.squareMarkCanWinIn('X'));
        } else {
            makeSmartMove();
        }
    }

    private void makeFirstMove() {
        if(xStartsInRightOrBottomMiddle()) {
            gameBoard.putMarkInSquare('O', 8);
        } else if(gameBoard.getMarkInSquare(4) != 'X'){
            gameBoard.putMarkInSquare('O', 4);
        } else {
            makeSmartMove();
        }
    }

    private boolean xStartsInRightOrBottomMiddle() {
        if(gameBoard.getMarkInSquare(7) == 'X' ||
                gameBoard.getMarkInSquare(5) == 'X')
            return true;
        return false;
    }

    protected void makeSmartMove() {
        for(int square = 0; square < Board.NUMBER_OF_SQUARES; square++) {
            if(gameBoard.putMarkInSquare('O', square)) {
                if(analyzer.numberOfPossibleWins('O') > 0 &&
                        playerBlockDoesNotForceLoss()) {
                    break;
                } else if(nextPlayerMoveForcesLoss()) {
                    gameBoard.removeMarkInSquare(square);
                } else {
                    break;
                }
            }
        }
    }

    protected boolean nextPlayerMoveForcesLoss() {
        for(int square = 0; square < 9; square++) {
            if(gameBoard.putMarkInSquare('X', square)) {
                if(analyzer.numberOfPossibleWins('X') > 1) {
                    gameBoard.removeMarkInSquare(square);
                    return true;
                }
                gameBoard.removeMarkInSquare(square);
            }
        }
        return false;
    }

    protected boolean playerBlockDoesNotForceLoss() {
        if(analyzer.numberOfPossibleWins('O') > 0){
            int square = analyzer.squareMarkCanWinIn('O');
            gameBoard.putMarkInSquare('X', square);
            if(analyzer.numberOfPossibleWins('X') > 1) {
                gameBoard.removeMarkInSquare(square);
                return false;
            }
            gameBoard.removeMarkInSquare(square);
        }
        return true;
    }

}
