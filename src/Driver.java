import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Driver {

    public static void main(String[] args) {

        System.out.println("Enter the path of the puzzle file");
        try {
           Cell[][] puzzle = new SudokuPuzzleReader().readFile(null);
           new SudokuSolver().solve(null);
        } catch (InvalidPuzzleException e) {
            e.printStackTrace();
        } catch (IllegalCharacterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPuzzle(Cell[][] puzzle){
        for (int i = 0; i < puzzle.length; i++){

            for(int j = 0; j < puzzle.length; j++){
                System.out.print(puzzle[i][j].getSize() + " ");
            }
            System.out.println();
        }
    }
}
