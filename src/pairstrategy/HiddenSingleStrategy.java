package pairstrategy;

import solvingstrategy.SolvingStrategy;
import util.Cell;

import java.util.*;

/**
 * Created by Shobhit on 11/15/2016.
 */
public class HiddenSingleStrategy extends SolvingStrategy{

    @Override
    public boolean applystrategy(Map<Integer, List<Integer>> cellToFill, Cell[][] puzzle) {
        Set<Integer> rowToFill = cellToFill.keySet();
        boolean stateChanged = false;

        for (int row : rowToFill) {
            List<Integer> colsToFill = cellToFill.get(row);
            for (int col : colsToFill) {
                Set<Character> valuesToLook = puzzle[row][col].getAllPossibleValues();
                for (int value = 0; value < valuesToLook.size(); value++) {

                    char valueToCheck = (Character) valuesToLook.toArray()[value];
                    if (checkRowHasHiddenValue(row, col, puzzle, valueToCheck) || checkColumnHasHiddenValue(row, col, puzzle, valueToCheck)) {
                        char valueFound = valueToCheck;
                        puzzle[row][col] = new Cell(valueFound);
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

                char value = puzzle[rowToCheck][col].getPossibleValues();
                Set < Character> setOfValues =  puzzle[rowToCheck][col].getAllPossibleValues();
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
                Set < Character> setOfValues =  puzzle[row][colToCheck].getAllPossibleValues();
                if(setOfValues.contains(valueToCheck)){
                    return false;
                }
            }
        }
        return true;
    }
}

