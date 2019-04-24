import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPuzzleGenerator {

    public Cell[][] generatePuzzle(int size, BufferedReader reader, Set<Integer> validCharacters)
            throws IllegalCharacterException, IOException {

        Cell[][] puzzle = new Cell[size][size];
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null){
            String []values = line.split(" ");
            for(int col = 0; col < size; col++){
                Set<Integer> possibleValues = new HashSet<>();

                if(values[col].equals("-")){
                    for(Integer value : validCharacters){
                        possibleValues.add(value);
                    }
                }else{
                    if(!validCharacters.contains(Integer.parseInt(values[col]))){
                        throw new IllegalCharacterException("Valid character list does not have "+ values[col]);
                    }

                    possibleValues.add(Integer.parseInt(values[col]));
                }

                Cell cell = new Cell(possibleValues);
                puzzle[row][col] = cell;
            }

            row++;
        }

        return puzzle;
    }
}
