import java.util.HashSet;
import java.util.Set;

public class Cell {

    private Set<Integer> possibleValues;

    public Cell(Set <Integer> possibleValues){
        this.possibleValues = possibleValues;
    }

    public int getSize(){
        return possibleValues.size();
    }

    public Set<Integer> getCandidates(){
        return possibleValues;
    }
}
