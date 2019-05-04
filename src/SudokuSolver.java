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
        solvingStrategies = Arrays.asList(new RowEliminationStrategy(), new ColumnEliminationStrategy(),
                new BlockEliminationStrategy(), new HiddenSingleStrategy(), new PointingPairStrategy(),
                new BoxReductionStrategy());
    }


    public List<SolvedPuzzle> solve(String path) throws InvalidPuzzleException, IOException, IllegalCharacterException {
        sudokuPuzzle = puzzleGenerator.generatePuzzle(path);
        int size = sudokuPuzzle.length;
        int strategyNumber = 0;
        boolean puzzleIsSolved = false;

        while(!puzzleIsSolved){
            if(solvingStrategies.get(strategyNumber).solve(size, sudokuPuzzle)){
                strategyNumber = 0;
            }else{
                strategyNumber++;
            }

            printPuzzle();
            puzzleIsSolved = isPuzzleSolved();
        }

        return solvedPuzzle;

    }

    private boolean isPuzzleSolved() {

        for(int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){

                Cell currentCell =sudokuPuzzle[i][j];
                //System.out.print(currentCell.getCandidates() + " ");
                if(currentCell.getSize() != 1){
                    return false;
                }

            }

            //System.out.println();
        }

        return true;
    }

    private void printPuzzle(){
        for (int i = 0; i < sudokuPuzzle.length; i++){

            for(int j = 0; j < sudokuPuzzle.length; j++){
                System.out.print(sudokuPuzzle[i][j].getCandidates()+ " ");
            }

            System.out.println();
        }

        System.out.println("-----------------------------------------");
    }

}
