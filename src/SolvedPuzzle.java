import java.util.ArrayList;
import java.util.List;

public class SolvedPuzzle {

    private Cell[][] puzzle;

    public SolvedPuzzle(Cell[][] puzzle){
        this.puzzle = puzzle;
    }

    public List<List<Character>> getSolvedPuzzle(){

        List<List<Character>> solvedPuzzle = new ArrayList<>();
        for(int i = 0; i < puzzle.length; i++){

            List<Character> cols = new ArrayList<>();
            for(int j = 0; j < puzzle.length; j++){

                Cell currentCell = puzzle[i][j];
                cols.add((char) currentCell.getCandidates().toArray()[0]);
            }

            solvedPuzzle.add(cols);
        }

        return solvedPuzzle;
    }
}
