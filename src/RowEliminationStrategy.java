public class RowEliminationStrategy implements SolvingStrategy
{
    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        boolean state = false;
        for(int currentRow = 0; currentRow < size; currentRow++){

            for(int currentCol = 0; currentCol < size; currentCol++){

                Cell currentCell = puzzle[currentRow][currentCol];
                if(currentCell.getSize() == 1){
                    int candidate = (int) currentCell.getCandidates().toArray()[0];
                    for(int col = 0; col < size; col++){

                        if(currentCol != col){
                            puzzle[currentRow][col].getCandidates().remove(candidate);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }
}
