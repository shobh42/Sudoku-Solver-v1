public class BlockEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        int sqrt = (int) Math.sqrt(size);
        for(int currentRow = 0; currentRow < size; currentRow+=currentRow + sqrt){

            for(int currentCol = 0; currentCol < size; currentCol+=sqrt){

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