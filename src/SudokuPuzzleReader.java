import java.io.*;

public class SudokuPuzzleReader {

    public BufferedReader readFile(String path) throws IOException {

        File file = new File("C:\\Users\\Shobhit\\Desktop\\repos\\Sudoku-Solver\\src\\puzzles\\Puzzle-4x4-0001.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }

}
