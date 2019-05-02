import java.util.Set;

/**
 * Box/Line reduction occurs when a candidate only appears in a row or column, and those candidates are also all in the same box.
 * When this happens, you know that the candidate MUST occur in that row or column because it can't appear anywhere else there,
 * so it CAN'T occur elsewhere in that box.
 */
public class BoxReductionStrategy implements SolvingStrategy{

    private boolean stateChanged ;

    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        stateChanged = false;
        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                if(puzzle[row][col].getSize() > 1){
                    Character []values = (Character[]) puzzle[row][col].getCandidates().toArray();
                    for(int i = 0; i < values.length; i++){
                        boolean blockHasValue = checkCandidateIsPresentInBlock(row, col, puzzle, values[i]);
                        boolean otherRowHasValue = checkCandidateIsPresentInOtherRowExcludingCurrentBlock(row, col, puzzle, values[i]);
                        boolean otherColHasValue = checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(row, col, puzzle, values[i]);

                        if(blockHasValue && (!otherRowHasValue || !otherColHasValue)){
                            if(!otherRowHasValue){
                                removeTheValueFromTheBlockExcludingCandidateColumn(row, col, puzzle, values[i]);
                            }else{
                                removeTheValueFromTheBlockExcludingCandidateRow(row, col, puzzle, values[i]);
                            }
                        }
                    }
                }
            }
        }

        return stateChanged;
    }

    private void removeTheValueFromTheBlockExcludingCandidateRow(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidateToRemove) {

        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = (candidateRow/sqrtOfSize)*sqrtOfSize; r1 < ((candidateRow/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (candidateColumn/sqrtOfSize)*sqrtOfSize; c1 < ((candidateColumn/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){

                if(r1 != candidateRow){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();
                    if(possibleValues.contains(candidateToRemove)){
                        stateChanged = true;
                        possibleValues.remove(candidateToRemove);
                    }

                }
            }
        }
    }

    private void removeTheValueFromTheBlockExcludingCandidateColumn(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidateToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = (candidateRow /sqrtOfSize)*sqrtOfSize; r1 < ((candidateRow /sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (candidateColumn/sqrtOfSize)*sqrtOfSize   ; c1 < ((candidateColumn/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){

                if(c1 != candidateColumn ){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();

                    if(possibleValues.contains(candidateToRemove)){
                        stateChanged = true;
                        possibleValues.remove(candidateToRemove);
                    }

                }
            }
        }
    }

    private boolean checkCandidateIsPresentInOtherColumnExcludingCurrentBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for (int c1 = 0; c1 < size; c1++) {
            if (c/sqrtOfSize != c1/sqrtOfSize) {
                Set<Character> possibleValues = puzzle[r][c1].getCandidates();
                if (possibleValues.contains(valueToFind)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCandidateIsPresentInOtherRowExcludingCurrentBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for (int r1 = (r / sqrtOfSize) * sqrtOfSize; r1 < ((r / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; r1++) {
            if (r != r1) {
                Set<Character> possibleValues = puzzle[r1][c].getCandidates();
                if (possibleValues.contains(valueToFind)) {
                    return true;
                }
            }
        }

        return false;
    }


    private boolean checkCandidateIsPresentInBlock(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for (int r1 = (r / sqrtOfSize) * sqrtOfSize; r1 < ((r / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; r1++) {

            for (int c1 = (c / sqrtOfSize) * sqrtOfSize; c1 < ((c / sqrtOfSize) * sqrtOfSize) + sqrtOfSize; c1++) {
                if (r1 != r) {
                    Set<Character> possibleValues = puzzle[r1][c1].getCandidates();
                    if (possibleValues.contains(valueToFind)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
