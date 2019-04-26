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
        solvingStrategies = Arrays.asList(new RowEliminationStrategy());
        //new BlockEliminationStrategy()
    }


    public List<SolvedPuzzle> solve(String path) throws InvalidPuzzleException, IOException, IllegalCharacterException {
        sudokuPuzzle = puzzleGenerator.generatePuzzle(path);
        int size = sudokuPuzzle.length;
        int strategyNumber = 0;
        while(strategyNumber < solvingStrategies.size()){
            if(!solvingStrategies.get(strategyNumber).solve(size, sudokuPuzzle)){
                strategyNumber++;
            }

            printPuzzle();
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
                System.out.print(currentCell.getCandidates() + " ");
//                if(currentCell.getSize() != 1){
//                    return false;
//                }

            }

            System.out.println();
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
