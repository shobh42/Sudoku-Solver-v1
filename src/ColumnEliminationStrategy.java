public class ColumnEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In column strategy");
        boolean stateChanged = false;
        for(int currentCol = 0; currentCol < size; currentCol++){

            for(int currentRow = 0; currentRow < size; currentRow++){

                Cell currentCell = puzzle[currentRow][currentCol];
                if(currentCell.getSize() == 1){
                    char candidate = (char) currentCell.getCandidates().toArray()[0];
                    for(int row = 0; row < size; row++){

                        if(currentRow != row && puzzle[row][currentCol].getSize() != 1){
                            puzzle[row][currentCol].getCandidates().remove(candidate);
                            stateChanged = true;
                        }
                    }
                }
            }
        }

        return stateChanged;
    }
}
