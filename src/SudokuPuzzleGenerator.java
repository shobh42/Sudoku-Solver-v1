import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPuzzleGenerator {

    public Cell[][] generatePuzzle(int size, BufferedReader reader, Set<Integer> validCharacters) throws IOException {

        Cell[][] puzzle = new Cell[size][size];
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null){
            String []values = line.split(" ");
            for(int j = 0; j < size; j++){
                Set<Integer> possibleValues = new HashSet<>();
                if(values[j].equals("-")){
                    for(Integer value : validCharacters){
                        possibleValues.add(value);
                    }
                }else{
                    possibleValues.add(Integer.parseInt(values[j]));
                }

                Cell cell = new Cell(possibleValues);
                puzzle[i][j] = cell;
            }

            i++;
        }

        return puzzle;
    }
}
