import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    private Cell[][] sudokuPuzzle;
    private List<SolvingStrategy> solvingStrategies;
    private SudokuPuzzleGenerator puzzleGenerator;
    List<SolvedPuzzle> solvedPuzzle;

    public SudokuSolver(){
        this.puzzleGenerator = new SudokuPuzzleGenerator();
        solvedPuzzle = new ArrayList<>();
        initializeStrategy();
    }

    private void initializeStrategy() {
        solvingStrategies = Arrays.asList(new RowEliminationStrategy(), new ColumnEliminationStrategy());
    }

    public List<SolvedPuzzle> solve(String path) throws InvalidPuzzleException, IOException, IllegalCharacterException {
        Cell[][] sudokuPuzzle = puzzleGenerator.generatePuzzle(path);
        int size = sudokuPuzzle.length;
        int strategyNumber = 0;
        while(strategyNumber < solvingStrategies.size()){
            if(!solvingStrategies.get(strategyNumber).solve(size, sudokuPuzzle)){
                strategyNumber++;
            }

            boolean isPuzzleSolved = isPuzzleSolved();
            if(isPuzzleSolved){
                solvedPuzzle.add(new SolvedPuzzle(sudokuPuzzle));
                break;
            }
        }

        return solvedPuzzle;

    }

    private boolean isPuzzleSolved() {

        for(int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){

                Cell currentCell =sudokuPuzzle[i][j];
                if(currentCell.getSize() != 1){
                    return false;
                }

            }
        }

        return true;
    }

}
