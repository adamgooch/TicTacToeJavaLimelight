import java.util.ArrayList;

/**
 * Author: Adam Gooch
 * Date: 7/25/12
 */
public class MiniMaxAI implements AI {
    private static final int POSITION = 0;
    private static final int SCORE = 1;
    private Board board;

    public MiniMaxAI(Board board) {
        this.board = board;
    }

    @Override
    public void move(char playerMark) {
        int square = findBestMove(playerMark);
        board.putMarkInSquare(playerMark, square);
    }

    private int findBestMove(char mark) {
        ArrayList<Integer> positions = board.getAvailableSquares();
        int[][] scores = new int[positions.size()][2];
        for(int i = 0; i < positions.size(); i++){
            int position = positions.get(i);
            Board child = board.clone(new Board());
            child.putMarkInSquare(mark, position);
            int currentScore;
            if(mark == 'O') {
                currentScore = min(child);
            } else {
                currentScore = max(child);
            }
            scores[i][POSITION] = position;
            scores[i][SCORE] = currentScore;
        }
        return determineBestMove(scores, mark);
    }

    private int max(Board board){
        char mark = 'O';
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(board.countSquaresAvailable() == 0 && analyzer.getWinner() == 'O')
            return 1;
        else if(board.countSquaresAvailable() == 0 && analyzer.getWinner() == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MIN_VALUE;
        for(Integer position : positions){
            Board child = board.clone(new Board());
            child.putMarkInSquare(mark, position);
            int move = min(child);
            if(move > best)
                best = move;
        }
        return best;
    }

    private int min(Board board){
        char mark = 'X';
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(analyzer.thereIsAWinner() && analyzer.getWinner() == 'O')
            return 1;
        else if(analyzer.thereIsAWinner() && analyzer.getWinner() == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MAX_VALUE;
        for(Integer position : positions){
            Board child = board.clone(new Board());
            child.putMarkInSquare(mark, position);
            int move = max(child);
            if(move < best)
                best = move;
        }
        return best;
    }

    private int determineBestMove(int[][] scores, char mark) {
        int bestIndex = 0;
        int bestScore = scores[bestIndex][SCORE];
        for(int i = 0; i < scores.length; i++){
            if((mark == 'O' && scores[i][SCORE] > bestScore) ||
                    (mark == 'X' && scores[i][SCORE] < bestScore)) {
                bestScore = scores[i][SCORE];
                bestIndex = i;
            }
        }
        return scores[bestIndex][POSITION];
    }

}
