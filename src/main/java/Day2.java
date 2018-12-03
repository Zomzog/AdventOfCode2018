import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public int part1(List<String> values) {
        int twoTime = 0;
        int threeTime = 0;

        for (String val : values) {
                final Map<Character, Long> collect = val.chars()
                        .mapToObj(i -> (char)i)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            if (collect.values().contains(2L)){
                twoTime++;
            }
            if (collect.values().contains(3L)) {
                threeTime++;
            }
        }
        return twoTime*threeTime;
    }
}
