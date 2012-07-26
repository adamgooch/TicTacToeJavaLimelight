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
        int square = findBestMove(board, playerMark);
        board.putMarkInSquare(playerMark, square);
    }

    public int findBestMove(Board board, char mark) {
        ArrayList<Integer> positions = board.getAvailableSquares();
        int[][] scores = new int[positions.size()][2];
        int bestScore;
        for(int i = 0; i < positions.size(); i++){
            int position = positions.get(i);
            Board child = new Board(board, position, mark);
            int currentScore;
            if(mark == 'O') {
                currentScore = min(child);
            } else {
                currentScore = max(child);
            }
            scores[i][POSITION] = position;
            scores[i][SCORE] = currentScore;
            //System.out.println("Child Score for position " +
            //        scores[i][POSITION] + " is : " + scores[i][SCORE]);
        }
        if(mark == 'O')
            return determineBestMoveForO(scores);
        else
            return determineBestMoveForX(scores);
    }

    public int max(Board board){
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(analyzer.thereIsAWinner() && analyzer.winner == 'O')
            return 1;
        else if(analyzer.thereIsAWinner() && analyzer.winner == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MIN_VALUE;
        for(Integer p : positions){
            Board b = new Board(board, p, 'O');
            int move = min(b);
            if(move > best)
                best = move;
        }
        return best;
    }

    public int min(Board board){
        BoardAnalyzer analyzer = new BoardAnalyzer(board);
        if(analyzer.thereIsAWinner() && analyzer.winner == 'O')
            return 1;
        else if(analyzer.thereIsAWinner() && analyzer.winner == 'X')
            return -1;
        else if(board.countSquaresAvailable() == 0)
            return 0;
        ArrayList<Integer> positions = board.getAvailableSquares();
        int best = Integer.MAX_VALUE;
        for(Integer p : positions){
            Board b = new Board(board, p, 'X');
            int move = max(b);
            if(move < best)
                best = move;
        }
        return best;
    }

    public int determineBestMoveForO(int[][] scores) {
        int bestIndex = 0;
        int bestScore = Integer.MIN_VALUE;
        for(int i = 0; i < scores.length; i++){
            if(scores[i][SCORE] > bestScore) {
                bestScore = scores[i][SCORE];
                bestIndex = i;
            }
        }
        return scores[bestIndex][POSITION];
    }

    public int determineBestMoveForX(int[][] scores) {
        int bestIndex = 0;
        int bestScore = Integer.MAX_VALUE;
        for(int i = 0; i < scores.length; i++){
            if(scores[i][SCORE] < bestScore) {
                bestScore = scores[i][SCORE];
                bestIndex = i;
            }
        }
        return scores[bestIndex][POSITION];
    }
}
