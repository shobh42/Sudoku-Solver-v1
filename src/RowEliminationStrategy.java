public class RowEliminationStrategy implements SolvingStrategy
{
    private int count = 0;

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In row strategy");

        boolean stateChanged = false;
        for(int currentRow = 0; currentRow < size; currentRow++){

            for(int currentCol = 0; currentCol < size; currentCol++){

                Cell currentCell = puzzle[currentRow][currentCol];
                if(currentCell.getSize() == 1){
                    char candidate = (char) currentCell.getCandidates().toArray()[0];
                    for(int col = 0; col < size; col++){

                        if(currentCol != col && puzzle[currentRow][col].getCandidates().contains(candidate)){
                            count++;
                            puzzle[currentRow][col].getCandidates().remove(candidate);
                            stateChanged = true;
                        }
                    }
                }
            }
        }

        return stateChanged;
    }

    public int getCount(){
        return count;
    }
}
