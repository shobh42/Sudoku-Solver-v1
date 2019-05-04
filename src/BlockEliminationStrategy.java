public class BlockEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In block elimination");
        int sqrtOfSize = (int)Math.sqrt(size);
        boolean stateChanged = false;
        for(int candidateRow = 0; candidateRow < size; candidateRow+=sqrtOfSize){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn+=sqrtOfSize) {

                for (int otherCandidateRow = candidateRow;
                         otherCandidateRow < (candidateRow + sqrtOfSize) && otherCandidateRow < size;
                         otherCandidateRow++) {

                        for (int otherCandidateColumn = candidateColumn;
                             otherCandidateColumn < (candidateColumn + sqrtOfSize) && otherCandidateColumn < size;
                             otherCandidateColumn++) {

                            Cell otherCandidateCell = puzzle[otherCandidateRow][otherCandidateColumn];
                            if (otherCandidateCell.getSize() == 1) {
                                stateChanged = removeCandidateFromBlock(puzzle, candidateRow, candidateColumn,
                                        otherCandidateRow, otherCandidateColumn);
                            }
                        }
                    }
                }
            }


        return stateChanged;
    }

    private boolean removeCandidateFromBlock(Cell[][] puzzle, int candidateRow, int candidateColumn,
                                          int currentBlockRowToCompare, int currentBlockColToCompare) {

        boolean stateChanged = false;
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(puzzle.length);
        for (int row = candidateRow;
             row < candidateRow + sqrtOfSize && row < size; row++) {

            for (int col = candidateColumn;
                 col < candidateColumn + sqrtOfSize && col < size; col++) {

                if(puzzle[row][col].getSize() != 1 &&
                        !(row == currentBlockRowToCompare && col == currentBlockColToCompare)){
                    char candidate = (char) puzzle[currentBlockRowToCompare][currentBlockColToCompare].getCandidates().toArray()[0];
                    Cell cell = puzzle[row][col];
                    if(cell.getCandidates().contains(candidate)){
                        cell.getCandidates().remove(candidate);
                        stateChanged = true;
                    }

                }
            }
        }

        return stateChanged;
    }
}