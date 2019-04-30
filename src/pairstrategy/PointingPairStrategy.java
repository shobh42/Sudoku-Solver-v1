package pairstrategy;

import solvingstrategy.SolvingStrategy;
import util.Cell;

import java.util.*;

/**
 * Created by Shobhit on 11/16/2016.
 */
public class PointingPairStrategy extends SolvingStrategy {
    private boolean stateChanged;

    @Override
    public boolean applystrategy(Map<Integer, List<Integer>> cellToFill, Cell[][] puzzle) {

        Set<Integer> rowToFill = cellToFill.keySet();

        for (int row : rowToFill) {
            List<Integer> colsToFill = cellToFill.get(row);
            for (int col : colsToFill) {
                Set<Character> values = puzzle[row][col].getAllPossibleValues();
                for (int i = 0; i < values.size(); i++) {
                    char valueToCheck = (Character) values.toArray()[i];
                    boolean blockHasValue = checkIfBlockContainsValue(row, col, puzzle, valueToCheck);
                    boolean rowHasValue = checkOnlyRowHasValue(row, col, puzzle, valueToCheck);
                    boolean colHasValue = checkOnlyColumnHasValue(row, col, puzzle, valueToCheck);

                    if (!blockHasValue && (rowHasValue ^ colHasValue)) {

                        if (rowHasValue) {
                            removeTheValuesFromThatRow(row, col, puzzle, valueToCheck);
                        } else {
                            removeTheValuesFromThatColumn(row, col, puzzle, valueToCheck);
                        }
                    }

                }
            }
        }
        return stateChanged;
    }

    private void removeTheValuesFromThatRow(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int col=0 ; col <size;col++){
            if((col/sqrtOfSize) != (c/sqrtOfSize)){
                char value =  puzzle[r][col].getPossibleValues();
                if(value == '0'){
                    Set <Character> possibleValues = puzzle[r][col].getAllPossibleValues();
                    if(possibleValues.contains(valueToRemove)){
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                    }
                }
            }
        }
    }

    private void removeTheValuesFromThatColumn(int r, int c, Cell[][] puzzle, char valueToRemove) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int row=0 ; row <size;row++){
            if((row/sqrtOfSize) != (r/sqrtOfSize)){
                char value =  puzzle[row][c].getPossibleValues();
                if(value == '0'){
                    Set <Character> possibleValues = puzzle[row][c].getAllPossibleValues();
                    if(possibleValues.contains(valueToRemove)){
                        stateChanged = true;
                        possibleValues.remove(valueToRemove);
                    }
                }
            }
        }
    }

    private boolean checkOnlyRowHasValue(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);

        for(int c1 = (c/sqrtOfSize)*sqrtOfSize ;c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize; c1++ ){
            char value = puzzle[r][c1].getPossibleValues();
            if(c!=c1) {
                Set <Character> possibleValues = puzzle[r][c1].getAllPossibleValues();
                if(possibleValues.contains(valueToFind)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkOnlyColumnHasValue(int r, int c, Cell[][] puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize ;r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize; r1++ ){
            char value = puzzle[r1][c].getPossibleValues();
            if(r!=r1 ) {
                Set <Character> possibleValues = puzzle[r1][c].getAllPossibleValues();
                if(possibleValues.contains(valueToFind)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkIfBlockContainsValue(int r , int c, Cell[][]puzzle, char valueToFind) {
        int size = puzzle.length;
        int sqrtOfSize = (int) Math.sqrt(size);
        for(int r1 = (r/sqrtOfSize)*sqrtOfSize ; r1 < ((r/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; r1++){

            for(int c1 = (c/sqrtOfSize)*sqrtOfSize   ; c1 < ((c/sqrtOfSize)*sqrtOfSize)+sqrtOfSize ; c1++){
                if(r1 != r && c1!=c){
                    Set <Character> possibleValues = puzzle[r1][c1].getAllPossibleValues();

                    if(possibleValues.contains(valueToFind)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
