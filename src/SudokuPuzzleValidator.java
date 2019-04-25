import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPuzzleValidator {

    private List<Set<Integer>> rows;
    private List<Set<Integer>> cols;
    private List<Set<Integer>> blocks;
    private Set<Integer> validCandidates;
    private Cell[][] sudokuPuzzle;
    private int size;

    public SudokuPuzzleValidator(Set<Integer> validCandidates, Cell[][] sudokuPuzzle, int size){
        this.validCandidates = validCandidates;
        this.sudokuPuzzle = sudokuPuzzle;
        this.size = size;
        initialize();
    }

    private void initialize() {
        rows = new ArrayList<>(size);
        cols = new ArrayList<>(size);
        blocks = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            rows.add(new HashSet<>());
            cols.add(new HashSet<>());
            blocks.add(new HashSet<>());
        }
    }


    public boolean isValid(){
        for(int i = 0; i < size; i++){

            for(int j = 0; j < size; j++){

                if(sudokuPuzzle[i][j].getSize() == 1){
                    int candidate = (int)sudokuPuzzle[i][j].getCandidates().toArray()[0];

                    if(checkCandidatePresentInRow(i, candidate) || checkCandidatePresentInColumn(j, candidate)
                    || checkCandidatePresentInBlock(i, j, candidate) || checkCandidateIsValid(candidate)){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean checkCandidateIsValid(int candidate) {
        return !validCandidates.contains(candidate);
    }

    private boolean checkCandidatePresentInBlock(int i, int j, int candidate) {
        int blockIndex = (i / (int)Math.sqrt(size)) * (int)Math.sqrt(size) + (j / (int)Math.sqrt(size));
        if(blocks.get(blockIndex).contains(candidate)){
            return true;
        }

        blocks.get(blockIndex).add(candidate);
        return false;
    }

    private boolean checkCandidatePresentInColumn(int j, int candidate) {
        if(cols.get(j).contains(candidate)){
            return false;
        }

        cols.get(j).add(candidate);
        return false;
    }

    private boolean checkCandidatePresentInRow(int i, int candidate) {
        if(rows.get(i).contains(candidate)){
            return true;
        }

        rows.get(i).add(candidate);
        return false;
    }
}
