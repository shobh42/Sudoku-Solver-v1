import java.util.List;

public class SudokuSolver {

    private int size;
    private Cell[][] sudokuPuzzle;
    private List<SolvingStrategy> solvingStrategies;

    public SudokuSolver(int size, Cell[][] sudokuPuzzle){
        this.size = size;
        this.sudokuPuzzle = sudokuPuzzle;
    }

    public List<List<Integer>> solve(){

        while(new RowEliminationStrategy().solve(size, sudokuPuzzle)){

        }

        return null;

    }

}
