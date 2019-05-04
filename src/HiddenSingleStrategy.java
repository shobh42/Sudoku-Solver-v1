import java.util.*;

/**
 * Created by Shobhit on 11/15/2016.
 */
public class HiddenSingleStrategy implements SolvingStrategy{

    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        boolean stateChanged = false;
        System.out.println("Inside Hidden Single Strategy");
        for(int candidateRow = 0; candidateRow < size; candidateRow++){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){

                Object[] cand = puzzle[candidateRow][candidateColumn].getCandidates().toArray();
                Character[] candidates = new Character[cand.length];
                for(int temp = 0; temp < cand.length; temp++){
                    candidates[temp] = (Character) cand[temp];
                }

                for (int index = 0; index < candidates.length; index++) {

                    char candidate = candidates[index];
                    if (checkRowHasHiddenValue(candidateRow, candidateColumn, puzzle, candidate) ||
                            checkColumnHasHiddenValue(candidateRow, candidateColumn, puzzle, candidate)) {
                        char valueFound = candidate;
                        Set<Character> s = new HashSet<>();
                        s.add(valueFound);
                        puzzle[candidateRow][candidateColumn] = new Cell(s);
                        stateChanged = true;
                    }
                }
            }
        }

        return stateChanged;
    }

    private boolean checkRowHasHiddenValue(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int otherCandidateRow = 0; otherCandidateRow < size; otherCandidateRow++){
            if(otherCandidateRow != candidateRow){
                Set < Character> setOfValues =  puzzle[otherCandidateRow][candidateColumn].getCandidates();
                if(setOfValues.contains(candidate)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkColumnHasHiddenValue(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int col = 0 ; col<size ; col++){
            if(col!=candidateColumn){

                Set<Character> setOfValues =  puzzle[candidateRow][col].getCandidates();
                if(setOfValues.contains(candidate)){
                    return false;
                }
            }
        }
        return true;
    }
}

