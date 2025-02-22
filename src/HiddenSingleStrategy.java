import java.util.*;

/**
 * Created by Shobhit on 11/15/2016.
 */
public class HiddenSingleStrategy implements SolvingStrategy{
    private int count;
    private double totalTime;

    public HiddenSingleStrategy(){
        count = 0;
        totalTime = 0;
    }


    @Override
    public boolean solve(int size, Cell[][] puzzle) {
        boolean stateChanged = false;
        System.out.println("Inside Hidden Single Strategy");
        long startTime = System.currentTimeMillis();
        for(int candidateRow = 0; candidateRow < size; candidateRow++){

            for(int candidateColumn = 0; candidateColumn < size; candidateColumn++){

                if(puzzle[candidateRow][candidateColumn].getCandidates().size() > 1) {
                    Object[] cand = puzzle[candidateRow][candidateColumn].getCandidates().toArray();
                    Character[] candidates = new Character[cand.length];
                    for (int temp = 0; temp < cand.length; temp++) {
                        candidates[temp] = (Character) cand[temp];
                    }

                    for (int index = 0; index < candidates.length; index++) {

                        char candidate = candidates[index];
                        if (checkCandidateIsNotPresentInRow(candidateRow, candidateColumn, puzzle, candidate) ||
                                checkCandidateIsNotPresentInColumn(candidateRow, candidateColumn, puzzle, candidate)) {
                            Set<Character> s = new HashSet<>();
                            s.add(candidate);
                            puzzle[candidateRow][candidateColumn] = new Cell(s);
                            stateChanged = true;
                            count++;
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        totalTime+= (endTime - startTime);
        return stateChanged;
    }

    public int getCount() {
        return count;
    }

    public double getTotalTime() {
        return totalTime;
    }

    private boolean checkCandidateIsNotPresentInRow(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int otherCandidateRow = 0; otherCandidateRow < size; otherCandidateRow++){
            if(otherCandidateRow != candidateRow){
                Set<Character> candidates =  puzzle[otherCandidateRow][candidateColumn].getCandidates();
                if(candidates.contains(candidate)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkCandidateIsNotPresentInColumn(int candidateRow, int candidateColumn, Cell [][]puzzle, char candidate){
        int size = puzzle.length;
        for(int otherCandidateColumn = 0; otherCandidateColumn<size; otherCandidateColumn++){
            if(otherCandidateColumn != candidateColumn){
                Set<Character> candidates =  puzzle[candidateRow][otherCandidateColumn].getCandidates();
                if(candidates.contains(candidate)){
                    return false;
                }
            }
        }

        return true;
    }

    public String toString(){
        return "Hidden Single has eliminated " + count + " and has taken " + totalTime/1000;
    }
}

