import java.util.*;

/**
 * Created by Shobhit on 11/15/2016.
 */
public class HiddenSingleStrategy implements SolvingStrategy{

    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        boolean stateChanged = false;
        for(int row = 0; row < size; row++){

            for(int col = 0; col < size; col++){

                Character[] candidates = (Character[])puzzle[row][col].getCandidates().toArray();
                for (int index = 0; index < candidates.length; index++) {

                    char valueToCheck = candidates[index];
                    if (checkRowHasHiddenValue(row, col, puzzle, valueToCheck) ||
                            checkColumnHasHiddenValue(row, col, puzzle, valueToCheck)) {
                        char valueFound = valueToCheck;
                        Set<Character> s = new HashSet<>();
                        s.add(valueFound);
                        puzzle[row][col] = new Cell(s);
                        stateChanged = true;
                        break;
                    }
                }
            }
        }

        return stateChanged;
    }

    private boolean checkRowHasHiddenValue(int rowToCheck,int colToCheck,Cell [][]puzzle,char valueToCheck){

        int size = puzzle.length;
        for(int col = 0 ; col<size ; col++){
            if(col!=colToCheck){

                Set<Character> setOfValues =  puzzle[rowToCheck][col].getCandidates();
                if(setOfValues.contains(valueToCheck)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkColumnHasHiddenValue(int rowToCheck,int colToCheck,Cell [][]puzzle,char valueToCheck){
        int size = puzzle.length;
        for(int row = 0 ; row<size ; row++){
            if(row!=rowToCheck){
                Set < Character> setOfValues =  puzzle[row][colToCheck].getCandidates();
                if(setOfValues.contains(valueToCheck)){
                    return false;
                }
            }
        }
        return true;
    }
}

