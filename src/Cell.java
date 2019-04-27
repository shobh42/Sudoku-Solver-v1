import java.util.Set;

public class Cell {

    private Set<Character> possibleCandidate;

    public Cell(Set <Character> possibleCandidate){
        this.possibleCandidate = possibleCandidate;
    }

    public int getSize(){
        return possibleCandidate.size();
    }

    public Set<Character> getCandidates(){
        return possibleCandidate;
    }
}
