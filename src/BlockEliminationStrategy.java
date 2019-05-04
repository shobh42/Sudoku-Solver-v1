import java.util.List;
import java.util.Set;

public class BlockEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In block elimination");
        int sqrtOfSize = (int)Math.sqrt(size);
        boolean stateChanged = false;
        for(int currentBlockRow = 0; currentBlockRow < size; currentBlockRow+=sqrtOfSize){

            for(int currentBlockCol = 0; currentBlockCol < size; currentBlockCol+=sqrtOfSize) {

                for (int currentBlockRowToCompare = currentBlockRow;
                     currentBlockRowToCompare < (currentBlockRow + sqrtOfSize) && currentBlockRowToCompare < size;
                     currentBlockRowToCompare++) {

                    for (int currentBlockColToCompare = currentBlockCol;
                         currentBlockColToCompare < (currentBlockCol + sqrtOfSize) && currentBlockColToCompare < size;
                         currentBlockColToCompare++) {


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

        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(puzzle.length);
        for (int row = currentBlockRow;
             row < currentBlockRow + sqrtOfSize && row < size; row++) {

            for (int col = currentBlockCol;
                 col < currentBlockCol + sqrtOfSize && col < size; col++) {

                boolean res = puzzle[row][col].getSize() != 1 &&
                        row != currentBlockRowToCompare && col != currentBlockColToCompare;
                if(puzzle[row][col].getSize() != 1 &&
                        !(row != currentBlockRowToCompare && col != currentBlockColToCompare)){
                    char candidate = (char) puzzle[currentBlockRowToCompare][currentBlockColToCompare].getCandidates().toArray()[0];
                    Cell cell = puzzle[row][col];
                    cell.getCandidates().remove(candidate);
                }
            }
        }
    }
}