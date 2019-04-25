import java.util.Set;

public class Cell {

    private Set<Integer> possibleCandidate;

    public Cell(Set <Integer> possibleCandidate){
        this.possibleCandidate = possibleCandidate;
    }

    public int getSize(){
        return possibleCandidate.size();
    }

    public Set<Integer> getCandidates(){
        return possibleCandidate;
    }
}
