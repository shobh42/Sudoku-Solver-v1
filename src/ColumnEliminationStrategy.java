public class ColumnEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        boolean stateChanged = false;
        for(int currentCol = 0; currentCol < size; currentCol++){

            for(int currentRow = 0; currentRow < size; currentRow++){

                Cell currentCell = puzzle[currentRow][currentCol];
                if(currentCell.getSize() == 1){
                    int candidate = (int) currentCell.getCandidates().toArray()[0];
                    for(int row = 0; row < size; row++){

                        if(currentRow != row){
                            puzzle[currentCol][row].getCandidates().remove(candidate);
                            stateChanged = true;
                        }
                    }
                }
            }
        }

        return stateChanged;
    }
}
