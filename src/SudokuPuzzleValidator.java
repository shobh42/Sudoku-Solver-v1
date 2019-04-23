import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPuzzleValidator {

    public boolean isValid(Cell[][] sodukuPuzzle, int size){

        List<Set<Integer>> rows = new ArrayList<>(size);
        List<Set<Integer>> cols = new ArrayList<>(size);
        List<Set<Integer>> blocks = new ArrayList<>(size);

        for(int i = 0; i < size; i++){

            for(int j = 0; j < size; j++){

                if(rows.get(i) == null){
                    rows.add(new HashSet<>());
                }

                if(cols.get(j) == null){
                    cols.add(new HashSet<>());
                }

                int blockIndex = 1;
                

            }

        }



        return true;
    }
}
