import java.util.List;
import java.util.Set;

public class BlockEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        int sqrtOfSize = (int)Math.sqrt(size);
        boolean stateChanged = false;
        for(int currentBlockRow = 0; currentBlockRow < size; currentBlockRow+=sqrtOfSize){

            for(int currentBlockCol = 0; currentBlockCol < size; currentBlockCol+=sqrtOfSize) {

                for (int currentBlockRowToCompare = currentBlockRow;
                     currentBlockRowToCompare < currentBlockRowToCompare + sqrtOfSize; currentBlockRowToCompare++) {

                    for (int currentBlockColToCompare = currentBlockCol;
                         currentBlockColToCompare < currentBlockColToCompare + sqrtOfSize; currentBlockColToCompare++) {


                        Cell currentCell = puzzle[currentBlockRowToCompare][currentBlockColToCompare];
                        if (currentCell.getSize() == 1) {
                            removeValuesFromBlock(puzzle, currentBlockRow, currentBlockCol,
                                    currentBlockRowToCompare, currentBlockColToCompare);
                            stateChanged = true;
                        }
                    }
                }
            }
        }

        return stateChanged;
    }

    private void removeValuesFromBlock(Cell[][] puzzle, int currentBlockRow, int currentBlockCol,
                                       int currentBlockRowToCompare, int currentBlockColToCompare) {

        int sqrtOfSize = (int) Math.sqrt(puzzle.length);
        for (int row = currentBlockRow;
             row < currentBlockRowToCompare + sqrtOfSize; row++) {

            for (int col = currentBlockCol;
                 col < currentBlockColToCompare + sqrtOfSize; col++) {

                if(row != currentBlockRowToCompare && col != currentBlockColToCompare){
                    int candidate = (int) puzzle[currentBlockRowToCompare][currentBlockColToCompare].getCandidates().toArray()[0];
                    Cell cell = puzzle[row][col];
                    cell.getCandidates().remove(candidate);
                }
            }
        }
    }
}