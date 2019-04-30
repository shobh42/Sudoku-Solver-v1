import java.util.Set;

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
                        boolean otherRowHasValue = checkOtherRowExcludingCurrentBlockHasCandidate(row, col, puzzle, values[i]);
                        boolean otherColHasValue = checkOtherColumnExcludingCurrentBlockHasCandidate(row, col, puzzle, values[i]);

                        if(!otherRowHasValue){
                            removeTheValueFromTheBlockExcludingColumn(row, col, puzzle, values[i]);
                        }

                        if(!otherColHasValue){
                            removeTheValueFromTheBlockExcludingRow(row, col, puzzle, values[i]);
                        }
                    }
                }
            }
        }

        return stateChanged;
    }

    private void removeTheValueFromTheBlockExcludingRow(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidateToRemove) {
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

    private void removeTheValueFromTheBlockExcludingColumn(int candidateRow, int candidateColumn, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = (candidateRow/sqrtOfSize)*sqrtOfSize; r1 < ((candidateRow/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (candidateColumn/sqrtOfSize)*sqrtOfSize; c1 < ((candidateColumn/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){

                if(r1 != candidateRow){
                    Set <Character> possibleValues = puzzle[r1][c1].getCandidates();

                    if(possibleValues.contains(valueToRemove)){
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                    }

                }
            }
        }
    }

    private boolean checkOtherRowExcludingCurrentBlockHasCandidate(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidate) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int r1 = 0; r1<size;r1++ ){
            if((candidateRow/sqrtOfSize)!=(r1/sqrtOfSize)) {
                Set <Character> possibleValues = puzzle[r1][candidateColumn].getCandidates();
                if(possibleValues.contains(candidate)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkOtherColumnExcludingCurrentBlockHasCandidate(int candidateRow, int candidateColumn, Cell[][] puzzle, char candidate) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int c1 = 0 ;c1 < size ; c1++ ){
            if((candidateColumn/sqrtOfSize)!=(c1/sqrtOfSize)) {
                Set <Character> possibleValues = puzzle[candidateRow][c1].getCandidates();
                if(possibleValues.contains(candidate)){
                    return true;
                }
            }
        }
        return false;
    }
}
