public class ColumnEliminationStrategy implements SolvingStrategy {

    @Override
    public boolean solve(int size, Cell[][] puzzle) {

        System.out.println("In column strategy");
        boolean stateChanged = false;
        for(int candidateRow = 0; candidateRow < size; candidateRow++){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){

                Cell candidateCell = puzzle[candidateRow][candidateColumn];
                if(candidateCell.getSize() == 1){
                    char candidate = (char) candidateCell.getCandidates().toArray()[0];
                    for(int otherCandidateCol = 0; otherCandidateCol < size; otherCandidateCol++){

                        if(otherCandidateCol != candidateColumn && puzzle[candidateRow][otherCandidateCol].getSize() > 1
                            && puzzle[candidateRow][otherCandidateCol].getCandidates().contains(candidate)){

                            puzzle[candidateRow][otherCandidateCol].getCandidates().remove(candidate);
                            stateChanged = true;
                        }
                    }
                }
            }
        }

        return stateChanged;
    }
}
