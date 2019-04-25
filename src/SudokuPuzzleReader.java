import java.io.*;
import java.util.Set;

public class SudokuPuzzleReader {

    public Cell[][] readFile(String path) throws InvalidPuzzleException, IllegalCharacterException, IOException {

        File file = new File("C:\\Users\\Shobhit\\Desktop\\repos\\Sudoku-Solver\\src\\puzzles\\Puzzle-4x4-0001.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        final int PUZZLE_SIZE = Integer.valueOf(reader.readLine());
        if(new PuzzleSizeValidator().isSizeIsValid(PUZZLE_SIZE)) {
            throw new InvalidPuzzleException("Puzzle size " + PUZZLE_SIZE + " is not a valid size");
        }

        Set<Integer> validCharacters = new ValidCandidateGenerator().generateValidCandidates(PUZZLE_SIZE, reader);
        Cell[][] puzzle = new SudokuPuzzleGenerator().generatePuzzle(PUZZLE_SIZE, reader, validCharacters);
        boolean isPuzzleValid = new SudokuPuzzleValidator(validCharacters, puzzle, PUZZLE_SIZE).isValid();
        if(!isPuzzleValid){
            throw new InvalidPuzzleException("Sudoku Puzzle is not valid");
        }

        return puzzle;
    }
}
