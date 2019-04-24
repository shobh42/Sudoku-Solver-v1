import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Driver {

    public static void main(String[] args) {

        System.out.println("Enter the path of the puzzle file");
        try {
            BufferedReader reader = new SudokuPuzzleReader().readFile(null);
            final int PUZZLE_SIZE = Integer.valueOf(reader.readLine());
            boolean isPuzzleNotSolved = true;
            while(isPuzzleNotSolved){
                if(new PuzzleSizeValidator().isSizeIsValid(PUZZLE_SIZE)){
                    Set<Integer> validCharacters = new ValidCharacterGenerator().generateValidSetOfCharacter(PUZZLE_SIZE, reader);
                    Cell[][] puzzle = new SudokuPuzzleGenerator().generatePuzzle(PUZZLE_SIZE, reader, validCharacters);
                    boolean isPuzzleValid = new SudokuPuzzleValidator(validCharacters, puzzle, PUZZLE_SIZE).isValid();
                    if(!isPuzzleValid){
                        throw new PuzzleInvalidException("Sudoku Puzzle is not valid");
                    }

                    List<List<Integer>> solvedPuzzle = new SudokuSolver(PUZZLE_SIZE, puzzle).solve();
                }else {
                    System.out.println("Puzzle size is not valid");
                }
            }
        } catch (PuzzleInvalidException e) {
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
