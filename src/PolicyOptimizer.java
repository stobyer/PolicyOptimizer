import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by stas.o on 22/02/2017.
 */
public class PolicyOptimizer {
    public static void main(String [] argv){
        Policy policy = new Policy();
        try {
            Files.lines(new File(argv[1]).toPath())
                    .map(s -> s.trim())
                    .filter(s -> !s.isEmpty())
                    .forEach(s -> policy.addRule(Rule.createFromCsvLine(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
