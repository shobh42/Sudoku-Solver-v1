import java.util.*;


/**
 * If any two cell in row, column or block has the same pair of values of size 2, then the rest of the corresponding row, column
 * block does not supposed to have those value. This strategy eliminates those values from the rest of the cell
 */
public class NakedPairStrategy implements SolvingStrategy{

    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        boolean stateChanged = false;

        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                Cell cell = puzzle[row][col];
                if(cell.getCandidates().size() == 2){

                    boolean isCandidatePresentInBlock = checkIfBlockHasPair(row, col, puzzle, cell.getCandidates());
                    boolean isCandidatePresentInRow = checkIfRowHasPair(row, col, puzzle, cell.getCandidates());
                    boolean isCandidatePresentInCol = checkIfColumnHasPair(row, col, puzzle, cell.getCandidates());

                    if (isCandidatePresentInBlock) {
                        removeValuesFromBlock(row, col, puzzle, cell.getCandidates());
                    }
                    if (isCandidatePresentInRow) {
                        removeValuesFromRow(row, col, puzzle, cell.getCandidates());
                    }
                    if (isCandidatePresentInCol) {
                        removeValuesFromColumn(row, col, puzzle, cell.getCandidates());
                    }
                }
            }
        }

        return stateChanged;
    }

    private void removeValuesFromColumn(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        for(int r1 = 0;r1<size;r1++){
            Set<Character> cellFromValuesToRemove = puzzle[r1][c].getCandidates();
            if(r!=r1 && cellFromValuesToRemove.size()>2 ){
                char value1 = (Character)values.toArray()[0];
                char value2 = (Character)values.toArray()[1];
                cellFromValuesToRemove.remove(value1);
                cellFromValuesToRemove.remove(value2);
            }
        }
    }

    private void removeValuesFromRow(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        for(int c1 = 0;c1<size;c1++){
            Set<Character> cellFromValuesToRemove = puzzle[r][c1].getCandidates();
            if(c!=c1 && cellFromValuesToRemove.size()>2 ){
                char value1 = (Character)values.toArray()[0];
                char value2 = (Character)values.toArray()[1];
                cellFromValuesToRemove.remove(value1);
                cellFromValuesToRemove.remove(value2);
            }
        }
    }

    private void removeValuesFromBlock(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize ; r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (c/sqrtOfSize)*sqrtOfSize ; c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){
                Set<Character> cellFromValuesToRemove = puzzle[r1][c1].getCandidates();

                if(r1 != r && c1!=c && cellFromValuesToRemove.size()>2 ){
                    char value1 = (Character)values.toArray()[0];
                    char value2 = (Character)values.toArray()[1];
                    cellFromValuesToRemove.remove(value1);
                    cellFromValuesToRemove.remove(value2);
                }
            }
        }
    }

    private boolean checkIfColumnHasPair(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        int countCellsWithSamePairs = 1;

        for(int r1 = 0; r1<size ; r1++){
            Set <Character> possibleValues = puzzle[r1][c].getCandidates();
            if(r1!=r && puzzle[r1][c].getSize()==2){
                if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                    countCellsWithSamePairs++;
                }
            }
        }
        return (countCellsWithSamePairs == 2);
    }

    private boolean checkIfRowHasPair(int r, int c, Cell[][] puzzle, Set<Character> values) {
        int size = puzzle.length;
        int countCellsWithSamePairs = 1;

        for(int c1 = 0; c1<size ; c1++){

            Set <Character> possibleValues = puzzle[r][c1].getCandidates();
            if(c1!=c && puzzle[r][c1].getSize()==2){
                if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                    countCellsWithSamePairs++;
                }
            }
        }
        return (countCellsWithSamePairs == 2);
    }


    private boolean checkIfBlockHasPair(int currentRowOfPair , int currentColumnOfPair, Cell[][]puzzle, Set<Character> values) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        int countCellsWithSamePairs = 1;
        for(int rowToCheck = (currentRowOfPair/sqrtOfSize)*sqrtOfSize ; rowToCheck < ((currentRowOfPair/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; rowToCheck++){

            for(int colToCheck = (currentColumnOfPair/sqrtOfSize)*sqrtOfSize   ; colToCheck < ((currentColumnOfPair/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; colToCheck++){
                Set <Character> possibleValues = puzzle[rowToCheck][colToCheck].getCandidates();

                if(rowToCheck != currentRowOfPair && colToCheck!=currentColumnOfPair && possibleValues.size()==2 ){

                    if(values.toArray()[0]==possibleValues.toArray()[0] && values.toArray()[1]==possibleValues.toArray()[1]){
                        countCellsWithSamePairs++;
                    }

                }
            }
        }
        return (countCellsWithSamePairs == 2);
    }
}
