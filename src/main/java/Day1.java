import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {

    public int part1(final List<Integer> values) {
        int result = 0;
        for(int v : values) {
            result += v;
        }
        return result;
    }

    public int part2(List<Integer> values) {
        Set<Integer> alreadySeen = new HashSet<>();
        alreadySeen.add(0);
        int result = 0;
        while (true) {
            for (int v : values) {
                result += v;
                if (alreadySeen.contains(result)) {
                    return result;
                } else {
                    alreadySeen.add(result);
                }
            }
        }
    }
}
