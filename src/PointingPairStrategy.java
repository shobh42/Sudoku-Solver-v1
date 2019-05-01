import java.util.Set;

/**
 * Created by Shobhit on 11/16/2016.
 */

public class PointingPairStrategy implements SolvingStrategy {
    private boolean stateChanged;


    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++) {

                Set<Character> values = puzzle[row][col].getCandidates();
                for (int i = 0; i < values.size(); i++) {
                    char valueToCheck = (Character) values.toArray()[i];
                    //boolean blockHasValue = checkCandidateIsPresentInCurrentRowAndBlock(row, col, puzzle, valueToCheck);
                    boolean rowHasValue = checkOnlyCurrentRowHasValue(row, col, puzzle, valueToCheck);
                    boolean colHasValue = checkCandidateIsPresentInOnlyCurrentColumnOfCurrentBlock(row, col, puzzle, valueToCheck);

                    if(rowHasValue) {
                        removeTheValuesFromThatRow(row, col, puzzle, valueToCheck);
                    }

                    if(colHasValue) {
                        removeTheValuesFromThatColumn(row, col, puzzle, valueToCheck);
                    }
                }
            }
        }
        return stateChanged;
    }

    private void removeTheValuesFromThatRow(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int col=0 ; col <size;col++){
            if((col/sqrtOfSize) != (c/sqrtOfSize)){
                if(puzzle[r][col].getSize() > 1){
                    Set <Character> possibleValues = puzzle[r][col].getCandidates();
                    if(possibleValues.contains(valueToRemove)){
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                    }
                }
            }
        }
    }

    private void removeTheValuesFromThatColumn(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int row=0 ; row <size;row++){
            if((row/sqrtOfSize) != (r/sqrtOfSize)){

                if(puzzle[row][c].getSize() > 1){
                    Set <Character> possibleValues = puzzle[row][c].getCandidates();
                    if(possibleValues.contains(valueToRemove)){
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                    }
                }
            }
        }
    }

    private boolean checkOnlyCurrentRowHasValue(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int c1 = (c/sqrtOfSize)*sqrtOfSize ;c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize; c1++ ){
            if(c!=c1) {
                Set <Character> possibleValues = puzzle[r][c1].getCandidates();
                if(possibleValues.contains(valueToFind)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCandidateIsPresentInOnlyCurrentColumnOfCurrentBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize ;r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize; r1++ ){

            if(r!=r1 ) {
                Set <Character> possibleValues = puzzle[r1][c].getCandidates();
                if(possibleValues.contains(valueToFind)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCandidateIsPresentInCurrentRowAndBlock(int r , int c, Cell[][]puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize; r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (c/sqrtOfSize)*sqrtOfSize ; c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){
                if(r1 != r){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();

                    if(possibleValues.contains(valueToFind)){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}