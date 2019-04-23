import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ValidCharacterGenerator {

    public Set<Integer> generateValidSetOfCharacter(int size, BufferedReader reader) throws IOException {

        Set<Integer> validCharSet = new HashSet<>();
        String validCharLine = reader.readLine();
        String []values = validCharLine.split(" ");
        for(int i = 0; i < size; i++){
            validCharSet.add(Integer.parseInt(values[i]));
        }
        
        return validCharSet;
    }
}
