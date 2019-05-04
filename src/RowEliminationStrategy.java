public class RowEliminationStrategy implements SolvingStrategy
{
    private int count = 0;

    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        System.out.println("In row strategy");
        boolean stateChanged = false;
        for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){

            for(int candidateRow = 0; candidateRow < size; candidateRow++){

                Cell candidateCell = puzzle[candidateRow][candidateColumn];
                if(candidateCell.getSize() == 1){
                    char candidate = (char) candidateCell.getCandidates().toArray()[0];
                    for(int otherCandidateRow = 0; otherCandidateRow < size; otherCandidateRow++) {

                        if (otherCandidateRow != candidateRow && puzzle[otherCandidateRow][candidateColumn].getSize() != 1 &&
                                puzzle[otherCandidateRow][candidateColumn].getCandidates().contains(candidate)) {
                            puzzle[otherCandidateRow][candidateColumn].getCandidates().remove(candidate);
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
